package com.ll.sbbrestapi.main.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class ApiMemberController {
    private final MemberService memberService;

    @GetMapping
    public List<Member> getMember() {
        return memberService.findAll();
    }
}
