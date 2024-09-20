package com.example.hospital_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Doctor {

    private int doctorId;
    private String doctorName;
    private String email;
    private String designation;
}