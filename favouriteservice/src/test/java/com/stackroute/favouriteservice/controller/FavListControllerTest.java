package com.stackroute.favouriteservice.controller;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.FavouriteService;
import com.stackroute.favouriteservice.service.FavouriteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.stackroute.favouriteservice.component.CORSFilter;
import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.service.JwtToken;
import com.stackroute.favouriteservice.service.FavouriteService;
import com.jayway.restassured.http.ContentType;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(FavListControllerTest.class)



public class FavListControllerTest {

    @Mock
    FavouriteServiceImpl favouriteServiceImpl;

        @Autowired
        private transient MockMvc mockMvc;

        @Autowired
        private WebApplicationContext context;

        @MockBean
        private transient FavouriteService favouriteService;

        @MockBean
        private transient JwtToken jwtToken;

        private transient MatchList matchList;

        @Before
        public void init(){
            setupMock();

        }

        public void setupMock() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilter(new CORSFilter(), "/*").build();
            MockitoAnnotations.initMocks(this);
            long matchID = 1144512;
            long favListID = 1144512;
            long id = 123;
            this.matchList = new MatchList(id, matchID, "date", "dateTimeGMT", "team_1", "team_2", "type","squad", "toss_winner_team","winner_team","matchStarted");

        }

    @Test
    public void thatShouldReturnListOfFavListOfUserWhenInvokingAPI() throws MatchNotFoundException {

        List<MatchList> matchLists = new ArrayList<MatchList>();
        matchLists.add(matchList);
        when(favouriteServiceImpl.getAllFavListOfUser("12")).thenReturn(matchLists);
        List<MatchList> favListOfUser = favouriteServiceImpl.getAllFavListOfUser("12");
        int expectedMatchId = 1144512;
        assertEquals(expectedMatchId, favListOfUser.get(0).getMatchId().intValue());
    }

    @Test
    public void thatShouldAddNewItemIntoFavListWhenInvokingAPI() throws MatchNotFoundException {

        when(favouriteServiceImpl.addMatchToFavList("1", (long) 123)).thenReturn(matchList);
        MatchList addedMatch = favouriteServiceImpl.addMatchToFavList("1", (long) 123);
        assertNotNull(addedMatch);
    }

    @Test
    public void thatShouldRemoveItemFromFavListWhenInvokingAPI() throws MatchNotFoundException {

        when(favouriteServiceImpl.removeFromFavList("1", (long) 123)).thenReturn("Success");
        String msg = favouriteServiceImpl.removeFromFavList("1", (long) 123);
        assertThat("Success", is(msg));
    }

    }