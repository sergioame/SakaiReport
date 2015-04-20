set dateStamp=%date:~10,4%%date:~4,2%%date:~7,2%
set timeStamp=%time:~0,2%%time:~3,2%%time:~6,2%
set header=SakaiReport
move C:\sakaitools\XMLRPCRemoteReport\%header%*.log C:\sakaitools\XMLRPCRemoteReport\%header%-%dateStamp%-%timeStamp%.bak
java -cp .;lib\commons-codec-1.9.jar;lib\xmlrpc-2.0-beta.jar edu.mum.mscs.tools.remotereport.xmlrpc.JavaServer 1234
