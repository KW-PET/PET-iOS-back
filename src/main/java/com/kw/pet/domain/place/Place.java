package com.kw.pet.domain.place;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@Entity(name = "place")
@NoArgsConstructor
@AllArgsConstructor
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private int placeid;

    private Double xpos;

    private Double ypos;

    private Double lon;

    private Double lat;

    private String category;

    private String name;

    private String address;

    private String phone;
}
