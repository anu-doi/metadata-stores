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

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

/**
 * <p>StoreCommand<p>
 * 
 * <p>The Australian National University</p>
 * 
 * <p></p>
 * 
 * @author Genevieve Turner
 *
 */
public class StoreCommand {
	@Argument(handler=SubCommandHandler.class, metaVar="<command> [options]", usage="Current command values are publication, grant, person, epress.  Please use 'store <command> -h' for further information.")
	@SubCommands({
		@SubCommand(name="publication", impl=PublicationOption.class)
		,@SubCommand(name="grant", impl=GrantOption.class)
		,@SubCommand(name="epress", impl=EpressOption.class)
		,@SubCommand(name="person", impl=PersonOption.class)
	})
	StoreSubCommand cmd;
	
	@Option(name="-h", aliases="--help", usage="Displays this")
	private boolean help = false;
	public StoreSubCommand getCmd() {
		return cmd;
	}

	public void setCmd(StoreSubCommand cmd) {
		this.cmd = cmd;
	}

	public boolean isHelp() {
		return help;
	}

	public void setHelp(boolean help) {
		this.help = help;
	}
}
