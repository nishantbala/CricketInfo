
package com.stackroute.favouriteservice.dao;

        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.doNothing;
        import static org.mockito.Mockito.when;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Date;
        import java.util.List;

        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.Mockito;
        import org.mockito.MockitoAnnotations;

        import com.stackroute.favouriteservice.domain.MatchList;
        import com.stackroute.favouriteservice.domain.FavList;
        import com.stackroute.favouriteservice.repository.MatchRepository;
        import com.stackroute.favouriteservice.repository.FavListRepository;

public class FavListDaoTest {

    @Mock
    private FavListRepository favListRepository;

    @Mock
    private MatchRepository matchRepository;

    @Mock
    private MatchDao matchDao;

    @InjectMocks
    private FavListDao favListDoa;

    private transient MatchList matchList;

    private transient FavList favList;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        long matchID = 1144512;
        long favListID = 12;
        long id = 123;
        this.matchList = new MatchList(id, matchID, "date", "dateTimeGMT", "team_1", "team_2", "type","squad", "toss_winner_team","winner_team","matchStarted");
        this.favList = new FavList(favListID, "UserID", matchID);
    }

    @Test
    public void removeFromWatchList() throws Exception {
        long matchID = 1144512;
        when(matchDao.getMatchWithId(matchID)).thenReturn(matchList);
        doNothing().when(favListRepository).removeMatch(Mockito.anyLong(), Mockito.anyString());
        System.out.println("match id :"+matchID);
        String tempWatchList = favListDoa.removeFromFavList(matchID, "userId");
        assertEquals("Success",tempWatchList);
    }

    @Test
    public void getAllFavListOfUser() throws Exception {
        long matchID = 1144512;
        String userId = "UserId";
        List<FavList> favListArray = new ArrayList<FavList>();
        favListArray.add(favList);
        when(favListRepository.findByUserId(Mockito.anyString())).thenReturn(favListArray);
        List<MatchList> matchListArray = new ArrayList<MatchList>();
        matchListArray.add(matchList);
        List<Long> matchIds =  Arrays.asList(new Long[]{matchID});
        when(matchRepository.findByMatchId(matchID)).thenReturn(matchList);
        when(matchDao.getMatchWithId(matchID)).thenReturn(matchList);
        List<MatchList> tempWatchList = favListDoa.getAllFavListOfUser(userId);
        assertEquals(favListArray.size(),tempWatchList.size());
    }

}

