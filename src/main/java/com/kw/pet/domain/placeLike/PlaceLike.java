package com.kw.pet.domain.placeLike;

import com.kw.pet.domain.place.Place;
import com.kw.pet.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Data
@Entity(name = "placelike")
@NoArgsConstructor
@AllArgsConstructor
public class PlaceLike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "placelike_id")
    private Long placelikeid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    public PlaceLike(User user, Place place) {
        this.user = user;
        this.place = place;
    }
}