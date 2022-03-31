package com.razal.intensappback.repository;

import com.razal.intensappback.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByName(String name);


}
