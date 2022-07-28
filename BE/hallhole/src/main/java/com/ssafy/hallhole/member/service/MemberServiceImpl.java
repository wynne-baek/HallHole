package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Long join(Member member){

        // 이메일 중복여부 확인
        duplicateMember(member.getEmail());

        // 홀홀태그 부여
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        boolean tagFlag=true;
        String tag="";
        while(tagFlag){
            String tmpTag = "";
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                idx = (int) (charSet.length * Math.random());
                tmpTag += charSet[idx];
            }

            // 태그 중복여부 확인
            if(memberRepository.findByIdTag(tmpTag)) {
                tagFlag = true;
                tag += tmpTag;
            }
        }

        member.addIdTag(tag);

        // 입력받은 정보 저장
        memberRepository.save(member);

        // return id
        return member.getId();
    }

    @Override
    public void duplicateMember(String email){
        Member findMember = memberRepository.findByEmail(email);
        if(findMember!=null){ // 중복 이메일 존재 시
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }


}
