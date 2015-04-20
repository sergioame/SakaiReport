package edu.mum.mscs.tools.remotereport;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class MembershipWSConsumer {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        RestTemplate restTemplate = new RestTemplate();
        try {
			SakaiUserMemberships userMemberships = restTemplate.getForObject("https://myschool.edu/direct/membership.json", SakaiUserMemberships.class);
			SakaiMembership membership = null;
			if (userMemberships != null && userMemberships.getMemberships() != null) {
				membership = userMemberships.getMemberships().get(0);
			    if (membership != null) {
			    	System.out.println("SakaiUser1stMembership role:    " + membership.getMemberRole());
			    }
			} else {
				System.out.println("SakaiUserMemberships is null");
			}
		} catch (HttpClientErrorException e) {
			// TODO Auto-generated catch block
			System.out.println("The user is not logged into Sakai.");
			e.printStackTrace();
		}
        
	}

}
