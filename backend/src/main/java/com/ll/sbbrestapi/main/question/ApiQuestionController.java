package com.ll.sbbrestapi.main.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class ApiQuestionController {
    private final QuestionService questionService;

    @GetMapping
    public List<Question> getQuestions() {
        return questionService.findAll();
    }

    @GetMapping("/detail/{id}")
    public Question getQuestion(Long id) {
        return questionService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteQuestion(Long id) {
        Question question = questionService.findById(id);

        questionService.delete(question);

        Map<String, Object> rsData = new HashMap<>();
        rsData.put("resultCode", "200-1");
        rsData.put("message", "%d번 글 삭제 완료되었습니다".formatted(id));
        return rsData;
    }

    @Getter
    @AllArgsConstructor
    public static class QuestionUpdateReqestBody {
        private String subject;
        private String content;
    }


    @PutMapping("/{id}")
    public Map<String, Object> updateQuestion(
            @PathVariable Long id,
            @RequestBody QuestionUpdateReqestBody questionUpdateReqestBody
    ) {
        Question question = questionService.findById(id);

        questionService.update(question, questionUpdateReqestBody.getSubject(), questionUpdateReqestBody.getContent());

        Map<String, Object> rsData = new HashMap<>();
        rsData.put("resultCode", "200-1");
        rsData.put("message", "%d번 글 수정 완료되었습니다".formatted(id));
        return rsData;
    }
}