package com.ssafy.hallhole.follow.service;

import com.ssafy.hallhole.follow.dto.FollowerOutputDTO;
import com.ssafy.hallhole.follow.dto.FollowingOutputDTO;
import com.ssafy.hallhole.member.domain.Member;

import java.util.List;

public interface FollowService {

    void addFollow(String following, String follower);

    void delFollow(String following, String follower);

    List<FollowingOutputDTO> findFollowing(String tag);

    List<FollowerOutputDTO> findFollower(String tag);
}
