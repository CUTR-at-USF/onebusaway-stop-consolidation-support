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

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.utils.URLUtil;

public class SpreadSheetReader {

    public static ListFeed readPrivateSpreadSheet(String userName, String password, String spreadSheetId) throws IOException, ServiceException {
	/** Our view of Google Spreadsheets as an authenticated Google user. */
	SpreadsheetService service = new SpreadsheetService("OBASCS");

	// Login and prompt the user to pick a sheet to use.
	service.setUserCredentials(userName, password);
	service.setConnectTimeout(GeneralConstants.GDATA_TIMEOUT);
	service.setReadTimeout(GeneralConstants.GDATA_TIMEOUT);

	// Load sheet
	String spreadSheetUrl = URLUtil.createPrivateSpreadSheetUrl(spreadSheetId);
	URL metafeedUrl = new URL(spreadSheetUrl);
	SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl, SpreadsheetEntry.class);
	URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();

	ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);

	return feed;
    }

    public static ListFeed readPublicSpreadSheet(String spreadSheetId) throws IOException, ServiceException {
	/** Our view of Google Spreadsheets as an authenticated Google user. */
	SpreadsheetService service = new SpreadsheetService("OBASCS");
	String spreadSheetUrl = URLUtil.createPublicSpreadSheetUrl(spreadSheetId);
	URL url = new URL(spreadSheetUrl);
	ListFeed feed = service.getFeed(url, ListFeed.class);
	return feed;
    }
}
