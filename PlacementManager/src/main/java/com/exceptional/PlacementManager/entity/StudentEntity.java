package com.exceptional.PlacementManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String address;
    private String phone_no;
    private String roll_no;
    private String registration_no;
    private String dept;
    private String dob;
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
    private String diploma_sgpa_sem1;
    private String diploma_sgpa_sem2;
    private String diploma_sgpa_sem3;
    private String diploma_sgpa_sem4;
    private String diploma_sgpa_sem5;
    private String diploma_sgpa_sem6;
    private String diploma_ogpa;
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
    private String be_pct_sem1;
    private String be_pct_sem2;
    private String be_pct_sem3;
    private String be_pct_sem4;
    private String be_pct_sem5;
    private String be_pct_sem6;
    private String be_pct_sem7;
    private String be_pct_sem8;
    private String be_opct;
    private String yop_be;
    private String backlogs;
    private String back_papers;
    //--------------------------
    private String pref_job_roles;
    private String prof_exp;
    private String linked_in;
    private String github;
    private String resume;
}
