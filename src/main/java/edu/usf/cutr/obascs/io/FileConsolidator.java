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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.constants.StringConstants;

public class FileConsolidator {

    public static String consolidateFile(ListFeed feed) {

	StringBuilder sb = new StringBuilder("#summary HART consolidated stops");
	
	sb.append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);
	sb.append(StringConstants.FILE_START).append(SystemUtils.LINE_SEPARATOR).append(SystemUtils.LINE_SEPARATOR);

	for (ListEntry entry : feed.getEntries()) {
	    String hartBusId = entry.getCustomElements().getValue(GeneralConstants.TAG_HART);
	    String bullrunnerBusId = entry.getCustomElements().getValue(GeneralConstants.TAG_BULLRUNNER);
	    String pstaBusId = entry.getCustomElements().getValue(GeneralConstants.TAG_PSTA);

	    if (StringUtils.isNotBlank(hartBusId)) {
		sb.append(GeneralConstants.AGENCY_ID_HART).append(StringConstants.UNDERSCORE).append(hartBusId).append(StringConstants.TAB);
	    }

	    if (StringUtils.isNotBlank(bullrunnerBusId)) {
		sb.append(GeneralConstants.AGENCY_ID_USF).append(StringConstants.UNDERSCORE).append(bullrunnerBusId).append(StringConstants.TAB);
	    } else {
		sb.append(StringConstants.TAB);
	    }

	    if (StringUtils.isNotBlank(pstaBusId)) {
		sb.append(GeneralConstants.AGENCY_ID_PSTA).append(StringConstants.UNDERSCORE).append(pstaBusId);
	    }
	    sb.append(SystemUtils.LINE_SEPARATOR);
	}

	sb.append(SystemUtils.LINE_SEPARATOR).append(StringConstants.FILE_END);
	
	return sb.toString();
    }
}
