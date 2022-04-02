package com.razal.intensappback.resource;

import com.razal.intensappback.IntensAppBackApplication;
import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.response.CustomHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;

@SpringBootTest(classes = IntensAppBackApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CandidateResourceIntegrationTest {

    @LocalServerPort
    int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    void addCandidate() {

        Candidate candidate = new Candidate();
        Date dateOfBirth = new Date();
        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill();
        skill.setName("GLUMA");
        skills.add(skill);

        candidate.setName("Srecko Soic");
        candidate.setDateOfBirth(dateOfBirth);
        candidate.setEmail("srecko@gmail.com");
        candidate.setContactNumber("+3816555333");
        candidate.setSkills(skills);

        HttpEntity<Candidate> entity = new HttpEntity<>(candidate,headers);

        ResponseEntity<CustomHttpResponse> response = restTemplate.exchange(
                URI.create("http://localhost:9999/addCandidate"),
                HttpMethod.POST,
                entity,
                CustomHttpResponse.class
        );
        System.out.println(response.toString());
        assertThat(response.getBody().getStatusCode()).isEqualTo(CREATED.value());
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