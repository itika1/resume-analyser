package com.example.resumeanalyser.controller;

import com.example.resumeanalyser.dto.JobDescriptionRequest;
import com.example.resumeanalyser.dto.JobDescriptionResponse;
import com.example.resumeanalyser.model.JobDescription;
import com.example.resumeanalyser.repository.JobDescriptionRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/jobs")
public class JobDescriptionController {

  private final JobDescriptionRepository repository;

  public JobDescriptionController(JobDescriptionRepository repository) {
    this.repository = repository;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public JobDescriptionResponse createJob(@Valid @RequestBody JobDescriptionRequest request) {
    JobDescription job = repository.save(new JobDescription(request.getTitle(), request.getDescription()));
    return new JobDescriptionResponse(job.getId(), job.getTitle(), job.getDescription());
  }

  @GetMapping
  public List<JobDescriptionResponse> listJobs() {
    return repository.findAll().stream()
        .map(job -> new JobDescriptionResponse(job.getId(), job.getTitle(), job.getDescription()))
        .toList();
  }

  @GetMapping("/{id}")
  public JobDescriptionResponse getJob(@PathVariable Long id) {
    JobDescription job = repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job description not found"));
    return new JobDescriptionResponse(job.getId(), job.getTitle(), job.getDescription());
  }
}
