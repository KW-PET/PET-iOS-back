package com.kw.pet.domain.pet;

import com.kw.pet.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Data
@Entity(name = "pet")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long petid;

    private String name;

    private String pic;

    private String sort;

    private int age;

    private LocalDate start_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
