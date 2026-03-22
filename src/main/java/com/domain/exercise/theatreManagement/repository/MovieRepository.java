package com.domain.exercise.theatreManagement.repository;

import com.domain.exercise.theatreManagement.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {}
