package com.stackroute.favouriteservice.domain;

import javax.persistence.*;

@Entity
public class FavList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long favListId;


    private String userId;

    private Long matchId;

    public FavList(Long favListId, String userId, Long matchId) {
        this.favListId = favListId;
        this.userId = userId;
        this.matchId = matchId;
    }

    public FavList() {

    }

    public Long getFavListId() {
        return favListId;
    }

    public void setFavListId(Long favListId) {
        this.favListId = favListId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
}
