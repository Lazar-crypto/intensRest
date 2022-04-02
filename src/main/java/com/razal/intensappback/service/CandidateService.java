package com.razal.intensappback.service;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;

import java.util.List;
import java.util.Set;

public interface CandidateService {

    Candidate addCandidate(Candidate candidate);

    List<Skill> addSkills(List<Skill> skills);

    Candidate updateCandidateWithSkill(Candidate candidate,Skill skill);

    Candidate removeSkillFromCandidate(Candidate candidate,Skill skill);

    Boolean removeCandidate(Long id);

    Candidate searchCandidateByName(String name);

    Set<Candidate> searchAllCandidatesWithGivenSkills(List<Long> skillsId);

    List<Candidate> getAllCandidates();


}
