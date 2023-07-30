package com.ade.learnjpaandhibernate.course;

import com.ade.learnjpaandhibernate.course.jdbc.CourseJDBCRepository;
import com.ade.learnjpaandhibernate.course.jpa.CourseJPARepository;
import com.ade.learnjpaandhibernate.course.models.Course;
import com.ade.learnjpaandhibernate.course.springjpadata.CourseSpringJPADataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    private CourseJDBCRepository repository;

//    @Autowired
//    private CourseJPARepository repository;

    @Autowired
    private CourseSpringJPADataRepository repository;

    @Override
    public void run(String... args) throws Exception {
//        repository.insert(new Course(1, "Learn AWS Now", "Jeff Bezos"));
//        repository.insert(new Course(2, "Learn Azure Now", "Satya Nadela"));
//        repository.insert(new Course(3, "Learn DevOps Now", "Jinwoo"));
        repository.save(new Course(1, "Learn AWS Now", "Jeff Bezos"));
        repository.save(new Course(2, "Learn Azure Now", "Satya Nadela"));
        repository.save(new Course(3, "Learn DevOps Now", "Jinwoo"));
//        repository.deleteById(3);
        repository.deleteById(3L);
//        System.out.println(repository.findById(1));
//        System.out.println(repository.findById(2));
        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(2L));
        System.out.println(repository.findAll());
        System.out.println(repository.count());
        System.out.println(repository.findByAuthor("Jeff Bezos"));
        System.out.println(repository.findByAuthor(""));
        System.out.println(repository.findByName("Learn Azure Now "));
        System.out.println(repository.findByName(""));
    }

}
