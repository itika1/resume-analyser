package com.example.resumeanalyser.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class SkillExtractionService {

  private static final List<String> SKILL_DICTIONARY = List.of(
      "java", "spring", "spring boot", "kotlin", "python", "javascript", "typescript",
      "react", "node", "sql", "postgresql", "mysql", "mongodb", "aws", "azure",
      "docker", "kubernetes", "terraform", "git", "ci/cd", "rest", "graphql",
      "microservices", "agile", "scrum", "machine learning", "nlp", "data analysis",
      "html", "css", "linux", "jira", "pytest", "junit", "hibernate"
  );

  public List<String> extractSkills(String text) {
    if (text == null || text.isBlank()) {
      return List.of();
    }
    String normalized = text.toLowerCase(Locale.ROOT);
    Set<String> matches = new LinkedHashSet<>();
    for (String skill : SKILL_DICTIONARY) {
      if (normalized.contains(skill)) {
        matches.add(skill);
      }
    }
    return new ArrayList<>(matches);
  }
}
