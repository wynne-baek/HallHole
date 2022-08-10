import React, { useEffect, useState } from "react";
import { CardHeader } from "@mui/material";
import { Box } from "@mui/system";

import ProfileImage from "../atom/ProfileImage";
import TextStyle from "../atom/Text";
import ButtonStyle from "../atom/Button";
import { checkFollowStatus, followUser, unfollowUser } from "../../apis/follow";

export default function ProfileDetail({ name, profile, idTag, followerCnt, followingCnt, user }) {
  const [followStatus, setFollowStatus] = useState("");

  function checkFollowStatusSuccess(res) {
    console.log(res.data);
    setFollowStatus(res.data);
  }

  function checkFollowStatusFail(err) {
    console.log("팔로우 상태 확인 실패", err);
  }

  useEffect(() => {
    checkFollowStatus(user?.idTag, idTag, checkFollowStatusSuccess, checkFollowStatusFail);
  }, [idTag]);

  // 프로필 수정, 팔로우, 언팔로우 버튼 설정
  let ProfileButton;
  if (user?.idTag === idTag) {
    ProfileButton = (
      <ButtonStyle size="small" variant="negative" onClick={editProfile}>
        프로필 수정
      </ButtonStyle>
    );
  } else if (followStatus) {
    ProfileButton = (
      <ButtonStyle size="small" variant="grey" onClick={unfollow}>
        언팔로우
      </ButtonStyle>
    );
  } else {
    ProfileButton = (
      <ButtonStyle size="small" variant="primary" onClick={follow}>
        팔로우
      </ButtonStyle>
    );
  }

  //팔로우
  function followSuccess(res) {
    console.log("팔로우 성공");
    setFollowStatus(true);
  }

  function followFail(err) {
    console.log("팔로우 실패");
  }

  function follow(e) {
    e.preventDefault();
    followUser(user.idTag, idTag, followSuccess, followFail);
  }
  // 언팔로우
  function unfollowSuccess(res) {
    console.log("언팔로우 성공");
    setFollowStatus(false);
  }

  function unfollowFail(err) {
    console.log("언팔로우 실패");
  }

  function unfollow(e) {
    e.preventDefault();
    unfollowUser(user.idTag, idTag, unfollowSuccess, unfollowFail)
  }

  function editProfile(e) {
    e.preventDefault();
    // 프로필 수정 페이지로 이동
  }

  return (
    // props로 해당 user 정보를 받아와 나타낼 예정
    // button은 위 주석처리한 코드와 같이 조건에 따라 문구와 기능을 다르게 할 예정!
    <Box>
      <CardHeader
        sx={{ padding: 0.5 }}
        avatar={<ProfileImage type="large" src=""></ProfileImage>}
        title={ProfileButton}
      ></CardHeader>
      <Box sx={{ ml: 3 }}>
        <TextStyle size="large">{name} </TextStyle>
        <TextStyle size="medium">{idTag}</TextStyle>
        <br></br>
        <TextStyle size="medium">
          팔로워 : {followingCnt} | 팔로잉 : {followerCnt}
        </TextStyle>
        <br></br>
        <TextStyle size="small">{profile}</TextStyle>
      </Box>
    </Box>
  );
}
