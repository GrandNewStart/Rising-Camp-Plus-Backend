package com.ade.learnjpaandhibernate.course;

import com.ade.learnjpaandhibernate.course.jdbc.CourseJDBCRepository;
import com.ade.learnjpaandhibernate.course.jpa.CourseJPARepository;
import com.ade.learnjpaandhibernate.course.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    private CourseJDBCRepository repository;

    @Autowired
    private CourseJPARepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Learn AWS Now", "Jeff Bezos"));
        repository.insert(new Course(2, "Learn Azure Now", "Satya Nadela"));
        repository.insert(new Course(3, "Learn DevOps Now", "Jinwoo"));
        repository.deleteById(3);
        System.out.println(repository.findById(1));
        System.out.println(repository.findById(2));
    }

}
