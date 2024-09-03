package com.exceptional.PlacementManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Use for getter() and setter() functions
@AllArgsConstructor // Use for the constructor
@NoArgsConstructor // By default constructor
public class StudentDto {
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

    private School school;
    private Diploma diploma;
    private BE be;

    private String pref_job_roles;
    private String prof_exp;
    private String linked_in;
    private String github;
    private String resume;
}
