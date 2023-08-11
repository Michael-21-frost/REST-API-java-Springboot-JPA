//service/business layer to fetch data from database(respository) to client
package com.ototech.practicespringbootjava.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    //declaring student repository

    private final StudentRepository studentrepository;
    @Autowired //to inject dependacies from repository
    //creating a constructor to instantiate the student repository
    public StudentService( StudentRepository studentrepository){
        this.studentrepository=studentrepository;
    }
            //http services
    public List<Student> getStudents(){
        //import data from repository (database)
        //to be injected into the controller or api layer
        return studentrepository.findAll();


    }
    //method to add new student to Student class
    public void addNewStudent(Student student)
    {
        Optional<Student> studentOptional=studentrepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("This email already has an account!");
        }
        //System.out.println(student);//to test the data for the new student
        //save student to the repository(database)
        studentrepository.save(student);
    }
        //delete a student from the database
    public void deleteStudent(Long studentId) {
        boolean exists =studentrepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("The  student with the ID " + studentId +" does not exist");
        }
        studentrepository.deleteById(studentId);

    }
    @Transactional // this prevents us from using queries
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentrepository.findById(studentId).orElseThrow(()-> new IllegalStateException(
                "The  student with the ID " + studentId +" does not exist"));
        if (name != null && name.length()> 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional=studentrepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("This email is taken!");
            }
            student.setEmail(email);

        }


    }
}
