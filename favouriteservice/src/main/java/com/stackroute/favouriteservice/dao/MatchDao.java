package com.stackroute.favouriteservice.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.favouriteservice.Model.MatchResponse;
import com.stackroute.favouriteservice.Model.Matches;
import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;

@Component
public class MatchDao {

    @Autowired
    MatchRepository matchRepository;


    public List<MatchList> getCurrentMatches(){
        List<MatchList> matchList = matchRepository.findAll();
        return matchList;
    }
    
    public Map<Long, MatchList> convertListToMap(List<MatchList> matchList){
        Map<Long,MatchList> matchListMap = new HashMap<Long,MatchList>();
        for (MatchList i : matchList) matchListMap.put(i.getMatchId(),i);
        return matchListMap;
    }
    
    public List<MatchList> convertMapToList(Map<Long, MatchList> matchListMap){
    	Collection<MatchList> values = matchListMap.values(); 
    	List<MatchList> matchList = new ArrayList<MatchList>(values);
        return matchList;
    }

    public MatchList getMatchWithId(Long id) throws MatchNotFoundException{
       MatchList match =  matchRepository.findByMatchId(id);
        if(match != null) {
            return match;
        } else {
            throw new MatchNotFoundException("Data Not available for the matchid :" + id);
        }
    }

    public List<MatchList> storeMatchData(MatchResponse matchData){

        List<Matches> matches = matchData.getMatches();
        List<MatchList> addedMatch = new ArrayList<MatchList>();
        for(int i=0; i< matchData.getMatches().size(); i++){
            MatchList match = new MatchList();
            match.setMatchId(matches.get(i).getUnique_id());
            match.setDate(matches.get(i).getDate());
            match.setDateTimeGMT(matches.get(i).getDateTimeGMT());
            match.setTeam_1(matches.get(i).getTeam1());
            match.setTeam_2(matches.get(i).getTeam2());
            match.setType(matches.get(i).getType());
            match.setSquad(matches.get(i).getSquad());
            match.setToss_winner_team(matches.get(i).getToss_winner_team());
            match.setWinner_team(matches.get(i).getWinner_team());
            match.setMatchStarted(matches.get(i).getMatchStarted());
            addedMatch.add(matchRepository.save(match));
        }
        return addedMatch;
    }
    
    public List<MatchList> storeMatchData(MatchResponse matchData, List<MatchList> matchesFromDb){

    	Map<Long,MatchList> addedMatch = convertListToMap(matchesFromDb);
    	List<Matches> matches = matchData.getMatches();
        for(int i=0; i< matchData.getMatches().size(); i++){
        	Long key = matches.get(i).getUnique_id();
            if(addedMatch.containsKey(key)) {
            	MatchList match = addedMatch.get(key);
            	if(match.getMatchId() != matches.get(i).getUnique_id() ||
            	   !match.getDate().equals(matches.get(i).getDate()) ||
            	   !match.getDateTimeGMT().equals(matches.get(i).getDateTimeGMT()) ||
            	   !match.getTeam_1().equals(matches.get(i).getTeam1()) ||
                   !match.getTeam_2().equals(matches.get(i).getTeam2()) ||
                   !match.getType().equals(matches.get(i).getType()) ||
                   !match.getSquad().equals(matches.get(i).getSquad()) ||
                   !match.getToss_winner_team().equals(matches.get(i).getToss_winner_team()) ||
                   !match.getWinner_team().equals(matches.get(i).getWinner_team()) ||
                   !match.getMatchStarted().equals(matches.get(i).getMatchStarted())) {
            		match.setMatchId(matches.get(i).getUnique_id());
                    match.setDate(matches.get(i).getDate());
                    match.setDateTimeGMT(matches.get(i).getDateTimeGMT());
                    match.setTeam_1(matches.get(i).getTeam1());
                    match.setTeam_2(matches.get(i).getTeam2());
                    match.setType(matches.get(i).getType());
                    match.setSquad(matches.get(i).getSquad());
                    match.setToss_winner_team(matches.get(i).getToss_winner_team());
                    match.setWinner_team(matches.get(i).getWinner_team());
                    match.setMatchStarted(matches.get(i).getMatchStarted());
                    addedMatch.put(match.getMatchId(),matchRepository.save(match));
            	}
            } else {
            	MatchList match = new MatchList();
                match.setMatchId(matches.get(i).getUnique_id());
                match.setDate(matches.get(i).getDate());
                match.setDateTimeGMT(matches.get(i).getDateTimeGMT());
                match.setTeam_1(matches.get(i).getTeam1());
                match.setTeam_2(matches.get(i).getTeam2());
                match.setType(matches.get(i).getType());
                match.setSquad(matches.get(i).getSquad());
                match.setToss_winner_team(matches.get(i).getToss_winner_team());
                match.setWinner_team(matches.get(i).getWinner_team());
                match.setMatchStarted(matches.get(i).getMatchStarted());
                addedMatch.put(match.getMatchId(),matchRepository.save(match));
            }
        }
        return convertMapToList(addedMatch);
    }


}

