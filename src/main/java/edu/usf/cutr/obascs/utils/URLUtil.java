package edu.usf.cutr.obascs.utils;

public class URLUtil {

    public static String createPrivateSpreadSheetUrl(String spreadSheetId){
	return "https://spreadsheets.google.com/feeds/spreadsheets/" + spreadSheetId;
    }

    public static String createPublicSpreadSheetUrl(String spreadSheetId){
	return "https://spreadsheets.google.com/feeds/list/" + spreadSheetId + "/default/public/values";
    }
}
