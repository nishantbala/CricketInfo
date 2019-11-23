package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.HttpUtils.HttpClient;
import com.stackroute.favouriteservice.Model.MatchDetailResponse;
import com.stackroute.favouriteservice.Model.MatchResponse;
import com.stackroute.favouriteservice.dao.FavListDao;
import com.stackroute.favouriteservice.dao.MatchDao;
import com.stackroute.favouriteservice.domain.MatchDetailsList;
import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;

@Service
public class FavouriteServiceImpl implements FavouriteService {

    @Autowired
    private FavListDao favListDao;

    @Autowired
    private MatchDao matchDao;
    
    @Autowired
    private HttpClient httpClient;

    //Get Current Match List
    @Override
    public List<MatchList> getCurrentMatches() {
        List<MatchList> currentMatches;
        List<MatchList> matchList = new ArrayList<>();
        try {
            currentMatches = matchDao.getCurrentMatches();
            if (currentMatches.size() == 0){
                MatchResponse matchData = httpClient.getCurrentMatchList();
                matchList = matchDao.storeMatchData(matchData);
            } else {
                MatchResponse matchData = httpClient.getCurrentMatchList();
                matchList = matchDao.storeMatchData(matchData, currentMatches);
            }
        } catch (Exception e){
            e.printStackTrace();

        }
        Collections.sort(matchList);
        return matchList;
    }

    /**
     * Desc: To get the match object from database for given matchid
     *
     * @param id - match id
     * @return match object
     */
    @Override
    public MatchDetailsList getMatchDetails(Long id) throws MatchNotFoundException{

    	MatchDetailsList matchDetails = new MatchDetailsList();
    	try {
    			MatchDetailResponse matchDetailResp = httpClient.getMatchDetails(id);
    			matchDetails = new MatchDetailsList(id, matchDetailResp.getStat(), matchDetailResp.getScore(), matchDetailResp.getDescription(),
    					matchDetailResp.getMatchStarted(), matchDetailResp.getTeam1(), matchDetailResp.getTeam2(), matchDetailResp.getV(),
    					matchDetailResp.getTtl(), matchDetailResp.getProvider(),matchDetailResp.getCreditsLeft());
    	} catch (Exception e){
            e.printStackTrace();

        }
    	return matchDetails;
    }


    @Override
    public MatchList addMatchToFavList(String userID,Long id) throws MatchNotFoundException {

        return favListDao.addMatchToFavList(id, userID);
    }


    /**
     * Desc: To remove the Matches in user list database
     *
     * @param id - match id
     * @param userID - user id
     * @return match - match object
     */
    @Override
    public String removeFromFavList(String userID, Long id) throws MatchNotFoundException {

        return favListDao.removeFromFavList(id, userID);
    }



    /**
     * Desc: To get all the Matches in user list database
     *
     * @param userID - user id
     * @return Match list
     */
    @Override
    public List<MatchList> getAllFavListOfUser(String userID) throws MatchNotFoundException {

        return favListDao.getAllFavListOfUser(userID);
    }
}
