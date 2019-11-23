package com.stackroute.favouriteservice.repository;


import com.stackroute.favouriteservice.domain.MatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<MatchList, Long> {

    public MatchList findByMatchId(Long id);
}