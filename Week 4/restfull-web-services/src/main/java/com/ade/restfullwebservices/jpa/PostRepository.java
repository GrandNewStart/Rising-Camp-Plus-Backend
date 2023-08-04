package com.ade.restfullwebservices.jpa;

import com.ade.restfullwebservices.post.Post;
import com.ade.restfullwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> { }
