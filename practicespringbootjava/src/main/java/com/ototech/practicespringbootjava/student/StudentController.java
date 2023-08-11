
//api layer to send JSON dat to client
package com.ototech.practicespringbootjava.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List; 

@RestController // binding annotation to create api layer


public class StudentController {
    private  final StudentService studentservice;
    // call and assign service from the Student service layer

    @Autowired // to inject dependencies from the service component
    //constructor to create a student service object
    public StudentController(StudentService studentservice) {
        this.studentservice=studentservice;
    }
    @GetMapping("api/v1/students") // to map data to client
    //send data to client
    public List<Student> getStudents(){
        return this.studentservice.getStudents();
    }
    //this will add a student into the database
    @PostMapping("api/v1/students")
    public void registerNewStudent(@RequestBody Student student){
        studentservice.addNewStudent(student);
    }
    //this will delete a student from the database
    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentservice.deleteStudent(studentId);

    }
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentservice.updateStudent(studentId, name, email);
    }



}
