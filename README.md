onebusaway-stop-consolidation-support [![Build Status](https://travis-ci.org/CUTR-at-USF/onebusaway-stop-consolidation-support.svg?branch=master)](https://travis-ci.org/CUTR-at-USF/onebusaway-stop-consolidation-support)
===========================
A java console application to consolidate shared bus stops from google spreadsheets.

##Run parameters

```
-d	print debug message
-o<file_path>	Output file url with name (default current directory)
-id<Spreadsheet_id> Spreadsheet id 
```
###Example
```
-o /path/to/Output.txt -d -id 1CmdPkMPo3Bgh4qfwf6BBZFMPBCJ9gC1WczUA8RU9vxw
```

##Setup
Please see our [setup guide](https://github.com/CUTR-at-USF/onebusaway-stop-consolidation-support/wiki) for details, including spreadsheet format, realtime configuration.

##Default SpreadSheet URL
```
	https://docs.google.com/a/mail.usf.edu/spreadsheets/d/1CmdPkMPo3Bgh4qfwf6BBZFMPBCJ9gC1WczUA8RU9vxw/edit#gid=527691915
```

##HART Consolidation script Input
To use HART consolidation script, the output of this application needs to submit this URL:
```
	https://github.com/camsys/onebusaway-application-modules/wiki/HartStopConsolidation
```

##Notes
-> There shouldn't be any empty rows in spreadsheet.

-> Jar file path: OBASCS/src/main/files/OBASCS.jar

