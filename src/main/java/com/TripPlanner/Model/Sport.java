package com.TripPlanner.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Table(name = "sport")
public class Sport {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "spotId")
    private Long spotId;
    @Column(name = "name")
    private String name;
    @Column(name = "toDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date to;
    @Column(name = "fromDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date from;
    @Column(name = "cost")
    private Double cost;

}
