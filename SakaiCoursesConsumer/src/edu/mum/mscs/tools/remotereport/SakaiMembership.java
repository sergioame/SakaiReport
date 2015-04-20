package edu.mum.mscs.tools.remotereport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SakaiMembership {
	
	private String userEid;
	
	private String memberRole;

	public String getUserEid() {
		return userEid;
	}

	public String getMemberRole() {
		return memberRole;
	}

}
