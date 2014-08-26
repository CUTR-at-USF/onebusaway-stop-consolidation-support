package edu.usf.cutr.obascs.utils;

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
	return options;
    }

    public static String getSpreadSheetId(CommandLine cmd) {

	String spreadSheetId;
	if (cmd.hasOption(GeneralConstants.CL_OPTION_SPREADSHEET_ID)) {
	    spreadSheetId = cmd.getOptionValue(GeneralConstants.CL_OPTION_SPREADSHEET_ID);
	} else {
	    Scanner scanner = new Scanner(System.in);
	    Logger.log("Enter SpreadSheet id:");
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
}
