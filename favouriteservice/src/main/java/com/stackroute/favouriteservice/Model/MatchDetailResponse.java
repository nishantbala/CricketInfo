package com.stackroute.favouriteservice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class MatchDetailResponse {

	  private Long id;
	  private Long matchId;
	  private String stat;
	  private String score;
	  private String description;
	  private String matchStarted;
	  @JsonProperty("team-1")
	  private String team1;
	  @JsonProperty("team-2")
	  private String team2;
	  private String v;
	  private String ttl;
	  private String creditsLeft;
	  @JsonDeserialize(using = ProviderDeserializer.class)
	  private Provider provider;
	  
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
