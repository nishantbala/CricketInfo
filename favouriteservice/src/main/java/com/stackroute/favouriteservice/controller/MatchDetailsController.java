package com.stackroute.favouriteservice.controller;


import com.stackroute.favouriteservice.domain.MatchList;
import com.stackroute.favouriteservice.service.FavouriteService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;

import java.util.List;

@Controller
@RequestMapping(path = "/")
@CrossOrigin
public class MatchDetailsController {



    @Autowired
    FavouriteService favouriteService;

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public ResponseEntity getCurrentMatches() {
        return new ResponseEntity<>(favouriteService.getCurrentMatches(), HttpStatus.OK);
    }



    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ResponseEntity getMatchDetails(@PathVariable("id") Long id)  {
        try {
            return new ResponseEntity<>(favouriteService.getMatchDetails(id), HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    /**
     * Desc: get all Favlist of cricMatch
     * params userid
     * @return List of Match Object
     */
    @RequestMapping(method = RequestMethod.GET, value = "/favList/{userId}")
    public ResponseEntity<?> getAllFavListOfUser(@PathVariable String userId)  {
        System.out.println("Inside getAllFavouriteListOfUser Controller function");
        try {
            return new ResponseEntity<List<MatchList>>(favouriteService.getAllFavListOfUser(userId), HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            System.out.println("Inside MatchNotFoundException for getAllFavListOfUser Controller function");
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Desc: add to Favlist of user
     * params userid and Matchid
     * @return  Match Object
     */
    @RequestMapping(method = RequestMethod.POST, value = "addToFavList/{id}")
    public ResponseEntity<?> addMatchToFavList(@RequestHeader String userID, @PathVariable Long id) {
        System.out.println("Inside add match to FavList Controller function");
        try {
            return new ResponseEntity<MatchList>(favouriteService.addMatchToFavList(userID, id), HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            System.out.println("Inside MatchNotFoundException for addToFavList  Controller function");
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    /**
     * Desc: delete from Favlist of user
     * params userid and Matchid
     * @return  Match Object
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/removeFromFavList/{id}")
    public ResponseEntity<String> removeFromFavList(@RequestHeader String userID, @PathVariable Long id) {
        System.out.println("Inside remove match From FavList Controller function");
        try {
            return new ResponseEntity<String>(favouriteService.removeFromFavList(userID, id), HttpStatus.OK);
        } catch (MatchNotFoundException e) {
            System.out.println("Inside MatchNotFoundException for removeFromFavList  Controller function");
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
