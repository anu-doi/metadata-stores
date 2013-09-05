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

import au.edu.anu.metadatastores.store.grants.Grant;
import au.edu.anu.metadatastores.store.grants.GrantService;

/**
 * <p>GrantOption<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class GrantOption extends StoreSubCommand {
	static final Logger LOGGER = LoggerFactory.getLogger(GrantOption.class);
	
	@Option(name="-u", aliases={"--user"}, metaVar="<uid>",usage="User ids to find/update information for")
	private List<String> users;
	
	@Option(name="-h", aliases="--help")
	private boolean help = false;
	
	public void execute() throws StoreCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.GRANT, this.getClass());
			return;
		}
		GrantService grantService = GrantService.getSingleton();
		if (users != null) {
			LOGGER.info("Updating grants for users: {}", users);
			for (String uid : users) {
				List<Grant> grants = grantService.fetchGrantsForPerson(uid);
				grantService.saveGrants(grants);
			}
		}
		else {
			throw new StoreCommandException("No option found");
		}
	}
}
