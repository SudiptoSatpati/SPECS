package com.exceptional.PlacementManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDto {
    private Long id;
    private String company;
    private String logo;
    private String job_description;
    private String job_location;
    private String job_role;
    private String job_type;
    private String offered_ctc;
    private String requirements;
    private String responsibilities;
    private String qualifications;
    private String salary_details;
    private List<String> departments;
    private String additional_info;
    private String posted;
    private String expires;
    private List<String> selected_candidates;
}
