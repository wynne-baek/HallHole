package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.mail.MailService;
import com.ssafy.hallhole.member.domain.Gender;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.CharacterDTO;
import com.ssafy.hallhole.member.dto.MemberJoinDTO;
import com.ssafy.hallhole.member.dto.MyProfileDTO;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailService mailService;



    @Override
    public Member join(MemberJoinDTO m){

        Member member = new Member(m.getEmail(),m.getName(),m.getPw());
        duplicateMember(member.getEmail());
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        boolean tagFlag=true;
        while(tagFlag){
            String tmpTag = "";
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                idx = (int) (charSet.length * Math.random());
                tmpTag += charSet[idx];
            }

            if(memberRepository.findByIdTag(tmpTag)==null) {
                tagFlag = false;
                member.setIdTag(tmpTag);
            }
        }
        memberRepository.save(member);
//        mailService.sendCongMail(member); // 테스트 데이터를 넣기 위해 지움. 나중에 풀기
        return member;
    }

    @Override
    public void duplicateMember(String email){
        if(memberRepository.findByEmail(email)!=null) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }

    @Override
    public void findPW(String email) {
        Member member = memberRepository.findByEmail(email);
        if(member==null){
            throw new IllegalStateException("해당 이메일은 존재하지 않습니다.");
        }

        mailService.sendPWMail(email);
        memberRepository.save(member);
    }

    @Override
    public void delMem(Long id) {
        Member member = memberRepository.findById(id).get();
        member.setOut(true);
        memberRepository.save(member);
    }

    @Override
    public void changePW(String email,String password) {
        Member member = memberRepository.findByEmail(email);
        System.out.println(member.getPassword());
        memberRepository.save(member);
    }

    @Override
    public Member changeInfo(MyProfileDTO myDto) {

        Member member = memberRepository.findById(myDto.getId()).get();
        member.setName(myDto.getName());
        member.setProfile(myDto.getProfile());

        Gender g = Gender.N;
        Gender gender = myDto.getGender();
        if(gender.equals("F")) g = Gender.F;
        else if(gender.equals("M")) g = Gender.M;
        member.setGender(g);
        memberRepository.save(member);

        return member;
    }

    @Override
    public Member getInfo(Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    @Override
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if(member==null){
            throw new IllegalStateException("이메일 또는 비밀번호를 다시 입력해주세요.");
        }
        if(!member.getPassword().equals(password)){
            throw new IllegalStateException("이메일 또는 비밀번호를 다시 입력해주세요.");
        }

        return member;
    }

    @Override
    public CharacterDTO getCharacter(Long id) {
        Member m = memberRepository.findById(id).get();
        if(m==null){
            throw new IllegalStateException("이메일이 유효하지 않습니다.");
        }

        CharacterDTO character = new CharacterDTO(id, m.getNowBg(),
                m.getNowChar(), m.getNowAcc());
        return character;
    }

    @Override
    public List<Member> findAllMember() {
        List<Member> memberList = memberRepository.findAll();
        return memberList;
    }

    @Override
    public Member findKakaoMember(String sid) {
        Member member = memberRepository.findBySid(sid);
        return member;
    }


}
