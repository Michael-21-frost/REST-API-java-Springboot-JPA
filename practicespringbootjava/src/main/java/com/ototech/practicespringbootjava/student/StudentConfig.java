package com.ototech.practicespringbootjava.student;

//DATABASE OBJECTS
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
         Student david =  new Student(
                    "David",
                    "david@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY, 5)


            );
            Student deborah =  new Student(
                    "Deborah",
                    "debbie@gmail.com",
                    LocalDate.of(1989, Month.MARCH, 3)


            );
            Student yinka =  new Student(
                    "Yinka",
                    "yinka@gmail.com",
                    LocalDate.of(2003, Month.AUGUST, 8)


            );
            repository.saveAll(
                    List.of(david, deborah, yinka)
            );

        };
    }

}
