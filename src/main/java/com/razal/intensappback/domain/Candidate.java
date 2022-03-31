package com.razal.intensappback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.razal.intensappback.validation.annotation.PhoneNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.IDENTITY;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Size(min = 3,message = "Name must have at least 3 characters")
    String name;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = STRING)
    @Past(message = "Invalid date of birth")
    @NotNull(message = "Required field")
    Date dateOfBirth;

    @Size(min = 7,message = "Please provide at least 7 digits")
    @Size(max = 15,message = "Invalid phone number")
    @PhoneNumberConstraint(message = "Please provide only digits")
    String contactNumber;

    @Email(message = "Invalid email")
    String email;

    @ManyToMany(cascade = {PERSIST,MERGE,DETACH,REFRESH}) //Persist-ako dodajem skill kandidatu,ubaci taj skill u bazu
    @JoinTable(
            name = "candidate_skills",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @NotEmpty(message = "Candidate must have at least 1 skill")
    List<Skill> skills; //nema duplikata

}
