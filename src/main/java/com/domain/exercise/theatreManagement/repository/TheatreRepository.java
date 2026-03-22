package com.domain.exercise.theatreManagement.repository;

import com.domain.exercise.theatreManagement.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {}
