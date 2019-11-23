package com.stackroute.favouriteservice.controller;



import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.stackroute.favouriteservice.component.CORSFilter;
import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.JwtToken;
import com.stackroute.favouriteservice.service.FavouriteService;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchDetailsController.class)

public class MatchControllerTest {


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
        public void setupMock() {
            this.mockMvc = MockMvcBuilders.webAppContextSetup( context )
                    .addFilter(new CORSFilter(), "/*")
                    .build();
            MockitoAnnotations.initMocks(this);
            long matchID = 1144512;
            long favListID = 1144512;
            long id = 123;
            this.matchList = new MatchList(id, matchID, "date", "dateTimeGMT", "team_1", "team_2", "type","squad", "toss_winner_team","winner_team","matchStarted");

        }

        @Test
        public void getAllMatch() throws Exception {
            List<MatchList> matchArray = new ArrayList();
            matchArray.add(matchList);
            when(favouriteService.getCurrentMatches()).thenReturn(matchArray);
            MvcResult result = mockMvc.perform(get(
                    "/current").accept(
                    MediaType.APPLICATION_JSON)
                    .header("Content-type",MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()).andReturn();
            String matchData = result.getResponse().getContentAsString();
            DocumentContext context = JsonPath.parse(matchData);
            int length = context.read("$.length()");
            assertEquals(length ,matchArray.size());
        }



        private String jsonToString(final Object obj1) throws JsonProcessingException {
            String result;
            try {
                final ObjectMapper mapper = new ObjectMapper();
                final String message = mapper.writeValueAsString(obj1);
                result = message;
            } catch (JsonProcessingException e) {
                result = "Json processing error";
            }

            return result;
        }








    }

