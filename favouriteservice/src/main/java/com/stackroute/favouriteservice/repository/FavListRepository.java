package com.stackroute.favouriteservice.repository;

import org.springframework.data.jpa.repository.*;

import com.stackroute.favouriteservice.domain.FavList;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FavListRepository extends JpaRepository<FavList, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM FavList w WHERE w.userId = :userId AND w.matchId = :id")
    public void     removeMatch(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = "SELECT w FROM FavList w WHERE w.userId = :userId AND w.matchId = :id")
    public FavList checkfavList(@Param("id") Long id, @Param("userId") String userId);

    @Query(value = "SELECT w FROM FavList w WHERE w.userId = :userId")
    public List<FavList> findByUserId(@Param("userId") String userId);

}

