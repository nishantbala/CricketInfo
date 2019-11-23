package com.stackroute.favouriteservice.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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

import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.repository.MatchRepository;

public class MatchDaoTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchDao matchDao;

    private transient MatchList matchList;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        long matchID = 1144512;

        long id = 123;
        this.matchList = new MatchList(id, matchID, "date", "dateTimeGMT", "team_1", "team_2", "type","squad", "toss_winner_team","winner_team","matchStarted");

    }

    @Test
    public void getAllMatch() throws Exception {
        List<MatchList> matchArray = new ArrayList();
        matchArray.add(matchList);
        when(matchRepository.findAll()).thenReturn(matchArray);
        List<MatchList> tempMatch = matchDao.getCurrentMatches();
        assertEquals(matchArray.size(), tempMatch.size());
    }

    @Test
    public void getMatchWithId() throws Exception {
        long matchID = 1144512;
        when(matchRepository.findByMatchId(matchID)).thenReturn(matchList);
        MatchList tempMatch = matchDao.getMatchWithId(matchID);
        assertEquals(matchList.getMatchId(), tempMatch.getMatchId());
    }


}
