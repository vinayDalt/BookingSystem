package com.domain.exercise.theatreManagement.repository;

import com.domain.exercise.theatreManagement.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {}
