package com.ssafy.hallhole.follow.controller;

import com.ssafy.hallhole.advice.exceptions.NotFoundException;
import com.ssafy.hallhole.follow.domain.Follow;
import com.ssafy.hallhole.follow.dto.FollowInputDTO;
import com.ssafy.hallhole.follow.dto.FollowOutputDTO;
import com.ssafy.hallhole.follow.dto.PagingInputDTO;
import com.ssafy.hallhole.follow.service.FollowServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
@ApiOperation(value = "followController")
public class FollowController {

    private final FollowServiceImpl followService;

    @PostMapping
    @ApiOperation(value="팔로우 하기 following -> follower")
    public void addFollow(@RequestBody FollowInputDTO dto) throws NotFoundException {
        followService.addFollow(dto.getFollowingTag(),dto.getFollowerTag());
    }

    @DeleteMapping
    @ApiOperation(value="팔로우 취소 following -> follower")
    public void delFollow(@RequestBody FollowInputDTO dto) throws NotFoundException {
        followService.delFollow(dto.getFollowingTag(),dto.getFollowerTag());
    }

    @PostMapping("/following")
    @ApiOperation(value="내 ID로 내가 팔로잉 중인 사용자 찾기 >> input: followingTag")
    public ResponseEntity<FollowOutputDTO> getFollowing(@RequestBody PagingInputDTO inputDto){
        try{
            List<FollowOutputDTO> followingList = followService.findFollowing(inputDto);
            return new ResponseEntity(followingList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/follower")
    @ApiOperation(value="내 ID로 나를 팔로잉 중인 사용자 찾기 >> input: followerTag")
    public ResponseEntity<FollowOutputDTO> getFollower(@RequestBody PagingInputDTO inputDto){
        try{
            List<FollowOutputDTO> followerList = followService.findFollower(inputDto);
            return new ResponseEntity(followerList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/chk-follow")
    @ApiOperation(value="팔로우 관계인지 알아보기 >> input: followingTag, followerTag")
    public boolean findRelation(@RequestBody FollowInputDTO inputDto) throws NotFoundException {
        List<Follow> relation = followService.findRelation(inputDto);
        if(relation.size()==0) return false;
        return true;
    }


}
