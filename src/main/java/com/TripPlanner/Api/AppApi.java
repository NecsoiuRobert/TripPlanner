package com.TripPlanner.Api;

import com.TripPlanner.Model.Sport;
import com.TripPlanner.Model.Spot;
import com.TripPlanner.Service.AppService;
import com.TripPlanner.Wrapper.RequestWrapperSport;
import com.TripPlanner.Wrapper.WrapperBonus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class AppApi {
    private final AppService appService;

    public AppApi(AppService appService) {
        this.appService = appService;
    }
    @PostMapping(path = "add-sport/")
    public Sport save(@RequestBody @NotNull RequestWrapperSport wrapperSport)
    {
        return appService.save(wrapperSport.getSport(),wrapperSport.getSpot());
    }
    @GetMapping(path = "get-spot/{id}")
    public Optional<Spot> getSpot(@PathVariable("id")@NotNull Long id)
    {
        return appService.getSpot(id);
    }
    @PostMapping(path = "add-spot/")
    public Spot saveSpot(@RequestBody @NotNull  Spot spot)
    {
        return appService.saveSpot(spot);
    }
    @DeleteMapping(path = "delete-spot/{id}")
    public void deleteSpot(@PathVariable("id") @NotNull Long id){
        appService.deleteSpot(id);
    }
    @PutMapping(path = "update-spot/{id}")
    public Spot updateSpot(@PathVariable("id")@NotNull Long id,@NotNull @RequestBody Spot spot)
    {
        return appService.updateSpot(id, spot);
    }

    @GetMapping(path = "get-best/{sports}+{from}+{to}")
    public List<WrapperBonus> getBest(@PathVariable("sports") String sports, @PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date from, @PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date to)
    {
        return appService.getBest(sports,from,to);
    }
}
