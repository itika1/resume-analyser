package com.example.resumeanalyser.repository;

import com.example.resumeanalyser.model.JobDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
}
