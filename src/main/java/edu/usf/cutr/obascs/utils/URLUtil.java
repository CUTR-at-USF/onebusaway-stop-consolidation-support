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

package edu.usf.cutr.obascs.utils;

public class URLUtil {

    public static String createPrivateSpreadSheetUrl(String spreadSheetId){
	return "https://spreadsheets.google.com/feeds/spreadsheets/" + spreadSheetId;
    }

    public static String createPublicSpreadSheetUrl(String spreadSheetId){
	return "https://spreadsheets.google.com/feeds/list/" + spreadSheetId + "/default/public/values";
    }
    
    public static String trimSpace(String url){
	if (url != null) {
	    url = url.replaceAll("%20", "\\ ");
	}
	return url;
    }
    
    public static String trimPath(String url){
	return url.replaceAll("/[^/]*$", "");
    }
}
