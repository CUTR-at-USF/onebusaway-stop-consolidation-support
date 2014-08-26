package edu.usf.cutr.obascs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

import edu.usf.cutr.obascs.io.FileConsolidator;
import edu.usf.cutr.obascs.io.FileWriter;
import edu.usf.cutr.obascs.io.SpreadSheetReader;
import edu.usf.cutr.obascs.utils.CommandLineUtil;
import edu.usf.cutr.obascs.utils.Logger;

/*
 * TODO: documentation
 * TODO: implementing alternative outputting method as json 
 * TODO: create a files folder and add executable jar 
 */

public class OBASCSMain {

    public static void main(String[] args) {

	String logLevel = null;
	String filePath = null;
	String spreadSheetId = null;

	Options options = CommandLineUtil.createCommandLineOptions();
	CommandLineParser parser = new BasicParser();
	CommandLine cmd;
	try {
	    cmd = parser.parse(options, args);
	    logLevel = CommandLineUtil.getLogLevel(cmd);
	    filePath = CommandLineUtil.getOutputPath(cmd);
	    spreadSheetId = CommandLineUtil.getSpreadSheetId(cmd);
	} catch (ParseException e1) {
	    Logger.logError(e1, logLevel);
	}

	Logger.log("Consolidation started...");
	Logger.log("Trying as public url");

	ListFeed listFeed = null;
	Boolean authRequired = false;
	try {
	    listFeed = SpreadSheetReader.readPublicSpreadSheet(spreadSheetId);
	} catch (IOException e) {
	    Logger.logError(e, logLevel);
	} catch (ServiceException e) {
	    Logger.log("Authentication Required");
	    authRequired = true;
	}

	if (listFeed == null && authRequired == true) {
	    Scanner scanner = new Scanner(System.in);
	    String userName, password;
	    Logger.log("UserName:");
	    userName = scanner.nextLine();
	    Logger.log("Password:");
	    password = scanner.nextLine();
	    scanner.close();

	    try {
		listFeed = SpreadSheetReader.readPrivateSpreadSheet(userName, password, spreadSheetId);
	    } catch (IOException e) {
		Logger.logError(e, logLevel);
	    } catch (ServiceException e) {
		Logger.logError(e, logLevel);
	    }
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
