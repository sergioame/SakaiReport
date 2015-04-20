:: ---------------------------------------------------------------------------
:  run series of Sakai reports from Excel
:
:  Notes:
:     * You must be logged in to Sakai before running this script!  (??)
:     * Run in a directory without any other .pdf files (it will archive and delete them!)
:
:  Note: /e// is the marker that this is run from the console
:      : substitute slashes (/) for spaces, but slashes cannot exist in the course names
:: ---------------------------------------------------------------------------
:  should probably convert this whole thing to powershell (or python?) ... sometime.
:
@echo off
setlocal EnableDelayedExpansion
:: 
: Get these more dynamically??

set courses=%*
set /a "count = 1"

for %%C in (%courses%) do (
	if !count! == 1 (
		set destEmail=%%C
	) else (
		start/W excel "C:\sakaitools\Sakai-courseReport.xlsm" /e//%%C
	)
	set /a "count+=1"
)

:: -------------------------
:  zip up all reports
set dateStamp=%date:~10,4%%date:~4,2%%date:~7,2%
set header=Sakai-Reports
set reports=%header%-%dateStamp%.zip


:  (or; powershell:  write-zip ?)
:  set zip="C:\Program Files\7-Zip\7z.exe"
set zip="C:\tools\7z.exe"
%zip% a C:\sakaitools\%reports% C:\sakaitools\*.txt
%zip% a C:\sakaitools\%reports% C:\sakaitools\*.pdf

:: -------------------------
:  and send to the DE office

echo "Sending mail: %reports%"
powershell.exe -NoProfile -NonInteractive -Command "& {           " ^
   " $dest='%destEmail%'          ;" ^
   " $source='sakai@myschool.edu'   ;" ^
   " $subject='%reports%'         ;" ^
   " $file='%reports%'            ;" ^
   " $server='smtp.myschool.edu'    ;" ^
   " send-mailmessage -To $dest -From $source " ^
   "                  -Subject %reports% -Attachments C:\sakaitools\%reports% -SmtpServer $server }"
                   
:: -------------------------
:  cleanup...  (archive directory should already exist)
: del %header%*.pdf
del C:\sakaitools\*.txt
del C:\sakaitools\*.pdf
move C:\sakaitools\%reports% C:\sakaitools\archive
