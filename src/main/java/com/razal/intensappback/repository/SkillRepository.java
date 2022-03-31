package com.razal.intensappback.repository;

import com.razal.intensappback.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SkillRepository extends JpaRepository<Skill,Long> {

    Boolean existsByName(String name);

    Skill findByName(String name);
}
