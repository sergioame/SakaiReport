# SakaiReport
Sakai Report of course activity, student forum usage, site visits and grade book.

Sakai Report by <a href='http://mscs.mum.edu/'>Maharishi University of Management</a> is an application that allows faculty to obtain reports of their courses in their email addresses based on the information that is already in Sakai LMS.

<strong>What’s Included</strong>
<hr>
In this application many technologies work together, namely:
<ul>
<li>JSP: Homepage of this application.</li>
<li>JQuery / Ajax web service request: To access Sakai Current User and Membership RESTful web services.</li>
<li>Axis Web Service: This is to connect the home page with a SOAP web service that brings the entire list of courses.</li>
<li>XMLRPC: Since the homepage is located in a Linux server and the Excel macro has to be executed in a Windows server, it is necessary to do an RPC call from the Linux server to the Windows server to execute the macro.</li>
<li>Batch file: This file specifies the process that coordinates the calls to the Excel Macro, the compression of the generated PDF files and the mail sending.</li>
<li>Excel Macro: Is the macro that generates every report.</li>
</ul>
