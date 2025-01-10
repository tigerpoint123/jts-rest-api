package com.ll.sbbrestapi.main.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question create(String subject, String content) {
        Question question = Question.builder()
                .subject(subject)
                .content(content)
                .build();
        return questionRepository.save(question);
    }

    public List<Question> findAll() {
        return this.questionRepository.findAll();
    }

    public Question findById(Long id) {
        return this.questionRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new IllegalArgumentException("Question not found"));
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void update(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        this.questionRepository.save(question);
    }
}
