package com.ade.learnjpaandhibernate.course.jdbc;

import com.ade.learnjpaandhibernate.course.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJDBCRepository {

    @Autowired
    private JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                insert into course (id, name, author)
                values (?, ?, ?);
            """;

    private static final String DELETE_QUERY =
            """
                delete from course 
                where id = ?
             """;

    private static final String SELECT_QUERY =
            """
                select * from course
                where id = ?
            """;

    public void insert(Course course) {
        this.template.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(long id) {
        this.template.update(DELETE_QUERY, id);
    }

    public Course findById(long id) {
        return this.template.queryForObject(
                SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class),
                id
        );
    }

}
