package com.ssafy.hallhole.follow.service;

import com.ssafy.hallhole.follow.dto.FollowOutputDTO;

import java.util.List;

public interface FollowService {

    void addFollow(Long following, Long follower);

    void delFollow(Long following, Long follower);

    List<FollowOutputDTO> findFollowing(Long mId);

    List<FollowOutputDTO> findFollower(Long mId);
}
