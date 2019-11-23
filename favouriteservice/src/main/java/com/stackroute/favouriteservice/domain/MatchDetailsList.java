package com.stackroute.favouriteservice.domain;

import com.stackroute.favouriteservice.Model.Provider;

public class MatchDetailsList {


    private Long id;

    private Long matchId;
    private String stat;
    private String score;
    private String description;
    private String matchStarted;
    private String team1;
    private String team2;
    private String v;
    private String ttl;
    private String url;
    private String source;
    private String pubDate;
    private String creditsLeft;


    public MatchDetailsList(Long id, String stat, String score, String description, String matchStarted, String team1, String team2,String v,String ttl,Provider provider,String creditsLeft) {

        this.id = id;
        this.matchId = id;
        this.stat = stat;
        this.score = score;
        this.description = description;
        this.matchStarted = matchStarted;
        this.team1 = team1;
        this.team2 = team2;
        this.v = v;
        this.ttl = ttl;
        this.creditsLeft = creditsLeft;
        this.url = provider.getUrl();
        this.source = provider.getSource();
        this.pubDate = provider.getPubDate();
    }

	public MatchDetailsList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long Id) {
        this.id = id;
    }
    
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMatchStarted() {
		return matchStarted;
	}

	public void setMatchStarted(String matchStarted) {
		this.matchStarted = matchStarted;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getTtl() {
		return ttl;
	}

	public void setTtl(String ttl) {
		this.ttl = ttl;
	}

	public String getCreditsLeft() {
		return creditsLeft;
	}

	public void setCreditsLeft(String creditsLeft) {
		this.creditsLeft = creditsLeft;
	}

}
