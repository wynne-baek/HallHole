package com.ssafy.hallhole.follow.service;

import com.ssafy.hallhole.follow.domain.Follow;
import com.ssafy.hallhole.follow.dto.FollowerOutputDTO;
import com.ssafy.hallhole.follow.dto.FollowingOutputDTO;
import com.ssafy.hallhole.follow.repository.FollowRepository;
import com.ssafy.hallhole.member.domain.Member;
import com.ssafy.hallhole.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Override
    public void addFollow(String following, String follower) {
        Member ing = memberRepository.findByIdTag(following);
        Member er = memberRepository.findByIdTag(follower);

        if(ing.isOut() || er.isOut() || ing==null || er==null || following==follower){
            throw new IllegalStateException("유효하지 않은 계정입니다.");
        }
        if(followRepository.findRelation(ing.getId(),er.getId())!=null){
            throw new IllegalStateException("이미 팔로우 한 계정입니다.");
        }

        Follow follow = new Follow(ing,er);
        followRepository.save(follow);

        ing.addFollowingCnt();
        er.addFollowerCnt();
        memberRepository.save(ing);
        memberRepository.save(er);
    }

    @Override
    public void delFollow(String following, String follower) {
        Member ing = memberRepository.findByIdTag(following);
        Member er = memberRepository.findByIdTag(follower);

        if(ing.isOut() || er.isOut() || ing==null || er==null || following==follower){
            throw new IllegalStateException("유효하지 않은 계정입니다.");
        }
        Follow delRelation = followRepository.findRelation(ing.getId(),er.getId());
        if(delRelation==null){
            throw new IllegalStateException("팔로우하지 않은 계정입니다.");
        }

        followRepository.deleteById(delRelation.getId());
        ing.subFollowingCnt();
        er.subFollowerCnt();
        memberRepository.save(ing);
        memberRepository.save(er);
    }

    @Override
    public List<FollowingOutputDTO> findFollowing(String tag) {

        Member member = memberRepository.findByIdTag(tag);
        if(member==null || member.isOut()) {
            throw new IllegalStateException("유효한 tag가 아닙니다.");
        }

        List<Long> list = followRepository.findByFollowingMemberId(member.getId());
        List<FollowingOutputDTO> followingList = new LinkedList<>();

        for(Long id:list){
            Member follower = memberRepository.findById(id).get();
            if(follower==null || follower.isOut()) continue;
            FollowingOutputDTO dto = new FollowingOutputDTO(follower.getName(), follower.getIdTag(),
                    follower.getNowBg(),follower.getNowChar(),follower.getNowAcc());
            followingList.add(dto);
        }

        return followingList;
    }

    @Override
    public List<FollowerOutputDTO> findFollower(String tag) {

        Member member = memberRepository.findByIdTag(tag);
        if(member==null || member.isOut()) {
            throw new IllegalStateException("유효한 tag가 아닙니다.");
        }

        List<Long> list = followRepository.findByFollowedMemberId(member.getId());
        List<FollowerOutputDTO> followerList = new LinkedList<>();

        for(Long id:list){
            Member follower = memberRepository.findById(id).get();
            if(follower==null || follower.isOut()) continue;
            FollowerOutputDTO dto = new FollowerOutputDTO(follower.getName(), follower.getIdTag(),
                    follower.getNowBg(),follower.getNowChar(),follower.getNowAcc());
            followerList.add(dto);
        }

        return followerList;
    }

}
