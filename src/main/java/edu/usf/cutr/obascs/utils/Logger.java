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

import edu.usf.cutr.obascs.constants.GeneralConstants;

public class Logger {
    private String level;
    private static Logger instance = null; 
    
    private Logger(){
	
    }
    
    public static Logger getInstance(){
	if (instance == null) {
	    instance = new Logger();
	}
	return instance;
    }
    
    public Logger setup(String level){
	this.level = level;
	return instance;
    }

    public void logError(Exception e){
	if (GeneralConstants.LOG_LEVEL_DEBUG.equals(level)) {
	    e.printStackTrace();
	}else {
	    System.err.println(e.getMessage());
	}
    }

    public void logError(String str){
	System.err.println(str);
    }
    
    public void log(Object o){
	System.out.println(o);
    }
    
    public void debug(Object o){
	if (GeneralConstants.LOG_LEVEL_DEBUG.equals(level)) {
	    System.out.println(o);
	}
    }
}
