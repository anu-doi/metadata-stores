/*******************************************************************************
 * Australian National University Metadata Stores
 * Copyright (C) 2013  The Australian National University
 * 
 * This file is part of Australian National University Metadata Stores.
 * 
 * Australian National University Metadata Stores is free software: you
 * can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package au.edu.anu.metadatastores.services.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;

import org.hibernate.engine.spi.SessionImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.services.store.StoreHibernateUtil;
import au.edu.anu.metadatastores.util.filter.ExtensionFileFilter;

/**
 * <p>ReportGenerator<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p>Class to generates reports</p>
 * 
 * @author Genevieve Turner
 *
 */
public class ReportGenerator {
	static final Logger LOGGER = LoggerFactory.getLogger(ReportGenerator.class);
	
	private static final String REPORT_PATH = "/WEB-INF/reports/";
	private static final String JASPER_UNCOMPILED_EXTENSION = "jrxml";
	private static final String JASPER_COMPILED_EXTENSION = "jasper";
	
	private String filePath_;
	private Map<String, Object> params_;
	
	/**
	 * Constructor
	 * 
	 * @param serverPath The path of the server application
	 * @param parameters The parameters sent to the object
	 */
	public ReportGenerator(String serverPath, Map<String, Object> parameters) {
		filePath_ = serverPath + REPORT_PATH + "rpt_mscollections.jasper";
		params_ = new HashMap<String, Object>();
		params_.put("baseURL", serverPath + REPORT_PATH);
		
		String endDateStr = (String) parameters.get("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			LOGGER.info("Date Str: {}", endDateStr);
			Timestamp endDate = new Timestamp(sdf.parse(endDateStr).getTime());
			params_.put("endDate", endDate);
		}
		catch (ParseException e) {
			LOGGER.error("Exception parsing date: {}", endDateStr);
		}
		
		String timePeriod = (String) parameters.get("timePeriod");
		params_.put("timePeriod", timePeriod);
	}
	
	/**
	 * Generate the report
	 * 
	 * @param format The format to generate - not this currently method currently only generates pdf's
	 * @return The http response with the report
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws JRException
	 * @throws IOException
	 */
	public Response generateReport(String format) 
			throws ClassNotFoundException, SQLException, JRException, IOException {
		Response response = null;
		response = generatePDF();
		return response;
	}
	
	/**
	 * Generate a PDF report
	 * 
	 * @return The response with the generated PDF as its content
	 * @throws JRException
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Response generatePDF() throws JRException, SQLException, IOException, ClassNotFoundException {
		InputStream inputStream = new FileInputStream(new File(filePath_));
		
		byte[] bytes = JasperRunManager.runReportToPdf(inputStream, params_, getConnection());
		return Response.ok(bytes).type("application/pdf").build();
	}
	
	/**
	 * Get a database connection
	 * 
	 * @return The connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		SessionImplementor sessionImplementor = (SessionImplementor) StoreHibernateUtil.getSessionFactory().openSession();
		
		return sessionImplementor.getJdbcConnectionAccess().obtainConnection();
	}
	
	/**
	 * Reload the reports
	 * 
	 * @param context The servlet context
	 */
	public static void reloadReports(ServletContext context) {
		String reportPath = context.getRealPath(REPORT_PATH);
		File file = new File(reportPath);
		if (!file.exists()) {
			LOGGER.error("Report path does not exist: {}", file.getAbsolutePath());
			throw new WebApplicationException(Response.status(500).entity("Report path does not exist").build());
		}
		File[] files = file.listFiles(new ExtensionFileFilter(JASPER_UNCOMPILED_EXTENSION));
		for (File jrxmlFile : files) {
			String outputFilename = stripExtension(jrxmlFile.getAbsolutePath()) + "." + JASPER_COMPILED_EXTENSION;
			File outputFile = new File(outputFilename);
			try {
				InputStream is = new FileInputStream(jrxmlFile);
				OutputStream os = new FileOutputStream(outputFile);
				try {
					JasperCompileManager.compileReportToStream(is, os);
				}
				catch(JRException e) {
					LOGGER.error("Exception compiling report: {}", jrxmlFile.getName(), e);
				}
				is.close();
				os.close();
			}
			catch (IOException e) {
				throw new WebApplicationException(Response.status(500).entity("Error reloading reports").build());
			}
		}
	}
	
	/**
	 * Remove the extension from the file name
	 * 
	 * @param filename The file name
	 * @return The name part of the file
	 */
	private static String stripExtension(String filename) {
		int index = filename.lastIndexOf(".");
		if (index == -1) {
			return filename;
		}
		return filename.substring(0, index);
	}
}
