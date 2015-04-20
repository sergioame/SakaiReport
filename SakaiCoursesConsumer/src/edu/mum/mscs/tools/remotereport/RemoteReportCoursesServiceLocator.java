/**
 * RemoteReportCoursesServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.mum.mscs.tools.remotereport;

public class RemoteReportCoursesServiceLocator extends org.apache.axis.client.Service implements edu.mum.mscs.tools.remotereport.RemoteReportCoursesService {

    public RemoteReportCoursesServiceLocator() {
    }


    public RemoteReportCoursesServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RemoteReportCoursesServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RemoteReportCourses
    private java.lang.String RemoteReportCourses_address = Util.rrQNAME;

    public java.lang.String getRemoteReportCoursesAddress() {
        return RemoteReportCourses_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RemoteReportCoursesWSDDServiceName = "RemoteReportCourses";

    public java.lang.String getRemoteReportCoursesWSDDServiceName() {
        return RemoteReportCoursesWSDDServiceName;
    }

    public void setRemoteReportCoursesWSDDServiceName(java.lang.String name) {
        RemoteReportCoursesWSDDServiceName = name;
    }

    public edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType getRemoteReportCourses() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RemoteReportCourses_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRemoteReportCourses(endpoint);
    }

    public edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType getRemoteReportCourses(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            edu.mum.mscs.tools.remotereport.RemoteReportCoursesSoapBindingStub _stub = new edu.mum.mscs.tools.remotereport.RemoteReportCoursesSoapBindingStub(portAddress, this);
            _stub.setPortName(getRemoteReportCoursesWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRemoteReportCoursesEndpointAddress(java.lang.String address) {
        RemoteReportCourses_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (edu.mum.mscs.tools.remotereport.RemoteReportCourses_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                edu.mum.mscs.tools.remotereport.RemoteReportCoursesSoapBindingStub _stub = new edu.mum.mscs.tools.remotereport.RemoteReportCoursesSoapBindingStub(new java.net.URL(RemoteReportCourses_address), this);
                _stub.setPortName(getRemoteReportCoursesWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("RemoteReportCourses".equals(inputPortName)) {
            return getRemoteReportCourses();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(Util.rrQNAME, "RemoteReportCoursesService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(Util.rrQNAME, "RemoteReportCourses"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RemoteReportCourses".equals(portName)) {
            setRemoteReportCoursesEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
