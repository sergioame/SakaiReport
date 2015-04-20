package edu.mum.mscs.tools.remotereport.xmlrpc;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.xmlrpc.*;

public class JavaServer {

	private static final Logger log = Logger.getLogger("SakaiReport");
        
        private static FileHandler fh;
        
        static {
            try {  
                fh = new FileHandler("SakaiReport.log");  
                log.addHandler(fh);
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);  
            } catch (SecurityException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }

	public int runtimeExec(String courseList) {
		int exitCode = 0;
                logInfo(courseList);
		String commandLine = "C:\\sakaitools\\AutoReportsMultiple.bat " + courseList;
		try {
			System.out.println("*** -> " + commandLine + " <- ***");
			Process p = Runtime.getRuntime().exec(commandLine);
			p.getOutputStream().close();
			exitCode = p.waitFor();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exitCode;
	}
        
        public void logInfo(String courseList) {
            String[] arguments =  courseList.split(" ");
            StringBuffer info = new StringBuffer();
            info.append("Date/time: ").append(new Date()).append(". ");
            info.append("Email ID: ").append(arguments[0]).append(". ");
            info.append("Course(s) requested: ");
            for (int i = 1; i < arguments.length; i++) {
                String f = arguments[i];
                info.append(arguments[i].replaceAll("/" , "")).append(", ");
            }
            info.delete(info.length() - 2, info.length());
            info.append(".");
            log.info(info.toString());
        }

	public static void main(String[] args) {
		int port = 0;
		if (args.length == 0) {
			System.out.println("The port number should be specified as an argument.");
		} else {
			port = Integer.parseInt(args[0]);
			try {
				System.out.println("Attempting to start XML-RPC Server for Remote Report Application.......");
				WebServer server = new WebServer(port);
				server.addHandler("sample", new JavaServer());
				server.start();
				System.out.println("Started successfully.");
				System.out.println("Accepting requests. (Halt program to stop.)");
			} catch (Exception exception) {
				System.err.println("JavaServer: " + exception);
			}
		}
	}
	
}