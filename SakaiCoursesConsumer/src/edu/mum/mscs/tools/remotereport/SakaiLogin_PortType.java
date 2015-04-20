/**
 * SakaiLogin_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edu.mum.mscs.tools.remotereport;

public interface SakaiLogin_PortType extends java.rmi.Remote {
    public java.lang.String login(java.lang.String id, java.lang.String pw) throws java.rmi.RemoteException;
    public boolean logout(java.lang.String sessionid) throws java.rmi.RemoteException;
    public java.lang.String loginToServer(java.lang.String id, java.lang.String pw) throws java.rmi.RemoteException;
}
