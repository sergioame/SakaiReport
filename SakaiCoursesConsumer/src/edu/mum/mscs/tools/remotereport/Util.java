package edu.mum.mscs.tools.remotereport;

import java.util.ResourceBundle;

public class Util {
	
	public static String rrQNAME;
	
	public static String loginQNAME;
	
	public static String usr;
	
	public static String pwd;
	
    static {
        ResourceBundle labels = ResourceBundle.getBundle("edu.mum.mscs.tools.remotereport.Util");
        rrQNAME = labels.getString("rrQNAME");
        loginQNAME = labels.getString("loginQNAME");
        usr = labels.getString("usr");
        pwd = labels.getString("pwd");
    }

}
