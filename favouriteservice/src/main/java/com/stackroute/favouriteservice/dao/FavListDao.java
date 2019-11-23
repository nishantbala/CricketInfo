package com.stackroute.favouriteservice.dao;

import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.domain.FavList;
import com.stackroute.favouriteservice.repository.FavListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
@Component
public class FavListDao {
    @Autowired
    private FavListRepository favListRepository;

    @Autowired
    private MatchDao matchDao;

    public MatchList addMatchToFavList(Long id, String userId) throws MatchNotFoundException {

        MatchList match =  matchDao.getMatchWithId(id);
        if(match != null) {
            FavList favList = new FavList();
            favList.setMatchId(id);
            favList.setUserId(userId);
            FavList favListData =  favListRepository.checkfavList(id, userId);
            System.out.print("favListData" + favListData);
            if(favListData == null){
                favListRepository.save(favList);
            } else {
                removeFromFavList(id, userId);
//                throw new MatchNotFoundException("Match has been already added for the user matchid  :" + id);
            }
        } else {
            throw new MatchNotFoundException("Data Not available for the matchid :" + id);
        }
        return match;
    }

    public String removeFromFavList(Long id, String userId) throws MatchNotFoundException {

        MatchList match =  matchDao.getMatchWithId(id);
        if(match != null) {
            favListRepository.removeMatch(id, userId);
        } else {
            throw new MatchNotFoundException("Data Not availabe for the matchid :" + id);
        }
        return "Success";
    }

    public List<MatchList> getAllFavListOfUser(String userId) throws MatchNotFoundException {

        try {

            List<FavList> userFavList = favListRepository.findByUserId(userId);
            List<MatchList> matchList = new ArrayList<MatchList>();
            for(FavList f :  userFavList) {
                matchList.add(matchDao.getMatchWithId(f.getMatchId()));

            }

            return matchList;
        } catch (Exception e) {
            throw new MatchNotFoundException("No FavList Avaliable for UserID :" + userId);
        }
    }
}
