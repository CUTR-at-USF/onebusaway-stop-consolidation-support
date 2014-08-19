package edu.usf.cutr.obascs.utils;

import edu.usf.cutr.obascs.constants.GeneralConstants;

public class Logger {

    public static void logError(Exception e, String level){
	if (GeneralConstants.LOG_LEVEL_DEBUG.equals(level)) {
	    e.printStackTrace();
	}else {
	    System.err.println(e.getMessage());
	}
    }

    public static void logError(Exception e){
	logError(e, null);
    }

    public static void logError(String str){
	System.err.println(str);
    }
    
    public static void log(Object o){
	System.out.println(o);
    }
}
