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
    private String criteria;
    private List<String> departments;
    private String additional_info;
    private String arriving_time;
    private List<String> selected_candidates;
}
