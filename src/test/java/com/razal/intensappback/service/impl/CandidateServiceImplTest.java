package com.razal.intensappback.service.impl;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.exception.custom.CandidateNotFoundException;
import com.razal.intensappback.repository.CandidateRepository;
import com.razal.intensappback.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class) // for opening mocks
class CandidateServiceImplTest {

    @Mock
    CandidateRepository candidateRepository;
    @Mock
    SkillRepository  skillRepository;
    @InjectMocks
    CandidateServiceImpl service;

    @Test
    void canAddCandidate() {
        //given
        Candidate candidate = new Candidate();
        Date dateOfBirth = new Date();
        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setName("GLUMA");

        candidate.setName("Srecko Soic");
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setEmail("srecko@gmail.com");
        candidate.setContactNumber("adadadadsd");
        candidate.setSkills(skills);

        //when - sta se testira
        service.addCandidate(candidate);

        //then - assert
        ArgumentCaptor<Candidate> candidateArgumentCaptor =
                ArgumentCaptor.forClass(Candidate.class);
        verify(candidateRepository).save(candidateArgumentCaptor.capture());
        Candidate capturedCandidate = candidateArgumentCaptor.getValue();

        assertThat(capturedCandidate).isEqualTo(candidate);
    }
    @Test
    void privateCanGetSkill() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Skill skill = new Skill();
        skill.setName("GLUMA");
        Method privateMethodGetSkill = service.getClass().getDeclaredMethod("getSkill", Skill.class);
        privateMethodGetSkill.setAccessible(true);

        when(skillRepository.findByName(Mockito.anyString())).thenReturn(skill);
        Skill foundSKill = (Skill) privateMethodGetSkill.invoke(service,skill);
        assertThat(foundSKill.getName()).isEqualTo(skill.getName());
    }
    @Test
    void privateCanNotGetSkill() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Skill skill = new Skill();
        skill.setName("GLUMA");
        Method privateMethodGetSkill = service.getClass().getDeclaredMethod("getSkill", Skill.class);
        privateMethodGetSkill.setAccessible(true);

        Mockito.when(skillRepository.findByName(Mockito.anyString())).thenReturn(null);
        Skill foundSKill = (Skill) privateMethodGetSkill.invoke(service,skill);
        assertThat(foundSKill.getName()).isEqualTo(skill.getName());
    }


    @Test
    void updateCandidateWithSkill() {
    }

    @Test
    void addSkills() {
    }

    @Test
    void removeSkillFromCandidate() {
    }

    @Test
    void removeCandidate() {
    }

    @Test
    void canSearchCandidateByName() {
        Candidate candidate = new Candidate();
        Date dateOfBirth = new Date();
        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setName("GLUMA");

        candidate.setName("Srecko Soic");
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setEmail("srecko@gmail.com");
        candidate.setContactNumber("adadadadsd");
        candidate.setSkills(skills);

        when(candidateRepository.findByName(Mockito.anyString())).thenReturn(candidate);
        Candidate newCandidate = service.searchCandidateByName("Srecko Soic");
        assertThat("Srecko Soic").isEqualTo(newCandidate.getName());

    }
    @Test
    void canSearchCandidateByNameThrowNotFound() {
        Candidate candidate = new Candidate();
        Date dateOfBirth = new Date();
        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setName("GLUMA");

        candidate.setName("Srecko Soic");
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setEmail("srecko@gmail.com");
        candidate.setContactNumber("adadadadsd");
        candidate.setSkills(skills);

        when(candidateRepository.findByName(Mockito.anyString())).thenReturn(null);
        assertThatThrownBy(()->service.searchCandidateByName(candidate.getName()))
                .isInstanceOf(CandidateNotFoundException.class);
    }

    @Test
    void searchAllCandidatesWithGivenSkills() {
    }
}