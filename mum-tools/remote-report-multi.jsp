<%@ page import="edu.mum.mscs.tools.remotereport.RemoteReportCoursesServiceLocator" %>
<%@ page import="edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType" %>
<%@ page import="edu.mum.mscs.tools.remotereport.SakaiLoginServiceLocator" %>
<%@ page import="edu.mum.mscs.tools.remotereport.SakaiLogin_PortType" %>
<%@ page import="edu.mum.mscs.tools.remotereport.Util" %>
<%@ page import="java.rmi.RemoteException" %>
<%@ page import="javax.xml.rpc.ServiceException" %>
<%--
    Remote report execution:
	Faculty can request reports to be sent to their email address. What we need to create is a web page that accepts: 
	course number, period, email address. When executed, web page calls a .BAT file to execute an Excel macro. 
	Excel macro queries data from Sakai learning system, creates Excel reports, zips and emails them.
--%>
<html>
<head>
<title>Sakai Report</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" href="main.css" type="text/css">
<link type="text/css" href="/library/js/jquery-ui-latest/css/smoothness/jquery-ui.css" rel="stylesheet" media="all"/>
<link href="/library/skin/tool_base.css" type="text/css" rel="stylesheet" media="all" />
<link href="/library/skin/default/tool.css" type="text/css" rel="stylesheet" media="all" />
<script type="text/javascript" src="/library/js/jquery-latest.min.js"></script>
<script type="text/javascript" src="/library/js/jquery-ui-latest/js/jquery-ui.min.js"></script>
<script type="text/javascript">

	var courseList = new Array();
	var checkMembershipFinished = false;
	
	function initCourseList() {
		for (var i = 0; i < document.forms["rrForm"]["course.period"].options.length; i++) {
			courseList.push(document.forms["rrForm"]["course.period"].options[i]);
		}
	}

	function clearFilter() {
		document.forms["rrForm"]["filter"].value = "";
		// Clean the select box and assign all the original values
		document.forms["rrForm"]["course.period"].options.length = 0;
		for (var i = 0; i < courseList.length; i++) {
			document.forms["rrForm"]["course.period"].options.add(courseList[i]);
		}		
	}
	
	function filterList() {
		filteredCourseList = new Array();
		if (document.forms["rrForm"]["filter"].value != "") {
			var re = new RegExp(document.forms["rrForm"]["filter"].value, 'i');
			// Find all that match
			for (var i = 0; i < courseList.length; i++) {
				if (courseList[i].value.match(re)) {
					filteredCourseList.push(new Option(courseList[i].value, courseList[i].value));
				}
			}
		}
		if (filteredCourseList.length > 0) {
			// Clean the select box and assign all the values that match the pattern
			document.forms["rrForm"]["course.period"].options.length = 0;
			for (var i = 0; i < filteredCourseList.length; i++) {
				document.forms["rrForm"]["course.period"].options.add(filteredCourseList[i]);
			}
		} else {
			alert("The typed string does not match any option. Please try another");
		}
	}
	
	function validateForm() {
		// Checking at least one of the courses is selected
		var oneSelected = false;
		var i;
		for (var i = 0; i < document.forms["rrForm"]["course.period"].options.length; i++) {
			if (document.forms["rrForm"]["course.period"].options[i].selected) {
				oneSelected = true;
			}
		}
		if (!oneSelected) {
			alert("Please select at least one course");
			return false;
		}		
		// Checking for a valid email address
		var x = document.forms["rrForm"]["email"].value;
		var atpos=x.indexOf("@");
		var dotpos=x.lastIndexOf(".");
		if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
			alert("Please enter a valid e-mail address");
			return false;
		}
		//document.forms["rrForm"].submit();
	}
	
	function checkMembership() {
		$('#dis_SitesListDetail').dialog({
			autoOpen: false,
			modal: true,
			width: '20%',
			minWidth: 300,
			maxHeight: 200
		});
		var allowed = false;
		jQuery.ajax({
			url: "/direct/user/current.json",
			async: false,
			success: function(data) {
				document.forms["rrForm"]["email"].value = data.email;
				if (data.id == "") {
					$('#dis_SitesListDetail').html("Please log in to Sakai first, \n<br>then reload this page.");
					$('#dis_SitesListDetail').dialog('option', 'title', 'Alert!');
					$('#dis_SitesListDetail').dialog('open');
				} else {
					jQuery.ajax({
						url: "/direct/membership.json?user=" + data.id,
						async: false,
						success: function(data) {
							var isAdmin = false;
							for (i = 0; i < data.membership_collection.length; i++) {
								if (data.membership_collection[i].memberRole == 'admin') {
									allowed = true;
									isAdmin = true;
									break;
								}
							}
							if (!isAdmin) {
								document.forms["rrForm"]["course.period"].options.length = 0;
								for (i = 0; i < data.membership_collection.length; i++) {
									if (data.membership_collection[i].memberRole == 'Instructor') {
										allowed = true;
										jQuery.ajax({
											url: "/direct" + data.membership_collection[i].locationReference + ".json",
											async: false,
											success: function(data) {
												document.forms["rrForm"]["course.period"].options.add(new Option(data.title, data.title));
											},
											error: function() {
												alert("Something went wrong while checking the Sakai Site web service.\n<br>Please check with the Administrator.");
											}
										});
									}
								}
							}
							if (!allowed) {
								$('#dis_SitesListDetail').html("Your Sakai role is neither admin or Instructor.\n<br>Please check with the Administrator.");
								$('#dis_SitesListDetail').dialog('option', 'title', 'Alert!');
								$('#dis_SitesListDetail').dialog('open');
							} else {
								// Show the course list, if any courses!
								initCourseList();
								if (courseList.length > 0) {
									$('#mainPanel').show();
								}
							}
						},
						error: function() {
							alert("Something went wrong while checking Sakai membership.\n<br>Please check that you are logged in to Sakai.");
						}
					});
				}
			},
			error: function() {
				alert("Something went wrong while checking the current Sakai user.\n<br>Please check that you are logged in to Sakai.");
			}
		});
		checkMembershipFinished = true;
	}

</script>
</head>
<body onload="checkMembership();">
	<div id="dis_SitesListDetail">
	</div>
	<div id="mainPanel" style="display:none">	
		<div align="center"><center>
			<div id="formText" style="display: block">
				<br>
				<p style="font-family:arial;color:black;font-size:16px;"><b>Use this page to obtain the Sakai reports of your courses.</b></p>
	<% 	
		String[] courseList = getSitesTitle();
		if  (courseList != null && courseList.length > 0) { %>
				<br>
				<br>
			<form name="rrForm" method="post" action="remote-report-exec-multi.jsp" onsubmit="return validateForm();">
			<table border="0">
			  <tr>
					<td colspan="2">
						<p style="font-family:arial;color:black;font-size:16px;" align="left">Please select the courses from which you want the Sakai reports, 
						<br>type your email address and submit the form:</p>
						<p>&nbsp;</p>
					</td>
			  </tr>
			  <tr>
				<td><p style="font-family:arial;color:black;font-size:16px;">Filter:</p></td>
				<td><input type="text" name="filter" value="" size="25">
					<input type="button" value="Filter" onclick="filterList();">
					<input type="button" value="Clear" onclick="clearFilter();">
					&nbsp;<img src="icon-question-mark.png" title="Type any string that matches your search and click Filter"></td>
			  </tr>
			  <tr>
				<td><p style="font-family:arial;color:black;font-size:16px;">Course/Period:</p></td>
				<td>
					<select multiple name="course.period" size="10">
					<% 	
						for (String course : courseList) { 
							%>
							<option value="<%= course %>"><%= course %></option>
					<% 	} %>
					</select>
					<img src="icon-question-mark.png" title="You can select as many courses as you want by holding the Ctrl key and then clicking on them.">
				</td>
			  </tr>
			  <tr>
				<td><p style="font-family:arial;color:black;font-size:16px;">Your e-mail address:</p></td>
				<td><input type="text" name="email" value="" size="25">&nbsp;<img src="icon-question-mark.png" title="Please type the email address in which you want to receive the reports"></td>
			  </tr>
			</table>
			<p ><input type="submit" value="Submit" name="b1"></p>
			</form>
	<% } else { %>
			<script>
				if (checkMembershipFinished == true) {
					alert('The list of courses is null or empty.\nPlease check with the Administrator.');
				}
			</script>
	<% } %>
			</div>
		</center></div>
	</div>
</body>
</html>
<%!

	private String[] getSitesTitle() {
		String[] courseList = null;
		try {
			RemoteReportCoursesServiceLocator remoteReportService = new RemoteReportCoursesServiceLocator();
			RemoteReportCourses_PortType remoteReportPortType = remoteReportService.getRemoteReportCourses();
			courseList = remoteReportPortType.getAllSitesTitle(null);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseList;
	}
	
%>