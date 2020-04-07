package com.TripPlanner.Service;

import com.TripPlanner.Model.Sport;
import com.TripPlanner.Model.Spot;
import com.TripPlanner.Wrapper.WrapperBonus;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AppService  {
    private final com.TripPlanner.Dao.sportsRepository sportsRepository;
    private final com.TripPlanner.Dao.spotRepository spotRepository;


    public AppService(com.TripPlanner.Dao.sportsRepository sportsRepository, com.TripPlanner.Dao.spotRepository spotRepository) {
        this.sportsRepository = sportsRepository;
        this.spotRepository = spotRepository;
    }
    public Sport save(Sport sport, Spot spot)
    {
        Optional<Spot> spotCheck = spotRepository.findByCountryNameAndRegionNameAndLocalityName(spot.getCountryName(),spot.getRegionName(),spot.getLocalityName());
        if(spotCheck.isEmpty())
        {
            spotRepository.save(spot);
            sport.setSpotId(spot.getId());
            return sportsRepository.save(sport);
        }
        sport.setSpotId(spotCheck.get().getId());
        return sportsRepository.save(sport);
    }
    public Spot saveSpot(Spot spot){
        Optional<Spot> spotCheck = spotRepository.findByCountryNameAndRegionNameAndLocalityName(spot.getCountryName(),spot.getRegionName(),spot.getLocalityName());
        if(spotCheck.isEmpty()) {
            return spotRepository.save(spot);
        }
        return null;
    }
    public void deleteSpot(Long id)
    {
        spotRepository.deleteById(id);
        sportsRepository.deleteAllBySpotId(id);
    }
    public Spot updateSpot(Long id, Spot spot)
    {
        Spot spotUpdate = spotRepository.getOne(id);
        spotUpdate.setCountryName(spot.getCountryName());
        spotUpdate.setLocalityName(spot.getLocalityName());
        spotUpdate.setRegionName(spot.getRegionName());
       return spotRepository.save(spotUpdate);
    }
    public Optional<Spot> getSpot(Long id)
    {
        return spotRepository.findById(id);
    }
    public List<WrapperBonus> getBest(String sports, Date from, Date to)
    {
        String[] arrOfSports = sports.split(",");
        ArrayList<Sport> sportsListToGetSpots = new ArrayList<Sport>();
        ArrayList<Spot> spotList = new ArrayList<Spot>();
        for (String sport : arrOfSports) {
         sportsListToGetSpots.addAll(sportsRepository.findAllByNameAndFromAndTo(sport,from,to));
        }
        for( Sport sport : sportsListToGetSpots)
        {
            spotList.add(spotRepository.findById(sport.getSpotId()).get());
        }
        ArrayList<WrapperBonus> mapped = new ArrayList<WrapperBonus>();
        for(Spot spot : spotList)
        {
           List <Sport> sportsList = sportsRepository.findAllBySpotIdAndFromAndTo(spot.getId(),from,to);
            Double finalCost = sportsList.stream()
                    .mapToDouble(a->a.getCost())
                    .sum();
            mapped.add(new WrapperBonus(spot,sportsList,finalCost));
        }
        Collections.sort(mapped);
        return mapped;
    }


}
