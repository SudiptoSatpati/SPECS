package com.exceptional.PlacementManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "job_offers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String job_location;

    @Column(nullable = false)
    private String job_role;

    @Column(nullable = false)
    private String job_type;

    @Column(nullable = false)
    private String offered_ctc;

    private List<String> job_description;

    private List<String> requirements;

    private List<String> responsibilities;

    private List<String> qualifications;

    private List<String> salary_details;

    private List<String> departments;

    private List<String> additional_info;

    private String posted;

    private String expires;

    private List<String> selected_candidates;

    @ManyToOne
    @JoinColumn(name = "college_id", nullable = false)
    private CollegeEntity college;

}
