package edu.mum.mscs.tools.remotereport;

import java.util.List;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SakaiUserMemberships {
	
    private List<SakaiMembership> memberships;

    public List<SakaiMembership> getMemberships() {
		return memberships;
	}

    @JsonDeserialize(contentAs = SakaiMembership.class)
	public void setMemberships(List<SakaiMembership> memberships) {
		this.memberships = memberships;
	}

	@Override
    public String toString() {
        return "ListSakaiMembership [list=" + memberships + "]";
    }

}
