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

import edu.usf.cutr.obascs.constants.GeneralConstants;

/**
 * @author cagryInside
 *
 */
public class ConfigFileGenerator {

    public static String generateStopConsolidationScriptConfigFile(String configXml, Map<String, String> agencyMap){
	StringBuilder bundleNamesBuilder  = new StringBuilder();
	StringBuilder gtfsBeansBuilder = new StringBuilder();
	
	for (Map.Entry<String, String> agency : agencyMap.entrySet()) {
	    bundleNamesBuilder.append("<ref bean=\"gtfs_").append(agency.getValue()).append("\" />").append(SystemUtils.LINE_SEPARATOR);
	    
	    gtfsBeansBuilder.append("<bean id=\"gtfs-").append(agency.getValue()).append("\" class=\"org.onebusaway.transit_data_federation.bundle.model.GtfsBundle\">");
	    gtfsBeansBuilder.append(SystemUtils.LINE_SEPARATOR);
	    gtfsBeansBuilder.append("<property name=\"path\" value=\"${bundle.root}/").append(agency.getValue()).append("/final\" />");
	    gtfsBeansBuilder.append(SystemUtils.LINE_SEPARATOR);
	    gtfsBeansBuilder.append("<property name=\"defaultAgencyId\" value=\"").append(agency.getKey()).append("\" />");
	    gtfsBeansBuilder.append(SystemUtils.LINE_SEPARATOR);
	    gtfsBeansBuilder.append(" </bean>");
	}
	
	configXml = StringUtils.replace(configXml, "${bundleNames}", bundleNamesBuilder.toString());
	configXml = StringUtils.replace(configXml, "${gtfsBeans}", gtfsBeansBuilder.toString());
	return configXml;
    }
    
    public static String generateSampleRealTimeConfigFile(String configXml, Map<String, String> agencyMap){
	StringBuilder bundleNamesBuilder  = new StringBuilder();
	for (Map.Entry<String, String> agency : agencyMap.entrySet()) {
	    bundleNamesBuilder.append("<bean class=\"org.onebusaway.transit_data_federation.impl.realtime.gtfs_realtime.GtfsRealtimeSource\">");
	    bundleNamesBuilder.append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<property name=\"tripUpdatesUrl\" value=\"").append(GeneralConstants.SAMPLE_REALTIME_CONFIG_TRIP_UPDATES_URL);
	    bundleNamesBuilder.append("\" />").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<property name=\"vehiclePositionsUrl\" value=\"").append(GeneralConstants.SAMPLE_REALTIME_CONFIG_VEHICLE_POS_URL);
	    bundleNamesBuilder.append("\" />").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<property name=\"alertsUrl\" value=\"").append(GeneralConstants.SAMPLE_REALTIME_CONFIG_ALERTS_URL);
	    bundleNamesBuilder.append("\" />").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<property name=\"refreshInterval\" value=\"15\" />").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<property name=\"agencyIds\">").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<list>").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("<value>").append(agency.getKey()).append("</value>").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("</list>").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("</property>").append(SystemUtils.LINE_SEPARATOR);
	    bundleNamesBuilder.append("</bean>").append(SystemUtils.LINE_SEPARATOR);
	}
	
	configXml = StringUtils.replace(configXml, "${beanNames}", bundleNamesBuilder.toString());
	return configXml;
    }
}
