package com.planner.service;

import com.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private final PlannerRepository plannerRepository;
}
