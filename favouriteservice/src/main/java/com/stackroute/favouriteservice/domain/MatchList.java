package com.stackroute.favouriteservice.domain;

import javax.persistence.*;

@Entity
public class MatchList implements Comparable<MatchList>{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long matchId;
    @Column(length = 1000)
    private String date;
    @Column(length = 1000)
    private String dateTimeGMT;
    @Column(length = 1000)
    private String team_1;
    @Column(length = 1000)
    private String team_2;
    private String type;
    @Column(length = 1000)
    private String squad;
    @Column(length = 1000)
    private String toss_winner_team;
    @Column(length = 1000)
    private String winner_team;
    @Column(length = 1000)
    private String matchStarted;

    public MatchList(Long id, Long matchId, String date, String dateTimeGMT, String team_1, String team_2, String type, String squad, String toss_winner_team, String winner_team, String matchStarted) {

        this.id = id;
        this.matchId = matchId;
        this.date = date;
        this.dateTimeGMT = dateTimeGMT;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.type = type;
        this.squad = squad;
        this.toss_winner_team = toss_winner_team;
        this.winner_team = winner_team;
        this.matchStarted = matchStarted;
    }

    public MatchList() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public void setDateTimeGMT(String dateTimeGMT) {
        this.dateTimeGMT = dateTimeGMT;
    }

    public String getTeam_1() {
        return team_1;
    }

    public void setTeam_1(String team_1) {
        this.team_1 = team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public void setTeam_2(String team_2) {
        this.team_2 = team_2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getToss_winner_team() {
        return toss_winner_team;
    }

    public void setToss_winner_team(String toss_winner_team) {
        this.toss_winner_team = toss_winner_team;
    }

    public String getWinner_team() {
        return winner_team;
    }

    public void setWinner_team(String winner_team) {
        this.winner_team = winner_team;
    }

    public String getMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(String matchStarted) {
        this.matchStarted = matchStarted;
    }

	@Override
	public int compareTo(MatchList o) {
		if (getId() == null || o.getId() == null) {
		      return 0;
		    }
		    return getId().compareTo(o.getId());
	}
}
