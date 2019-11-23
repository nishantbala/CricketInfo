package com.stackroute.favouriteservice.service;


import java.util.List;

import com.stackroute.favouriteservice.domain.MatchDetailsList;
import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;

public interface FavouriteService {
    MatchList addMatchToFavList(String userID, Long id) throws MatchNotFoundException;

    String removeFromFavList(String userID, Long id) throws MatchNotFoundException;

    List<MatchList> getAllFavListOfUser(String userId) throws MatchNotFoundException;

    List<MatchList> getCurrentMatches();

    MatchDetailsList getMatchDetails(Long id) throws MatchNotFoundException;







}
