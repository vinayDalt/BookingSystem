package com.domain.exercise.theatreManagement.repository;

import com.domain.exercise.theatreManagement.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {}
