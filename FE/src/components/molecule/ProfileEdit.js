import React from "react";
import { Box } from "@mui/system";

import ProfileImage from "../atom/ProfileImage";
import TextStyle from "../atom/Text";
import ButtonStyle from "../atom/Button";
import Input from "../atom/Input"

export default function ProfileDetail(props) {
  //   let ProfileButton = <ButtonStyle size="medium" variant="negative">테스트 중</ButtonStyle>
  //   if (request.user === props.user) {
  //     ProfileButton = <ButtonStyle size="medium" variant="negative">프로필 수정</ButtonStyle>
  //   } else if (request.user in props.user.follower) {
  //     ProfileButton = <ButtonStyle size="medium" variant="negative">언팔로우</ButtonStyle>
  //   } else {
  //     ProfileButton = <ButtonStyle size="medium" variant="primary">팔로우</ButtonStyle>
  //   }
  return (
    // props로 해당 user 정보를 받아와 나타낼 예정
    // button은 위 주석처리한 코드와 같이 조건에 따라 문구와 기능을 다르게 할 예정!
    <Box>
        <ProfileImage type="large" src=""></ProfileImage>
        <ButtonStyle size={"large"} variant={"primary"}>캐릭터 꾸미기</ButtonStyle>
        <TextStyle size="large">자기소개</TextStyle>
        <Input size="large">{}</Input>
      <div style={{ marginLeft: 10 }}>
        <TextStyle size="large">{props.username} </TextStyle>
        <TextStyle size="medium">{props.usertag}</TextStyle>
        <br></br>
        <TextStyle size="small">{props.profile}</TextStyle>
      </div>
    </Box>
  );
}
