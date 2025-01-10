package com.ll.sbbrestapi.main.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member join(String username, String password, String nickname) {
        Member member = Member.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
        return this.memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}
