package com.razal.intensappback.service.impl;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.exception.custom.CandidateAlreadyHasSKillException;
import com.razal.intensappback.exception.custom.CandidateNotFoundException;
import com.razal.intensappback.exception.custom.SkillNameAlreadyExistsException;
import com.razal.intensappback.repository.CandidateRepository;
import com.razal.intensappback.repository.SkillRepository;
import com.razal.intensappback.service.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    EntityManager entityManager;


    //Pre nego sto ubacim skill proveri da li postoji ako postoji setuj taj u suprotnom pravi novi
    @Override
    public Candidate addCandidate(Candidate candidate) {
        log.info("Adding new candidate: {}",candidate.getName());
        List<Skill> skills = new ArrayList<>();
        addSkillsToCandidate(candidate.getSkills(),skills);
        candidate.setSkills(skills);
        return candidateRepository.save(candidate);
    }

    //Pre nego sto dodam novi skill kandidatu proveri da li ga kandidat vec ima
    //Ako ga nema,pre nego sto ga dodam kandidatu,proveri da li on vec postoji u tabeli skills
    //Ako postoji vrati mu taj u suprotnom pravi novi
    @Override
    public Candidate updateCandidateWithSkill(Candidate candidate) {
        log.info("Updating candidate: {} with new skills",candidate.getName());
        Candidate foundCandidate = candidateRepository.findById(candidate.getId()).orElse(null);
        if(foundCandidate!=null){
            for(Skill skill: candidate.getSkills()){
                findByCandidateIdAndSkillName(foundCandidate,skill.getName());
            }
            addSkillsToCandidate(candidate.getSkills(),foundCandidate.getSkills());
        }else
            throw new CandidateNotFoundException("Candidate with provided id: " + candidate.getId() + " doesnt exist!");

        return candidateRepository.save(foundCandidate);
    }
    private void findByCandidateIdAndSkillName(Candidate candidate,String skillName){
        final String QUERY = "select s.name from candidate_skills cs join skill s on s.id = cs.skill_id where candidate_id = '"+candidate.getId()+"' and s.name = '"+skillName+"'";
        try {
            entityManager.createNativeQuery(QUERY).getSingleResult();
            //ako ne baci NoResultEx znaci da kandidat vec ima taj skill
            throw new CandidateAlreadyHasSKillException("Candidate: "+candidate.getName()+" already has skill: "+skillName + ". Skills not saved");
        }catch (NoResultException ex){
            //uhvati izuzetak i nastavi
        }
    }
    private void addSkillsToCandidate(List<Skill> newSkills,List<Skill> skills) {
        for(Skill skill : newSkills){
            Skill s = getSkill(skill);
            skills.add(s);
        }
    }
    private Skill getSkill(Skill skill) {
        Skill foundSKill = skillRepository.findByName(skill.getName());
        if(foundSKill != null)
            return foundSKill;
        return skill;
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
    public Candidate removeSkillFromCandidate(Candidate candidate, Long skillId) {
        //isto update
        Skill skill = skillRepository.findById(skillId).orElse(null);
        //if skill null..
        candidate.getSkills().remove(skill);
        /*Iterator<Skill> i = skills.iterator();
        while ()*/
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
        //if null..
        return candidate;
    }

    @Override
    public List<Candidate> searchAllCandidatesWithGivenSkills(List<Long> skillsIds) {
        List<Candidate> candidates = new ArrayList<>();

        /*for (Long id:skillsIds) {
            Skill skill = skillRepository.findById(id).get();
            candidates.addAll(skill.getCandidates());
        }*/
        return  candidates;
    }
}
