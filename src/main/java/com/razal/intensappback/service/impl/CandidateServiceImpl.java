package com.razal.intensappback.service.impl;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.exception.custom.CandidateAlreadyHasSKillException;
import com.razal.intensappback.exception.custom.CandidateNotFoundException;
import com.razal.intensappback.exception.custom.SkillNameAlreadyExistsException;
import com.razal.intensappback.exception.custom.SkillNotFoundException;
import com.razal.intensappback.repository.CandidateRepository;
import com.razal.intensappback.repository.SkillRepository;
import com.razal.intensappback.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.*;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    SkillRepository skillRepository;


    ///Pre nego sto ga dodam skill kandidatu,proveri da li on vec postoji u tabeli skills
    //Ako postoji vrati mu taj u suprotnom pravi novi
    @Override
    public Candidate addCandidate(Candidate candidate) {
        log.info("Adding new candidate: {}",candidate.getName());
        List<Skill> skills = addSkillsToCandidate(candidate.getSkills());
        candidate.setSkills(skills);
        return candidateRepository.save(candidate);
    }
    private List<Skill> addSkillsToCandidate(List<Skill> newSkills) {
        List<Skill> skills = new ArrayList<>();
        for(Skill skill : newSkills){
            Skill s = getSkill(skill);
            skills.add(s);
        }
        return skills;
    }
    private Skill getSkill(Skill skill) {
        Skill foundSKill = skillRepository.findByName(skill.getName());
        if(foundSKill != null)
            return foundSKill;
        return skill;
    }

    //Proveri da li kandidat vec ima skill koji hoce da mu se dodeli
    @Override
    public Candidate updateCandidateWithSkill(Candidate candidate,Skill skill) {
        log.info("Updating candidate: {} with new skill",candidate.getName());
        if(skill == null || skill.getName() == null)
            throw new SkillNotFoundException("You must provide name for the skill");

        List<Skill> skills = candidate.getSkills();
        for(Skill s : skills){
            if(s.getName().equals(skill.getName()))
                throw new CandidateAlreadyHasSKillException("Candidate: "+candidate.getName()+" already has skill: "+skill.getName()+".Candidate not updated.");
        }
        skills.add(skill);
        skills = addSkillsToCandidate(skills);
        candidate.setSkills(skills);

        return candidateRepository.save(candidate);
    }

    @Override
    public List<Skill> addSkills(List<Skill> skills) {
        log.info("Adding new skills:");
        List<Skill> newSkills = new ArrayList<>();
        for(Skill skill : skills){
            if (skillRepository.existsByName(skill.getName()))
                throw new SkillNameAlreadyExistsException("SKill with the name: "+skill.getName()+" exists. SKills not saved!");
            newSkills.add(skillRepository.save(skill));
        }
        return newSkills;
    }

    @Override
    public Candidate removeSkillFromCandidate(Candidate candidate, Skill skill) {
        //isto update
        if(skill != null){
            candidate.getSkills().remove(skill);
        }
        return candidateRepository.save(candidate);
    }

    @Override
    public Boolean removeCandidate(Long id) {
       log.info("Deleting candidate with an id: {}",id);
       candidateRepository.deleteById(id);
       return TRUE;
    }

    @Override
    public Candidate searchCandidateByName(String name) {
        log.info("Getting candidate with the name: {}",name);
        Candidate candidate = candidateRepository.findByName(name);
        if(candidate == null)
            throw  new CandidateNotFoundException("Candidate with provided name: "+name+" doesnt exist");
        return candidate;
    }

    @Override
    public Set<Candidate> searchAllCandidatesWithGivenSkills(List<Long> skillsIds) {
        List<Candidate> candidates = new ArrayList<>();
        for(Long id : skillsIds){
            Skill skill = skillRepository.findById(id).get();
            candidates.addAll(skill.getCandidates());
        }
        return  new HashSet<>(candidates);
    }

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
}
