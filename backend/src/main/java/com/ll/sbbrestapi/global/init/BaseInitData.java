package com.ll.sbbrestapi.global.init;


import com.ll.sbbrestapi.main.member.Member;
import com.ll.sbbrestapi.main.member.MemberService;
import com.ll.sbbrestapi.main.question.Question;
import com.ll.sbbrestapi.main.question.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    private final QuestionService questionService;
    private final MemberService memberService;

    @Autowired
    @Lazy
    private BaseInitData self;

    @Bean
    public ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.init();
            self.init2();
        };
    }

    @Transactional
    public void init() {
        if (!questionService.findAll().isEmpty()) return;

        Question question1 = questionService.create("Question 1", "Content 1");
        Question question2 = questionService.create("Question 2", "Content 2");
        Question question3 = questionService.create("Question 3", "Content 3");
        Question question4 = questionService.create("Question 4", "Content 4");
        Question question5 = questionService.create("Question 5", "Content 5");
    }

    @Transactional
    public void init2() {
        if (!memberService.findAll().isEmpty()) return;

        Member memberSystem = memberService.join("system", "1234", "시스템");
        Member memberAdmin = memberService.join("admin", "1234", "관리자");
        Member memberUser1 = memberService.join("user1", "1234", "유저1");
        Member memberUser2 = memberService.join("user2", "1234", "유저2");
        Member memberUser3 = memberService.join("user3", "1234", "유저3");
        Member memberUser4 = memberService.join("user4", "1234", "유저4");
        Member memberUser5 = memberService.join("user5", "1234", "유저5");
    }
}
