package com.razal.intensappback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;

    @ManyToMany(mappedBy = "skills")
    @JsonIgnore
    List<Candidate> candidates;
}
