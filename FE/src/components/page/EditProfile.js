import React, { useEffect, useState } from "react";
import { Box } from "@mui/system";

import { useNavigate } from "react-router";
import ProfileImage from "../atom/ProfileImage";
import ProfileEdit from "../molecule/ProfileEdit"
import ButtonStyle from "../atom/Button";
import TextStyle from "../atom/Text";
import { useSelector } from "react-redux";

// 유저정보 조회, 수정 api
import { requestUserInfo, userEditProfile } from "../../apis/user";


export default function EditProfile(props) {
  const user = useSelector(state => state.user.info)
  const [userInfo, setUserInfo] = React.useState([])
  const [characterInfo, setCharacterInfo] = React.useState([])
  
  // 기존 유저 정보 가져오기
  useEffect(() => {
    requestUserInfo(user?.idTag, getProfileUserSuccess, getProfileUserFail);
  }, [user]);

  function getProfileUserSuccess(res) {
    setUserInfo(res.data);
    console.log(res.data);
    // console.log("프로필 유저 정보 조회 성공", res);

  }

  function getProfileUserFail(err) {
    console.log("정보 읽어오기 실패")
  }


  const movePage = useNavigate();
  
  function editHoly() {
    movePage(`/editholy`)
  }

  function cancelEdit() {
    movePage(`/profile/${user.idTag}`)
  }

  function editConfirm() {
    const birth = user.birth

  }
  // 추후 작업 - 비밀번호 변경, 회원 탈퇴 위치 재지정
  // 버튼 클릭 시 이동 구현하기, 텍스트에 onClick 구현
  return (
    <Box>
      <Box sx={{ mt: 2, display: "flex", justifyContent: 'center' }}>
        <ProfileImage type="large" src=""></ProfileImage>
      </Box>
      <Box sx={{ display: "flex", justifyContent: 'center', mt: 2 }} >
        <ButtonStyle size="medium" variant="primary" onClick={editHoly}>캐릭터 변경</ButtonStyle>
      </Box>
      <ProfileEdit></ProfileEdit>
      <Box sx={{ display: "flex", justifyContent:"center" }}>
        <Box sx={{ mt:2, display:"flex", justifyContent:"space-evenly" }}>
          <Box>
            <TextStyle size="small" variant="black">비밀번호 변경하기</TextStyle>
          </Box>
          <Box>
            <TextStyle size="small" variant="primary">회원 탈퇴</TextStyle>
          </Box>
        </Box>
      </Box>
      <Box sx={{ mt: 2, mx: 5, display:"flex", justifyContent:"space-evenly" }}> 
        <ButtonStyle size="medium" variant="grey" onClick={ cancelEdit }>취소</ButtonStyle>
        <ButtonStyle size="medium" variant="primary" onClick={ cancelEdit }>저장</ButtonStyle>
      </Box>
    </Box>  
  );
}
