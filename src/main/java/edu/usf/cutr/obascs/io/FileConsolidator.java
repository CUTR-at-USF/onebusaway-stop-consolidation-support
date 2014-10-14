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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.constants.StringConstants;
import edu.usf.cutr.obascs.utils.Logger;

public class FileConsolidator {

    public static String consolidateFile(ListFeed feed, Map<String, String> agencyMap) {
	Logger logger = Logger.getInstance();
	logger.debug("Merging files started...");
	logger.debug("Total Entries : " + feed.getEntries().size());

	StringBuilder sb = new StringBuilder("#summary HART consolidated stops");

	sb.append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);
	sb.append(StringConstants.FILE_START).append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);

	for (ListEntry entry : feed.getEntries()) {

	    /*
	     *  lineCounter: to separate columns by tab character 
	     */
	    Integer lineCounter = 0;
	    for (Map.Entry<String, String> agency : agencyMap.entrySet()) {
		String stopId = StringUtils.trim(entry.getCustomElements().getValue(StringUtils.deleteWhitespace(agency.getKey())));
		
		if (lineCounter == 0) {
		    if (StringUtils.isNotBlank(stopId)) {
			sb.append("\"").append(agency.getKey()).append(StringConstants.UNDERSCORE).append(stopId).append("\"").append(StringConstants.TAB);
		    }
		}else if (lineCounter == agencyMap.size() - 2) {
		    if (StringUtils.isNotBlank(stopId)) {
			sb.append("\"").append(agency.getKey()).append(StringConstants.UNDERSCORE).append(stopId).append("\"");
		    }
		}else {
		    if (StringUtils.isNotBlank(stopId)) {
			sb.append("\"").append(agency.getKey()).append(StringConstants.UNDERSCORE).append(stopId).append("\"").append(StringConstants.TAB);
		    } else {
			sb.append(StringConstants.TAB);
		    }
		}
		lineCounter++;
	    }
	    sb.append(SystemUtils.LINE_SEPARATOR);
	    
	}

	sb.append(SystemUtils.LINE_SEPARATOR).append(StringConstants.FILE_END);
	logger.debug("Merge finished");

	return sb.toString();
    }
}
