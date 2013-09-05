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
package au.edu.anu.metadatastores.services.store.commands;

import java.util.List;

import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.metadatastores.store.publication.Publication;
import au.edu.anu.metadatastores.store.publication.PublicationService;

/**
 * <p>PublicationOption<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class PublicationOption extends StoreSubCommand {
	static final Logger LOGGER = LoggerFactory.getLogger(PublicationOption.class);
	
	@Option(name="-u", aliases={"--user"}, metaVar="<uid>",usage="User ids to find/update information for")
	private List<String> users;
	
	@Option(name="-y",aliases={"--year"}, metaVar="<year>",usage="Year to update information for")
	private Integer year;
	
	@Option(name="-h", aliases="--help")
	private boolean help = false;
	
	public void execute() throws StoreCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.PUB, this.getClass());
			return;
		}
		
		PublicationService pubService = PublicationService.getSingleton();
		
		if (year != null) {
			LOGGER.info("Updating publications for year: {}", year);
			List<Publication> publications = pubService.fetchPublicationsByYear(year.toString());
			pubService.savePublications(publications);
		}
		else if (users != null) {
			LOGGER.info("Updating publications for users: {}", users);
			for (String user : users) {
				List<Publication> publications = pubService.fetchPublicationsForUid(user);
				pubService.savePublications(publications);
			}
		}
		else {
			throw new StoreCommandException("No option found");
		}
	}
}
