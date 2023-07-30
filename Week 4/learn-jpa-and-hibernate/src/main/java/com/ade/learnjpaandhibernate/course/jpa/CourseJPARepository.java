package com.ade.learnjpaandhibernate.course.jpa;

import com.ade.learnjpaandhibernate.course.models.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJPARepository {

//    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Course course) {
        this.entityManager.merge(course);
    }

    public void deleteById(long id) {
        Course course = this.entityManager.find(Course.class ,id);
        this.entityManager.remove(course);
    }

    public Course findById(long id) {
        return this.entityManager.find(Course.class, id);
    }
}


