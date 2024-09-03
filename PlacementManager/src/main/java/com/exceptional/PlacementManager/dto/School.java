package com.exceptional.PlacementManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    private String pct_10th;
    private String yop_10th;
    private String pct_12th;
    private String yop_12th;
    private String phys_marks_12th;
    private String chem_marks_12th;
    private String math_marks_12th;
}
