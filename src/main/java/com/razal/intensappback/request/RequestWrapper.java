package com.razal.intensappback.request;

import com.razal.intensappback.domain.Candidate;
import com.razal.intensappback.domain.Skill;
import lombok.Data;

@Data
public class RequestWrapper {
    Candidate candidate;
    Skill skill;
}
