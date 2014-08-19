package edu.usf.cutr.obascs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.io.FileConsolidator;
import edu.usf.cutr.obascs.io.FileWriter;
import edu.usf.cutr.obascs.io.SpreadSheetReader;
import edu.usf.cutr.obascs.utils.Logger;

/*
 * TODO: documentation
 * TODO: add json alternative
 */

public class OBASCSMain {

    public static void main(String[] args) {

	String logLevel = null;
	String filePath = null;
	try {
	    filePath = args[0];
	} catch (Exception e) {
	    Logger.log("INFO: File path set to default with output name:" + GeneralConstants.DEFAULT_FILE_LOCATION);
	    filePath = GeneralConstants.DEFAULT_FILE_LOCATION;
	}
	
	try {
	    logLevel = args[1]; // -d is debug logging
	} catch (Exception e) {
	}

	Scanner scanner = new Scanner(System.in);
	String spreadSheetUrl, userName, password;
	Logger.log("Enter SpreadSheet url:");
	spreadSheetUrl = scanner.nextLine();
	Logger.log("UserName:");
	userName = scanner.nextLine();
	Logger.log("Password:");
	password = scanner.nextLine();

	scanner.close();
	Logger.log("Consolidation started...");

	SpreadSheetReader ssr = new SpreadSheetReader(userName, password,spreadSheetUrl);
	ListFeed listFeed = null;
	try {
	    listFeed = ssr.readSpreadSheet();

	} catch (IOException e) {
	    Logger.logError(e, logLevel);
	} catch (ServiceException e) {
	    Logger.logError(e, logLevel);
	}

	if (listFeed != null) {
	    String consolidatedString = FileConsolidator.consolidateFile(listFeed);
	    try {
		FileWriter.writeToFile(consolidatedString, filePath);
	    } catch (FileNotFoundException e) {
		Logger.logError(e, logLevel);
	    }
	} else {
	    Logger.logError("Cannot write files");
	}

	Logger.log("Consolidation finished...");

    }
}
