package com.stackroute.favouriteservice.service;



import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.HttpUtils.HttpClient;
import com.stackroute.favouriteservice.dao.MatchDao;
import com.stackroute.favouriteservice.dao.FavListDao;
import com.stackroute.favouriteservice.domain.MatchList;

public class FavListServiceImplTest {

    @Mock
    private MatchDao matchDaoDao;

    @Mock
    private FavListDao favListDoa;

    @Mock
    private HttpClient httpClient;

    private transient MatchList matchList;

    @InjectMocks
    private FavouriteServiceImpl favouriteServiceImpl;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        long matchID = 1144512;
        long favListID = 1144512;
        long id = 123;
        this.matchList = new MatchList(id, matchID, "date", "dateTimeGMT", "team_1", "team_2", "type","squad", "toss_winner_team","winner_team","matchStarted");

    }

/*    @Test
    public void addToFavList() throws Exception {

        long matchID = 1144512;
        String userId = "UserId12";
        given(favListDoa.addMatchToFavList(Mockito.anyLong(),Mockito.anyString())).willReturn("added");
        String result = favouriteServiceImpl.addMatchToFavList(userId, matchID);
        assertEquals("Added to favlist", result);
    }*/

    @Test
    public void removeFromFavList() throws Exception {

        long matchID = 1144512;
        String userId = "UserId";
        given(favListDoa.removeFromFavList(Mockito.anyLong(), Mockito.anyString())).willReturn("Succcess");
        String result = favouriteServiceImpl.removeFromFavList(userId, matchID);
        assertEquals("Succcess", result);
    }

    @Test
    public void getAllFavListOfUser() throws Exception {

        List<MatchList> favListArray = new ArrayList();

        favListArray.add(matchList);
        String userId = "UserId";
        when(favListDoa.getAllFavListOfUser(Mockito.anyString())).thenReturn(favListArray);
        List<MatchList> tempFavList = favouriteServiceImpl.getAllFavListOfUser(userId);
        assertEquals(favListArray.size(), tempFavList.size());
    }

}
