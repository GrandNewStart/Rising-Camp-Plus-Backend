package com.ade.learnjpaandhibernate.course.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJDBCRepository {

    @Autowired
    private JdbcTemplate template;

    private static final String INSERT_QUERY =
            """
                insert into course (id, name, author)
                values (1, 'Learn AWS', 'Jeff Bezos');
            """;

    public void insert() {
        template.update(INSERT_QUERY);
    }

}
