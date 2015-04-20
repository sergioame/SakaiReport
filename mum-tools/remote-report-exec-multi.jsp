<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Vector" %>
<%@ page import="org.apache.xmlrpc.XmlRpcClient" %>
<%
	String[] coursePeriodList = request.getParameterValues("course.period");
	String email = request.getParameter("email");
	StringBuffer coursePeriodCmd = new StringBuffer();
	coursePeriodCmd.append(request.getParameter("email")).append(" ");
	if (coursePeriodList == null) {
		response.sendRedirect("remote-report-multi.jsp");
	} else {
		for (String coursePeriod : coursePeriodList) {
			if (coursePeriod.trim().contains(" ")) {
				coursePeriodCmd.append(coursePeriod.trim().replaceAll(" " , "/")).append(" ");
			} else {
				coursePeriodCmd.append(coursePeriod.trim()).append(" ");
			}
		}
		int exitCode = doRPC(coursePeriodCmd.toString());
	}
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sakai Reports</title>
		<link rel="stylesheet" href="main.css" type="text/css">
	</head>
    <body>
		<div align="center"><center>
			<div id="formText" style="display: block">
				<br>
				<p><b>A report for the specified courses will be sent to <%= email %>.</b></p>
				<br>
				<br>
			</div>
		</center></div>
	</body>
</html>
<%!
	
	/**
	 * Does a Remote Procedure Call to a Windows Server that executes the Excel macro.
	 */
	private synchronized int doRPC(String courseList) throws IOException, InterruptedException {
		int exitCode = 0;
		try {
			XmlRpcClient server = new XmlRpcClient("http://127.0.0.1:1234/RPC2");
			Vector params = new Vector();
			params.addElement(courseList);
			Object result = server.execute("sample.runtimeExec", params);
			exitCode = ((Integer) result).intValue();
			System.out.println("The result is: " + exitCode);
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception);
		}
		return exitCode;
	}
	
%>