package edu.mum.mscs.tools.remotereport;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.AxisFault;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SakaiLoginServiceLocator sakaiLoginService = new SakaiLoginServiceLocator();
			SakaiLogin_PortType sakaiLoginPortType = sakaiLoginService.getSakaiLogin();
			String sessionid = sakaiLoginPortType.login(Util.usr, Util.pwd);
			RemoteReportCoursesServiceLocator remoteReportService = new RemoteReportCoursesServiceLocator();
			RemoteReportCourses_PortType remoteReportPortType = remoteReportService.getRemoteReportCourses();
			String[] CSDECourseList = remoteReportPortType.getAllSitesTitle(null);
			for (String course : CSDECourseList) {
				System.out.println("* " + course);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
