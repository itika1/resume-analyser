package com.example.resumeanalyser.controller;

import com.example.resumeanalyser.dto.ResumeAnalysisResponse;
import com.example.resumeanalyser.model.JobDescription;
import com.example.resumeanalyser.repository.JobDescriptionRepository;
import com.example.resumeanalyser.service.ImprovementSuggestionService;
import com.example.resumeanalyser.service.MatchScoringService;
import com.example.resumeanalyser.service.ResumeParsingService;
import com.example.resumeanalyser.service.SkillExtractionService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

  private final ResumeParsingService parsingService;
  private final SkillExtractionService skillExtractionService;
  private final MatchScoringService matchScoringService;
  private final ImprovementSuggestionService suggestionService;
  private final JobDescriptionRepository repository;

  public ResumeController(ResumeParsingService parsingService,
                          SkillExtractionService skillExtractionService,
                          MatchScoringService matchScoringService,
                          ImprovementSuggestionService suggestionService,
                          JobDescriptionRepository repository) {
    this.parsingService = parsingService;
    this.skillExtractionService = skillExtractionService;
    this.matchScoringService = matchScoringService;
    this.suggestionService = suggestionService;
    this.repository = repository;
  }

  @PostMapping("/analyze")
  public ResumeAnalysisResponse analyzeResume(@RequestParam Long jobId,
                                              @RequestPart("file") MultipartFile file) {
    JobDescription job = repository.findById(jobId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job description not found"));

    String resumeText;
    try {
      resumeText = parsingService.extractText(file);
    } catch (IOException ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to parse resume file", ex);
    }

    List<String> resumeSkills = skillExtractionService.extractSkills(resumeText);
    List<String> jobSkills = skillExtractionService.extractSkills(job.getDescription());

    double score = matchScoringService.calculateScore(resumeSkills, jobSkills);
    Map<String, Double> gaps = matchScoringService.buildSkillGaps(resumeSkills, jobSkills);
    List<String> suggestions = suggestionService.buildSuggestions(gaps);

    return new ResumeAnalysisResponse(job.getTitle(), score, gaps, resumeSkills, suggestions);
  }
}
