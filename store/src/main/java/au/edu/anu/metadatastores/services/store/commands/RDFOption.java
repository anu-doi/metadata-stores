package au.edu.anu.metadatastores.services.store.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kohsuke.args4j.Option;

import au.edu.anu.metadatastores.rdf.RDFService;

public class RDFOption extends StoreSubCommand {
	@Option(name="-t", aliases={"--type"}, metaVar="<type>", usage="The system type to update all the records for")
	private String type;
	
	@Option(name="-d", aliases={"--date"}, metaVar="<date>", usage="The date to check against hte modified date to update those records")
	private String date;
	
	@Option(name="-a", aliases={"--all"}, metaVar="<type>", usage="")
	private boolean all = false;
	
	@Option(name="-h", aliases="--help")
	private boolean help = false;
	
	
	@Override
	public void execute() throws StoreCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.PUB, this.getClass());
			return;
		}
		
		RDFService service = RDFService.getSingleton();
		if (all) {
			service.updateAll();
		}
		else if (type != null) {
			service.updateFromSpecifiedSystem(type);
		}
		else if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				Date lastModiifedDate = sdf.parse(date);
				service.updateModifiedAfterDate(lastModiifedDate);
			}
			catch (ParseException e) {
				throw new StoreCommandException("Unparseable date format is: yyyy-MM-dd HH:mm");
			}
		}
		else {
			throw new StoreCommandException("No option found");
		}
	}

}
