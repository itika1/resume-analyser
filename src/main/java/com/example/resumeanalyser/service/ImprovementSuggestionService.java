package com.example.resumeanalyser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ImprovementSuggestionService {

  public List<String> buildSuggestions(Map<String, Double> skillGaps) {
    List<String> suggestions = new ArrayList<>();
    if (skillGaps.isEmpty()) {
      suggestions.add("Highlight measurable achievements that align with the job requirements.");
      suggestions.add("Add impact metrics (e.g., time saved, revenue generated) to key projects.");
      return suggestions;
    }
    suggestions.add("Add or emphasize the following missing skills: " + String.join(", ", skillGaps.keySet()) + ".");
    suggestions.add("Include recent projects that demonstrate the missing skills at production scale.");
    suggestions.add("Quantify outcomes for each relevant project to strengthen alignment with the role.");
    return suggestions;
  }
}
