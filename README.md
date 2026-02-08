# AI Resume Analyzer & Job Match API

Spring Boot API for parsing resumes, extracting skills, and matching them against job descriptions.

## Features
- Upload resumes with multipart file handling.
- AI-style skill extraction via a lightweight skill dictionary.
- Match scoring and gap analysis for job descriptions stored in an H2 database.
- Improvement suggestions tailored to missing skills.

## Quick Start

```bash
mvn spring-boot:run
```

### Create a job description
```bash
curl -X POST http://localhost:8080/api/jobs \
  -H "Content-Type: application/json" \
  -d '{"title":"Backend Engineer","description":"Spring Boot, Java, REST, SQL, Docker"}'
```

### Analyze a resume
```bash
curl -X POST "http://localhost:8080/api/resumes/analyze?jobId=1" \
  -F "file=@/path/to/resume.pdf"
```
