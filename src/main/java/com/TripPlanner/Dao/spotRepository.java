package com.TripPlanner.Dao;

import com.TripPlanner.Model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface spotRepository extends JpaRepository<Spot,Long> {
    @Query("SELECT spot FROM Spot spot WHERE spot.countryName = ?1 AND spot.regionName = ?2 AND spot.localityName = ?3 ")
    public Optional<Spot> findByCountryNameAndRegionNameAndLocalityName(String country,String region,String locality);
}
