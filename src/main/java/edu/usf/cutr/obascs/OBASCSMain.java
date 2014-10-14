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

package edu.usf.cutr.obascs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.io.ConfigFileGenerator;
import edu.usf.cutr.obascs.io.FileConsolidator;
import edu.usf.cutr.obascs.io.FileUtil;
import edu.usf.cutr.obascs.io.SpreadSheetReader;
import edu.usf.cutr.obascs.utils.CommandLineUtil;
import edu.usf.cutr.obascs.utils.Logger;
import edu.usf.cutr.obascs.utils.URLUtil;


public class OBASCSMain {

    public static void main(String[] args) {

	String logLevel = null;
	String outputFilePath = null;
	String inputFilePath = null;
	String spreadSheetId = null;
	Logger logger = Logger.getInstance();
	

	Options options = CommandLineUtil.createCommandLineOptions();
	CommandLineParser parser = new BasicParser();
	CommandLine cmd;
	try {
	    cmd = parser.parse(options, args);
	    logLevel = CommandLineUtil.getLogLevel(cmd);
	    logger.setup(logLevel);
	    outputFilePath = CommandLineUtil.getOutputPath(cmd);
	    spreadSheetId = CommandLineUtil.getSpreadSheetId(cmd);
	    
	    inputFilePath = CommandLineUtil.getInputPath(cmd);
	} catch (ParseException e1) {
	    logger.logError(e1);
	} catch (FileNotFoundException e) {
	    logger.logError(e);
	}
	
	Map<String, String> agencyMap = null;
	try {
	    agencyMap = FileUtil.readAgencyInformantions(inputFilePath);
	} catch (IOException e1) {
	    logger.logError(e1);
	}

	logger.log("Consolidation started...");
	logger.log("Trying as public url");

	ListFeed listFeed = null;
	Boolean authRequired = false;
	try {
	    listFeed = SpreadSheetReader.readPublicSpreadSheet(spreadSheetId);
	} catch (IOException e) {
	    logger.logError(e);
	} catch (ServiceException e) {
	    logger.log("Authentication Required");
	    authRequired = true;
	}

	if (listFeed == null && authRequired == true) {
	    Scanner scanner = new Scanner(System.in);
	    String userName, password;
	    logger.log("UserName:");
	    userName = scanner.nextLine();
	    logger.log("Password:");
	    password = scanner.nextLine();
	    scanner.close();

	    try {
		listFeed = SpreadSheetReader.readPrivateSpreadSheet(userName, password, spreadSheetId);
	    } catch (IOException e) {
		logger.logError(e);
	    } catch (ServiceException e) {
		logger.logError(e);
	    }
	}

	if (listFeed != null) {
	    //Creating consolidated stops
	    String consolidatedString = FileConsolidator.consolidateFile(listFeed, agencyMap);
	    try {
		FileUtil.writeToFile(consolidatedString, outputFilePath);
	    } catch (FileNotFoundException e) {
		logger.logError(e);
	    }
	    
	    //Creating sample stop consolidation script config file
	    try {
		String path = ClassLoader.getSystemClassLoader().getResource(GeneralConstants.CONSOLIDATION_SCRIPT_CONFIG_FILE).getPath();
		String configXml = FileUtil.readFile(URLUtil.trimSpace(path));
		configXml = ConfigFileGenerator.generateStopConsolidationScriptConfigFile(configXml, agencyMap);
		path = URLUtil.trimPath(outputFilePath) + "/" + GeneralConstants.CONSOLIDATION_SCRIPT_CONFIG_FILE;
		FileUtil.writeToFile(configXml, path);
	    } catch (IOException e) {
		logger.logError(e);
	    }
	    
	  //Creating sample real-time config file
	    try {
		String path = ClassLoader.getSystemClassLoader().getResource(GeneralConstants.SAMPLE_REALTIME_CONFIG_FILE).getPath();
		String configXml = FileUtil.readFile(URLUtil.trimSpace(path));
		configXml = ConfigFileGenerator.generateSampleRealTimeConfigFile(configXml, agencyMap);
		path = URLUtil.trimPath(outputFilePath) + "/" + GeneralConstants.SAMPLE_REALTIME_CONFIG_FILE;
		FileUtil.writeToFile(configXml, path);
	    } catch (IOException e) {
		logger.logError(e);
	    }
	} else {
	    logger.logError("Cannot write files");
	}

	logger.log("Consolidation finished...");

    }
}
