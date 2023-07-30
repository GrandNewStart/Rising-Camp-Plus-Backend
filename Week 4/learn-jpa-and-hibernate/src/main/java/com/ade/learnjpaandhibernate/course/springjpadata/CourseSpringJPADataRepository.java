package com.ade.learnjpaandhibernate.course.springjpadata;

import com.ade.learnjpaandhibernate.course.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseSpringJPADataRepository extends JpaRepository<Course, Long> {

    List<Course> findByAuthor(String author);
    List<Course> findByName(String name);

}
