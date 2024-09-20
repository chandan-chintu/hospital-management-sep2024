package com.example.hospital_management.controller;

import com.example.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor/api")
public class DoctorController {

    // here we are storing the doctor data inside hashmap
    Map<Integer, Doctor> doctorMap = new HashMap<Integer, Doctor>();
    // 1 -> {1,sanjay,sanjay@gmail.com,cardiologist}
    // 2 -> {2,sanjay,sanjay@gmail.com,cardiologist}
    // 3 -> {3,sanjay,sanjay@gmail.com,cardiologist}

    // @RequestBody - which takes the request from front end and passed it to the API
    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctorRequest){
        doctorMap.put(doctorRequest.getDoctorId(),doctorRequest);
        return "Doctor saved";
    }

}
