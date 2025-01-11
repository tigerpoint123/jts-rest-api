package com.ll.sbbrestapi.main.question;

import com.ll.sbbrestapi.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
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
    public RsData deleteQuestion(Long id) {
        Question question = questionService.findById(id);

        questionService.delete(question);

        return new RsData(
                "200-1",
                "%d번 글 삭제 완료되었습니다".formatted(id)
        );
    }

    record QuestionUpdateReqestBody (
            @NotBlank @Length(min = 2) String subject,
            @NotBlank @Length(min = 2) String content
    ) {}

    @PutMapping("/{id}")
    public RsData updateQuestion(
            @PathVariable Long id,
            @RequestBody @Valid QuestionUpdateReqestBody questionUpdateReqestBody
    ) {
        Question question = questionService.findById(id);
        questionService.update(question, questionUpdateReqestBody.subject, questionUpdateReqestBody.content);

        return new RsData(
                "200-1",
                "%d번 글 수정 완료되었습니다".formatted(id)
        );
    }
}
