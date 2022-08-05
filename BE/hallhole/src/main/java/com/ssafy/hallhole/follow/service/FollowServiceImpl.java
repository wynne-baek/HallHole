package com.ssafy.hallhole.follow.service;

import com.ssafy.hallhole.follow.domain.Follow;
import com.ssafy.hallhole.follow.dto.FollowOutputDTO;
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
    public void addFollow(Long following, Long follower) {
        Member ing = memberRepository.findById(following).get();
        Member er = memberRepository.findById(follower).get();

        if(ing.isOut() || er.isOut() || ing==null || er==null || following==follower){
            throw new IllegalStateException("유효하지 않은 계정입니다.");
        }
        if(followRepository.findRelation(following,follower)!=null){
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
    public void delFollow(Long following, Long follower) {
        Member ing = memberRepository.findById(following).get();
        Member er = memberRepository.findById(follower).get();

        if(ing.isOut() || er.isOut() || ing==null || er==null || following==follower){
            throw new IllegalStateException("유효하지 않은 계정입니다.");
        }
        Follow delRelation = followRepository.findRelation(following,follower);
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
    public List<FollowOutputDTO> findFollowing(Long mId) {

        Member member = memberRepository.findById(mId).get();
        if(member==null || member.isOut()) {
            throw new IllegalStateException("유효한 사용자가 아닙니다.");
        }
        List<Follow> list = followRepository.findByFollowingMemberId(member.getId());
        List<FollowOutputDTO> followingList = new LinkedList<>();

        for(Follow f:list){
            Member follow = f.getFollowedMember();
            if(follow==null || follow.isOut()) continue;
            FollowOutputDTO dto = new FollowOutputDTO(follow.getName(), follow.getIdTag(),
                    follow.getNowBg(),follow.getNowChar(),follow.getNowAcc());
            followingList.add(dto);
        }

        return followingList;
    }

    @Override
    public List<FollowOutputDTO> findFollower(Long mId) {

        Member member = memberRepository.findById(mId).get();
        if(member==null || member.isOut()) {
            throw new IllegalStateException("유효한 사용자가 아닙니다.");
        }

        List<Follow> list = followRepository.findByFollowedMemberId(member.getId());
        List<FollowOutputDTO> followerList = new LinkedList<>();

        for(Follow f:list){
            Member follow = f.getFollowingMember();
            if(follow==null || follow.isOut()) continue;
            FollowOutputDTO dto = new FollowOutputDTO(follow.getName(), follow.getIdTag(),
                    follow.getNowBg(),follow.getNowChar(),follow.getNowAcc());
            followerList.add(dto);
        }

        return followerList;
    }

}
