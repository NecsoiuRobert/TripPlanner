package com.TripPlanner.Dao;

import com.TripPlanner.Model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface sportsRepository extends JpaRepository<Sport, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Sport sport WHERE sport.spotId = ?1 ")
    public void deleteAllBySpotId(Long spotId);
    @Query("SELECT sport FROM Sport sport WHERE sport.name = ?1 AND sport.from = ?2 AND sport.to = ?3")
    public List<Sport> findAllByNameAndFromAndTo(String name, Date from, Date to);
    @Query("SELECT sport FROM Sport sport WHERE sport.spotId = ?1  AND sport.from = ?2 AND sport.to = ?3 ")
    public List<Sport> findAllBySpotIdAndFromAndTo(Long id, Date from, Date to);
}
