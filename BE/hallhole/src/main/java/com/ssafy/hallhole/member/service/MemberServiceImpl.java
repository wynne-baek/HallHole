package com.ssafy.hallhole.member.service;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.comment.domain.Comment;
import com.ssafy.hallhole.comment.repository.CommentRepository;
import com.ssafy.hallhole.follow.domain.Follow;
import com.ssafy.hallhole.follow.repository.FollowRepositoryImpl;
import com.ssafy.hallhole.mail.MailService;
import com.ssafy.hallhole.member.domain.Gender;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.dto.*;
import com.ssafy.hallhole.member.repository.HashMapRepository;
import com.ssafy.hallhole.member.repository.MemberRepository;
import com.ssafy.hallhole.review.domain.Review;
import com.ssafy.hallhole.review.repository.ReviewRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    private final MailService mailService;

    private final JwtTokenProviderImpl jwtTokenService;

    private final HashMapRepository sessionRepository;

    private final FollowRepositoryImpl followRepository;

    @Override
    public void join(MemberJoinDTO m,String sessionId) throws NotFoundException {

        Member member = new Member(m.getEmail(),m.getName(),m.getPw());
        duplicateMember(member.getEmail());

        if(!Pattern.matches(
                "^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\\\(\\\\)\\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\\\(\\\\)\\-_=+]).{8,20}$" , m.getPw()))
            throw new NotFoundException("비밀번호를 다시 입력해주세요");
        if(!Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", m.getEmail()))
            throw new NotFoundException("이메일을 다시 입력해주세요.");

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
    }

    @Override
    public String login(String email, String password, String sessionId) throws NotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member==null || member.isOut() || !member.getProvider().equals("HH")){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        if(!member.getPassword().equals(password)){
            throw new NotFoundException("비밀번호를 다시 입력해주세요.");
        }

        return jwtTokenService.createToken(member.getId(), sessionId);
    }

    @Override
    public void logout(String token, String sessionId) throws NotFoundException {
        // 토큰에서 데이터 추출

        // 세션 확인 후 있는 세션 날리기

    }

    @Override
    public void duplicateMember(String email) throws NotFoundException {
        if(memberRepository.findByEmail(email)!=null) {
            throw new NotFoundException("이미 사용 중인 이메일입니다.");
        }
    }

    @Override
    public void findPW(String email) throws NotFoundException {
        Member member = memberRepository.findByEmail(email);
        if(member==null || member.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

//        mailService.sendPWMail(email);
        memberRepository.save(member);
    }

    @Override
    public void delMem(String token,String sessionId) throws NotFoundException {

        Claims claim = jwtTokenService.getAllclaimsFromToken(token);
        Long memberId = Long.parseLong(claim.get("memberId").toString());
        Member m = memberRepository.findById(memberId).get();
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        Member member = memberRepository.findById(m.getId()).get();
        if(member==null || member.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        System.out.println("다 지남");

        // 팔로우 데이터 날리기
        List<Follow> followList = followRepository.findAllRelationByMemberId(member.getId());
        for(Follow f:followList){
            followRepository.delete(f);
            f.getFollowingMember().subFollowingCnt();
            f.getFollowedMember().subFollowerCnt();
            memberRepository.save(f.getFollowingMember());
            memberRepository.save(f.getFollowedMember());
        }

        System.out.println("fin follow");


        // 댓글 데이터 날리기
        List<Comment> commentList = commentRepository.findAllCommentByMemberId(member.getId());
        for(Comment c:commentList){
            c.setDelete(true);
            commentRepository.save(c);
        }

        System.out.println("fin comment");

        // 후기 데이터 날리기
        List<Review> reviewList = reviewRepository.findAllByMemberId(member.getId());
        for(Review r:reviewList){
            r.setDelete(true);
            reviewRepository.save(r);
        }

        System.out.println("fin review");

        member.setOut(true);
        member.setOutDate(LocalDate.now());
        memberRepository.save(member);
    }

    @Override
    public void changePW(String email,String password) throws NotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member==null || member.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        System.out.println(member.getPassword());
        memberRepository.save(member);
    }

    @Override
    public MemberOutputDTO changeInfo(MyProfileDTO myDto) throws NotFoundException {

        Member m = memberRepository.findByIdTag(myDto.getIdTag());

        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        m.setName(myDto.getName());
        m.setProfile(myDto.getProfile());

        Gender g = Gender.N;
        Gender gender = myDto.getGender();
        if(gender==Gender.F) g = Gender.F;
        else if(gender==Gender.M) g = Gender.M;
        m.setGender(g);

        m.setNowBg(myDto.getNowBg());
        m.setNowChar(myDto.getNowChar());
        m.setNowAcc(myDto.getNowAcc());

        memberRepository.save(m);

        MemberOutputDTO member = new MemberOutputDTO(m.getName(),m.getEmail(),
                m.getGender(),m.getBirth(),m.isAdmin(),m.getPoint(),m.isOut(),m.getIdTag(),m.isBan(),
                m.getFollowingCnt(),m.getFollowerCnt(),m.getProfile(),m.getNowBg(),m.getNowChar(),
                m.getNowAcc());

        return member;
    }

    @Override
    public MemberOutputDTO getInfo(String tag) throws NotFoundException {
        Member m = memberRepository.findByIdTag(tag);
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        MemberOutputDTO member = new MemberOutputDTO(m.getName(),m.getEmail(),
                m.getGender(),m.getBirth(),m.isAdmin(),m.getPoint(),m.isOut(),m.getIdTag(),m.isBan(),
                m.getFollowingCnt(),m.getFollowerCnt(),m.getProfile(),m.getNowBg(),m.getNowChar(),
                m.getNowAcc());

        return member;
    }

    @Override
    public CharacterDTO getCharacter(String tag) throws NotFoundException {
        Member m = memberRepository.findByIdTag(tag);
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        CharacterDTO character = new CharacterDTO(tag, m.getNowBg(),
                m.getNowChar(), m.getNowAcc());
        return character;
    }

    @Override
    public List<Member> findAllMember() throws NotFoundException {
        List<Member> memberList = memberRepository.findAllAliveMember();
        if(memberList.size()==0)
            throw new NotFoundException("현재 유효한 회원이 없습니다.");
        return memberList;
    }

    @Override
    public Member findKakaoMember(String sid) throws NotFoundException {
        Member member = memberRepository.findBySid(sid);

        if(member==null || member.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        return member;
    }

    @Override
    public MemberOutputDTO findInfo(String token) throws NotFoundException {
        Claims claim = jwtTokenService.getAllclaimsFromToken(token);
        Long memberId = Long.parseLong(claim.get("memberId").toString());
        Member m = memberRepository.findById(memberId).get();
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        MemberOutputDTO member = new MemberOutputDTO(m.getName(),m.getEmail(),
                m.getGender(),m.getBirth(),m.isAdmin(),m.getPoint(),m.isOut(),m.getIdTag(),m.isBan(),
                m.getFollowingCnt(),m.getFollowerCnt(),m.getProfile(),m.getNowBg(),m.getNowChar(),
                m.getNowAcc());

        return member;
    }

    @Override
    public void makeBan(String tag) throws NotFoundException {
        Member m = memberRepository.findByIdTag(tag);
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        m.setBan(true);
        memberRepository.save(m);
    }

    @Override
    public void cancelBan(String tag) throws NotFoundException {
        Member m = memberRepository.findByIdTag(tag);
        if(m==null || m.isOut()){
            throw new NotFoundException("유효한 회원이 아닙니다.");
        }

        m.setBan(false);
        memberRepository.save(m);
    }
}
