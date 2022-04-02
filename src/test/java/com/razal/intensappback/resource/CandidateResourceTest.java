package com.razal.intensappback.resource;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.response.CustomHttpResponse;
import com.razal.intensappback.service.impl.CandidateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateResourceTest {

    @Mock
    CandidateServiceImpl service;

    @InjectMocks
    CandidateResource resource;

    @Test
    void addCandidate() {

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

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(service.addCandidate(any(Candidate.class))).thenReturn(candidate);
        ResponseEntity<CustomHttpResponse> responseEntity = resource.addCandidate(candidate);

        assertThat(responseEntity.getBody().getStatusCode()).isEqualTo(201);
        assertThat(responseEntity.getBody().getData().get("candidate")).isEqualTo(candidate);

    }

    @Test
    void addSkills() {
    }

    @Test
    void updateCandidateWithSkill() {
    }

    @Test
    void removeSKill() {
    }

    @Test
    void removeCandidate() {
    }

    @Test
    void searchCandidate() {
    }

    @Test
    void searchCandidatesWithSkills() {
    }
}