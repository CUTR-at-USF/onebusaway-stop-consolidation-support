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

package edu.usf.cutr.obascs.constants;

public class GeneralConstants {

    public static int GDATA_TIMEOUT = 10* 1000;
    
    public static final String AGENCY_ID_HART = "Hillsborough Area Regional Transit";
    public static final String AGENCY_ID_USF = "USF BullRunner";
    public static final String AGENCY_ID_PSTA = "PSTA";

    public static final String TAG_HART = "hartstopid";
    public static final String TAG_BULLRUNNER = "bullrunnerid";
    public static final String TAG_PSTA = "pstastopid";
    
    public static final String DEFAULT_FILE_LOCATION = "Output.txt";

    public static final String LOG_LEVEL_DEBUG = "-d";
    
    public static final String CL_OPTION_DEBUG = "d";
    public static final String CL_OPTION_OUTPUT_PATH = "o";
    public static final String CL_OPTION_INPUT_PATH = "i";
    public static final String CL_OPTION_SPREADSHEET_ID = "id";
    
    public static final String AGENCY_MAP_IDENTIFIER = "agency_id";
    public static final String CONSOLIDATION_SCRIPT_CONFIG_FILE = "transit-data-bundle-gtfs.xml";
    public static final String SAMPLE_REALTIME_CONFIG_FILE = "sample-data-sources.xml";

    public static final String SAMPLE_REALTIME_CONFIG_TRIP_UPDATES_URL = "http://example.updates.com/trip-updates";
    public static final String SAMPLE_REALTIME_CONFIG_VEHICLE_POS_URL = "http://example.updates.com/vehicle-positions";
    public static final String SAMPLE_REALTIME_CONFIG_ALERTS_URL = "http://example.updates.com/alerts";
    
    
}
