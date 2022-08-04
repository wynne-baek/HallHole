import React from "react";
import { Box } from "@mui/system";
import CharacterProfile from "../molecule/CharacterProfile";
import ProfileEdit from "../molecule/ProfileEdit"
import ButtonStyle from "../atom/Button";

export default function EditProfile(props) {
  // 저장, 취소, 비밀번호 변경, 탈퇴하기 버튼 구성하기
  return (
    <Box>
      <CharacterProfile></CharacterProfile>
      <ProfileEdit></ProfileEdit>
    </Box>  
  );
}
