package com.razal.intensappback.resource;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import com.razal.intensappback.request.RequestWrapper;
import com.razal.intensappback.response.CustomHttpResponse;
import com.razal.intensappback.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequestMapping("/")
@RestController
@RequiredArgsConstructor
public class CandidateResource {

    @Autowired
    CandidateService service;

    @PostMapping("/addCandidate")
    public ResponseEntity<CustomHttpResponse> addCandidate(@RequestBody @Valid Candidate candidate){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidate", service.addCandidate(candidate)))
                        .msg("Candidate: "+candidate.getName()+" added")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @PostMapping("/addSkills")
    public ResponseEntity<CustomHttpResponse> addSkills(@RequestBody List<Skill> skills){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("skills",service.addSkills(skills)))
                        .msg("Skills added")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @PutMapping("/updateCandidateSkill")
    public ResponseEntity<CustomHttpResponse> updateCandidateWithSkill(@RequestBody RequestWrapper wrapper){
        Candidate candidate = wrapper.getCandidate();
        Skill skill = wrapper.getSkill();
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidate",service.updateCandidateWithSkill(candidate,skill)))
                        .msg("Updated candidate: "+candidate.getName()+" with a new skills")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @PutMapping("/removeSkillFromCandidate")
    public ResponseEntity<CustomHttpResponse> removeSKill(@RequestBody RequestWrapper wrapper){
        Candidate candidate = wrapper.getCandidate();
        Skill skill = wrapper.getSkill();
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidate",service.removeSkillFromCandidate(candidate,skill)))
                        .msg("Removed skill: "+skill.getName() +" from candidate: "+ candidate.getName())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @DeleteMapping("/removeCandidate/{id}")
    public ResponseEntity<CustomHttpResponse> removeCandidate(@PathVariable Long id){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted",service.removeCandidate(id)))
                        .msg("Candidate deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("candidate/{name}")
    public ResponseEntity<CustomHttpResponse> searchCandidate(@PathVariable String name){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidate",service.searchCandidateByName(name)))
                        .msg("Candidate retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/skills/{skillsIds}")
    public ResponseEntity<CustomHttpResponse> searchCandidatesWithSkills(@PathVariable List<Long> skillsIds){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidates",service.searchAllCandidatesWithGivenSkills(skillsIds)))
                        .msg("Candidates retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/all")
    public ResponseEntity<CustomHttpResponse> getAllCandidates(){
        return ResponseEntity.ok(
                CustomHttpResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("candidates",service.getAllCandidates()))
                        .msg("All candidates retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
