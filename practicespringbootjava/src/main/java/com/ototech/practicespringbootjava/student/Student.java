//student class
package com.ototech.practicespringbootjava.student;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;


//mapping class to database
@Entity //this decorator will map a class to the database
@Table // to create a table in the SQL database
public class Student {
    @Id
    @SequenceGenerator(
            name="student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    //class properties
    private Long id;
    private String name;
    private LocalDate dob;
    private String email;
    @Transient
    private Integer age;


    //generating our constructors from the student class
    //empty default  constructor
    public Student(){


    }
    //second constructor to lead to database
    public Student(String name, String email, LocalDate dob){
        //default id
        this.name=name;
        this.email=email;
        this.dob=dob;


    }
    //creating the getters and setters for the constructor above
    //get and set id
    public long getId(){
        return  id;
    }
    public void setId(Long id){
        this.id=id;
    }
    //get and set name
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    //get and set email
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    //get and set dob
    public LocalDate getDob(){
        return dob;
    }
    public void setDob(LocalDate dob){
        this.dob=dob;
    }
    //get and set age
    public Integer getAge(){
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
    public void setAge(Integer age){
        this.age=age;
    }


    //annotate the data properties to string
    //

    @Override
    public String toString(){
        return  "Student{"+
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                '}';


    }


}
