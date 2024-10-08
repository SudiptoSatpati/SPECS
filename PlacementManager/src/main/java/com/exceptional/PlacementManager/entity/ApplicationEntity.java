package com.exceptional.PlacementManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(nullable = false)
    private String phone_no;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String dob;

    @Column(nullable = false)
    private String sex;

    private String course_type;
    //school
    private String pct_10th;
    private String yop_10th;
    private String pct_12th;
    private String yop_12th;
    private String phys_marks_12th;
    private String chem_marks_12th;
    private String math_marks_12th;
    //Diploma
    private String diploma_pct_sem1;
    private String diploma_pct_sem2;
    private String diploma_pct_sem3;
    private String diploma_pct_sem4;
    private String diploma_pct_sem5;
    private String diploma_pct_sem6;
    private String diploma_opct;
    private String yop_diploma;
    //BE
    private String be_sgpa_sem1;
    private String be_sgpa_sem2;
    private String be_sgpa_sem3;
    private String be_sgpa_sem4;
    private String be_sgpa_sem5;
    private String be_sgpa_sem6;
    private String be_sgpa_sem7;
    private String be_sgpa_sem8;
    private String be_cgpa;
    private String be_cpct;
    private String yop_be;
    private String backlogs;
    //--------------------------
    private String resume;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}
