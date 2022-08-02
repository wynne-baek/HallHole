package com.ssafy.hallhole.follow.controller;

import com.ssafy.hallhole.follow.dto.FollowInputDTO;
import com.ssafy.hallhole.follow.dto.FollowerOutputDTO;
import com.ssafy.hallhole.follow.dto.FollowingOutputDTO;
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
    @ApiOperation(value="[완료] 팔로우 하기 following -> follower")
    public ResponseEntity addFollow(@RequestBody FollowInputDTO dto){
        try{
            followService.addFollow(dto.getIng_tag(),dto.getEd_tag());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    @ApiOperation(value="[완료] 팔로우 취소 following -> follower")
    public ResponseEntity delFollow(@RequestBody FollowInputDTO dto){
        try{
            followService.delFollow(dto.getIng_tag(),dto.getEd_tag());
            return new ResponseEntity(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/following/{tag}")
    @ApiOperation(value="[완료] 내 태그로 내가 팔로잉 중인 사용자 찾기")
    public ResponseEntity<FollowingOutputDTO> getFollowing(@PathVariable("tag") String tag){
        try{
            List<FollowingOutputDTO> followingList = followService.findFollowing(tag);
            return new ResponseEntity(followingList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/follower/{tag}")
    @ApiOperation(value="[완료] 내 태그로 나를 팔로잉 중인 사용자 찾기")
    public ResponseEntity<FollowerOutputDTO> getFollower(@PathVariable("tag") String tag){
        try{
            List<FollowerOutputDTO> followerList = followService.findFollower(tag);
            return new ResponseEntity(followerList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


}
