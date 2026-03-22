package com.domain.exercise.theatreManagement.repository;

import com.domain.exercise.theatreManagement.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {}
