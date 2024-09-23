package com.example.hospital_management.controller;

import com.example.hospital_management.model.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/doctor/api")
public class DoctorController {

    // debugging - it is the process of understanding the flow of application

    // here we are storing the doctor data inside hashmap
    Map<Integer, Doctor> doctorMap = new HashMap<Integer, Doctor>();
    // 1 -> {1,sanjay,sanjay@gmail.com,cardiologist}
    // 2 -> {2,sanjay,sanjay@gmail.com,cardiologist}
    // 3 -> {3,sanjay,sanjay@gmail.com,cardiologist}

    // REST - representation state transfer API

    // @RequestBody - which takes the request from front end and passed it to the API
    @PostMapping("/save")
    public String saveDoctor(@RequestBody Doctor doctorRequest){
        doctorMap.put(doctorRequest.getDoctorId(),doctorRequest);
        return "Doctor saved";
    }

    @GetMapping("/findAll")
    public Map<Integer, Doctor> getAllDoctor(){
        return doctorMap;
    }

    // @PathVariable - take the input in the url itself
    @GetMapping("/find/{doctorid}")
    public Doctor getDoctorById(@PathVariable("doctorid") int doctorId){
        Doctor doctor = doctorMap.get(doctorId);
        return doctor;
    }

    // @RequestParam - it takes the input inside request parameter
    @GetMapping("/findByName")
    public Doctor getDoctorByName(@RequestParam("doctorName") String doctorName){
        for(Doctor d1 : doctorMap.values()){
            if(d1.getDoctorName().equalsIgnoreCase(doctorName)){
                return d1;
            }
        }
        return null;
    }

    @GetMapping("/findByNameAndDesignation")
    public Doctor getDoctorByNameAndDesignation(@RequestParam("doctorName") String doctorName, @RequestParam("designation") String designation){
        for(Doctor d1 : doctorMap.values()){
            if(d1.getDoctorName().equalsIgnoreCase(doctorName) && d1.getDesignation().equalsIgnoreCase(designation)){
                return d1;
            }
        }
        return null;
    }


    @DeleteMapping("/delete/{doctorid}")
    public String deleteDoctorById(@PathVariable("doctorid") int doctorId){
        doctorMap.remove(doctorId);
        return "Doctor deleted";
    }

    // PUT - When you use a PUT request, you are telling the server to completely replace the existing data with the new data you provide.
    // in put we have send whole object as input request
    @PutMapping("/update/{doctorid}")
    public String updateDoctorUsingPut(@RequestBody Doctor updatedDoctorRequest, @PathVariable("doctorid") int doctorId){
        // always in update find first with id
        // if present update it else not
        if(doctorMap.containsKey(doctorId)){
            Doctor oldDoctor = doctorMap.get(doctorId);
            oldDoctor.setDoctorId(updatedDoctorRequest.getDoctorId());
            oldDoctor.setDoctorName(updatedDoctorRequest.getDoctorName());
            oldDoctor.setDesignation(updatedDoctorRequest.getDesignation());
            oldDoctor.setEmail(updatedDoctorRequest.getEmail());
            doctorMap.put(doctorId,oldDoctor);
            return "Doctor updated";
        }else{
            return "doctor is not found";
        }
    }

    //PATCH - This means you only need to send the data that you want to change, without affecting the rest of the resource.
    @PatchMapping("/update/{doctorid}")
    public String updateDoctorUsingPatch(@PathVariable("doctorid") int doctorId, @RequestParam("doctorName") String doctorName, @RequestParam("designation") String designation){
        // always in update find first with id
        // if present update it else not
        if(doctorMap.containsKey(doctorId)){
            Doctor oldDoctor = doctorMap.get(doctorId);
            oldDoctor.setDoctorName(doctorName);
            oldDoctor.setDesignation(designation);
            doctorMap.put(doctorId,oldDoctor);
            return "Doctor updated";
        }else{
            return "doctor is not found";
        }
    }
}
