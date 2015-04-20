/**
 * SakaiLoginServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.mum.mscs.tools.remotereport;

public class SakaiLoginServiceLocator extends org.apache.axis.client.Service implements edu.mum.mscs.tools.remotereport.SakaiLoginService {

    public SakaiLoginServiceLocator() {
    }


    public SakaiLoginServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SakaiLoginServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SakaiLogin
    private java.lang.String SakaiLogin_address = Util.loginQNAME;

    public java.lang.String getSakaiLoginAddress() {
        return SakaiLogin_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SakaiLoginWSDDServiceName = "SakaiLogin";

    public java.lang.String getSakaiLoginWSDDServiceName() {
        return SakaiLoginWSDDServiceName;
    }

    public void setSakaiLoginWSDDServiceName(java.lang.String name) {
        SakaiLoginWSDDServiceName = name;
    }

    public edu.mum.mscs.tools.remotereport.SakaiLogin_PortType getSakaiLogin() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SakaiLogin_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSakaiLogin(endpoint);
    }

    public edu.mum.mscs.tools.remotereport.SakaiLogin_PortType getSakaiLogin(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            edu.mum.mscs.tools.remotereport.SakaiLoginSoapBindingStub _stub = new edu.mum.mscs.tools.remotereport.SakaiLoginSoapBindingStub(portAddress, this);
            _stub.setPortName(getSakaiLoginWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSakaiLoginEndpointAddress(java.lang.String address) {
        SakaiLogin_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (edu.mum.mscs.tools.remotereport.SakaiLogin_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                edu.mum.mscs.tools.remotereport.SakaiLoginSoapBindingStub _stub = new edu.mum.mscs.tools.remotereport.SakaiLoginSoapBindingStub(new java.net.URL(SakaiLogin_address), this);
                _stub.setPortName(getSakaiLoginWSDDServiceName());
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
        if ("SakaiLogin".equals(inputPortName)) {
            return getSakaiLogin();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(Util.loginQNAME, "SakaiLoginService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(Util.loginQNAME, "SakaiLogin"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SakaiLogin".equals(portName)) {
            setSakaiLoginEndpointAddress(address);
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
