package com.TripPlanner.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "countryName")
    private String countryName;
    @Column(name = "regionName")
    private  String regionName;
    @Column(name = "localityName")
    private String localityName;

}
