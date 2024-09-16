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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String job_location;

    @Column(nullable = false)
    private String job_role;

    @Column(nullable = false)
    private String job_type;

    @Column(nullable = false)
    private String offered_ctc;

    private String criteria;

    private List<String> departments;

    private String additional_info;

    private String arriving_time;

    private List<String> selected_candidates;

    @ManyToOne
    @JoinColumn(name = "college_id", nullable = false)
    private CollegeEntity college;

}
