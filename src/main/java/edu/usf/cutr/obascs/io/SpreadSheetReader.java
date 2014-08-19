package edu.usf.cutr.obascs.io;

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.ServiceException;

import edu.usf.cutr.obascs.constants.GeneralConstants;

public class SpreadSheetReader {

    private String userName;
    private String password;
    private String spreadSheetUrl;
    
    public SpreadSheetReader(String userName, String password, String spreadSheetUrl) {
	this.userName = userName;
	this.password = password;
	this.spreadSheetUrl = spreadSheetUrl;
    }

    public ListFeed readSpreadSheet() throws IOException, ServiceException {
	/** Our view of Google Spreadsheets as an authenticated Google user. */
	SpreadsheetService service = new SpreadsheetService("Test");

	// Login and prompt the user to pick a sheet to use.
	service.setUserCredentials(userName, password);
	service.setConnectTimeout(GeneralConstants.GDATA_TIMEOUT);
	service.setReadTimeout(GeneralConstants.GDATA_TIMEOUT);

	// Load sheet
	URL metafeedUrl = new URL(spreadSheetUrl);
	SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl, SpreadsheetEntry.class);
	URL listFeedUrl = ((WorksheetEntry) spreadsheet.getWorksheets().get(0)).getListFeedUrl();

	ListFeed feed = (ListFeed) service.getFeed(listFeedUrl, ListFeed.class);
	
	return feed;
    }

    public String getUserName() {
	return userName;
    }

    public String getPassword() {
	return password;
    }

    public String getSpreadSheetUrl() {
	return spreadSheetUrl;
    }

}
