package com.razal.intensappback.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import static javax.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
}
