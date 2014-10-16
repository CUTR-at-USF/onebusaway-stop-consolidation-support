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

package edu.usf.cutr.obascs.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.constants.StringConstants;

public class FileUtil {

    public static void writeToFile(String text, String path) throws FileNotFoundException {
	File file = new File(path);
	PrintWriter printWriter = new PrintWriter(file);
	printWriter.println(text);
	printWriter.close();
    }

    public static Map<String, String> readAgencyInformantions(String path) throws IOException {

	Map<String, String> agencyMap = new HashMap<String, String>();

	BufferedReader br = new BufferedReader(new FileReader(path));
	try {
	    String line = br.readLine();

	    while (line != null) {
		if (StringUtils.isNotBlank(line) && line.contains(GeneralConstants.AGENCY_MAP_IDENTIFIER) == false) {
		    try {
			String[] split = line.split(StringConstants.COMMA);
			String agency = null;
			if (StringUtils.isNotBlank(split[0])) {
			    agency = split[0];
			} else {
			    agency = split[1];
			}
			agencyMap.put(agency, split[2]);
		    } catch (Exception e) {
			throw new IOException("Wrong line format. Line should be comma seperated.");
		    }
		}
		line = br.readLine();
	    }

	} finally {
	    br.close();
	}

	return agencyMap;
    }

    public static String readFile(String path) throws IOException {
	byte[] encoded = Files.readAllBytes(Paths.get(path));
	return new String(encoded, "UTF-8");
    }
}
