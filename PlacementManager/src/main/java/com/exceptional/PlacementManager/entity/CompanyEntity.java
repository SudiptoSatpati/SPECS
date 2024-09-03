package com.exceptional.PlacementManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "companies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String job_location;
    private String job_role;
    private String job_type;
    private String offered_ctc;
    private String criteria;
    private List<String> streams;
    private String additional_info;
    private String arriving_time;
    private List<String> selected_candidates;

}
