package edu.mum.mscs.tools.remotereport.xmlrpc;

import java.util.*;
import org.apache.xmlrpc.*;

public class JavaClient {
	
	public static void main(String[] args) {
		try {

			XmlRpcClient server = new XmlRpcClient("http://127.0.0.1:1234/RPC2");
			Vector params = new Vector();
			params.addElement(new Integer(30));
			params.addElement(new Integer(3));

			Object result = server.execute("sample.prod", params);

			int sum = ((Integer) result).intValue();
			System.out.println("The sum is: " + sum);

		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
	}
	
}