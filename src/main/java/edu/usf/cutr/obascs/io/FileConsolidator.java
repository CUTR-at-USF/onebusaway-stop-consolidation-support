package edu.usf.cutr.obascs.io;

import org.apache.commons.lang3.StringUtils;

import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;

import edu.usf.cutr.obascs.constants.GeneralConstants;
import edu.usf.cutr.obascs.constants.StringConstants;

public class FileConsolidator {

    public static String consolidateFile(ListFeed feed) {

	StringBuilder sb = new StringBuilder("#summary HART consolidated stops");
	sb.append(StringConstants.NEW_LINE).append(StringConstants.NEW_LINE);
	sb.append(StringConstants.FILE_START).append(StringConstants.NEW_LINE).append(StringConstants.NEW_LINE);

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
	    sb.append(StringConstants.NEW_LINE);
	}

	sb.append(StringConstants.NEW_LINE).append(StringConstants.FILE_END);
	
	return sb.toString();
    }
}
