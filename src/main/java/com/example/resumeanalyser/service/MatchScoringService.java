package com.example.resumeanalyser.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MatchScoringService {

  public double calculateScore(List<String> resumeSkills, List<String> jobSkills) {
    if (jobSkills.isEmpty()) {
      return 0.0;
    }
    long matched = jobSkills.stream().filter(resumeSkills::contains).count();
    return Math.round((matched * 100.0 / jobSkills.size()) * 10.0) / 10.0;
  }

  public Map<String, Double> buildSkillGaps(List<String> resumeSkills, List<String> jobSkills) {
    Map<String, Double> gaps = new LinkedHashMap<>();
    for (String skill : jobSkills) {
      if (!resumeSkills.contains(skill)) {
        gaps.put(skill, 1.0);
      }
    }
    return gaps;
  }
}
