package com.razal.intensappback.service;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;

import java.util.List;

public interface CandidateService {

    Candidate addCandidate(Candidate candidate);

    List<Skill> addSkills(List<Skill> skills);

    Candidate updateCandidateWithSkill(Candidate candidate);

    Candidate removeSkillFromCandidate(Candidate candidate,Long skillId);

    Boolean removeCandidate(Long id);

    Candidate searchCandidateByName(String name);

    List<Candidate> searchAllCandidatesWithGivenSkills(List<Long> skillsId);


}
