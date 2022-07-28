package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        memberRepository.save(member);
        return member.getId();
    }

    public void duplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }

    public List<Member> findMembers() { return memberRepository.findAll(); }
    public Member findMember(Long id){
        return memberRepository.findOne(id);
    }

}
