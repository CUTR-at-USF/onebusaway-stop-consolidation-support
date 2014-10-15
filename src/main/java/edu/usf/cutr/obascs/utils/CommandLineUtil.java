/*
 * Copyright 2014 University of South Florida
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

package edu.usf.cutr.obascs.utils;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import edu.usf.cutr.obascs.constants.GeneralConstants;

public class CommandLineUtil {

    public static Options createCommandLineOptions() {
	Options options = new Options();
	options.addOption(GeneralConstants.CL_OPTION_DEBUG, false, "print debugging information");
	options.addOption(GeneralConstants.CL_OPTION_OUTPUT_PATH, true, "Output file path");
	options.addOption(GeneralConstants.CL_OPTION_SPREADSHEET_ID, true, "Spread sheet id");
	options.addOption(GeneralConstants.CL_OPTION_INPUT_PATH, true, "Agency name mappig input file path");
	return options;
    }

    public static String getSpreadSheetId(CommandLine cmd) {

	String spreadSheetId;
	if (cmd.hasOption(GeneralConstants.CL_OPTION_SPREADSHEET_ID)) {
	    spreadSheetId = cmd.getOptionValue(GeneralConstants.CL_OPTION_SPREADSHEET_ID);
	} else {
	    Scanner scanner = new Scanner(System.in);
	    Logger.getInstance().log("Enter SpreadSheet id:");
	    spreadSheetId = scanner.nextLine();
	    scanner.close();
	}
	return spreadSheetId;
    }

    public static String getLogLevel(CommandLine cmd) {

	String logLevel = null;
	if (cmd.hasOption(GeneralConstants.CL_OPTION_DEBUG)) {
	    logLevel = new String(GeneralConstants.LOG_LEVEL_DEBUG);
	}
	return logLevel;
    }

    public static String getOutputPath(CommandLine cmd) {
	String filePath;
	if (cmd.hasOption(GeneralConstants.CL_OPTION_OUTPUT_PATH)) {
	    filePath = cmd.getOptionValue(GeneralConstants.CL_OPTION_OUTPUT_PATH);
	} else {
	    filePath = GeneralConstants.DEFAULT_FILE_LOCATION;
	}
	return filePath;
    }
    
    public static String getInputPath(CommandLine cmd) throws FileNotFoundException {
	String filePath;
	if (cmd.hasOption(GeneralConstants.CL_OPTION_INPUT_PATH)) {
	    filePath = cmd.getOptionValue(GeneralConstants.CL_OPTION_INPUT_PATH);
	} else {
	    throw new FileNotFoundException("Agency mapping file not found.");
	}
	return filePath;
    }
}
