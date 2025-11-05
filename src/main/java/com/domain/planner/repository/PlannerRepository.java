package com.domain.planner.repository;

import com.domain.planner.entity.Planner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlannerRepository extends JpaRepository<Planner, Long> {
}
