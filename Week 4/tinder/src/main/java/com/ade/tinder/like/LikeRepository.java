package com.ade.tinder.like;

import com.ade.tinder.like.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> { }
