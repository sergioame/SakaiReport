/**
 * RemoteReportCoursesService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.mum.mscs.tools.remotereport;

public interface RemoteReportCoursesService extends javax.xml.rpc.Service {
    public java.lang.String getRemoteReportCoursesAddress();

    public edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType getRemoteReportCourses() throws javax.xml.rpc.ServiceException;

    public edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType getRemoteReportCourses(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
