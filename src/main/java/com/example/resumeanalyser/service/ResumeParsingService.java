package com.example.resumeanalyser.service;

import java.io.IOException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeParsingService {

  private final Tika tika = new Tika();

  public String extractText(MultipartFile file) throws IOException {
    try {
      return tika.parseToString(file.getInputStream());
    } catch (TikaException ex) {
      throw new IOException("Unable to extract text from resume file", ex);
    }
  }
}
