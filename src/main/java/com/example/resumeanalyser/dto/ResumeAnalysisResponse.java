package com.example.resumeanalyser.dto;

import java.util.List;
import java.util.Map;

public class ResumeAnalysisResponse {
  private String jobTitle;
  private double matchScore;
  private Map<String, Double> skillGaps;
  private List<String> extractedSkills;
  private List<String> improvementSuggestions;

  public ResumeAnalysisResponse(String jobTitle, double matchScore, Map<String, Double> skillGaps,
                                List<String> extractedSkills, List<String> improvementSuggestions) {
    this.jobTitle = jobTitle;
    this.matchScore = matchScore;
    this.skillGaps = skillGaps;
    this.extractedSkills = extractedSkills;
    this.improvementSuggestions = improvementSuggestions;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public double getMatchScore() {
    return matchScore;
  }

  public Map<String, Double> getSkillGaps() {
    return skillGaps;
  }

  public List<String> getExtractedSkills() {
    return extractedSkills;
  }

  public List<String> getImprovementSuggestions() {
    return improvementSuggestions;
  }
}
