import React, { useEffect, useState } from "react";
import { Box } from "@mui/system";
import { MenuItem, FormControl, Select, InputLabel, Button } from "@mui/material"
import { useSelector } from "react-redux";
import { requestUserInfo, userEditProfile } from "../../apis/user";
import TextStyle from "../atom/Text";
import Input from "../atom/Input"
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import TextField from '@mui/material/TextField';
import { useNavigate } from "react-router";
import ButtonStyle from "../atom/Button";


export default function ProfileEdit() {
  const user = useSelector(state => state.user.info)
  const [open, setOpen] = React.useState(false);
  const [gender, setGender] = React.useState('');
  const [name, setName] = React.useState('')
  const [profile, setProfile] = React.useState('')
  const [email, setEmail] = React.useState('')
  const [birth, setBirth] = React.useState(null)
  
  // 기존 유저 정보 가져오기
  useEffect(() => {
    requestUserInfo(user?.idTag, getProfileUserSuccess, getProfileUserFail);
    setName(user?.name)
    setProfile(user?.profile)
    setGender(user?.gender)
    setEmail(user?.email)
    setBirth(user?.birth)
  }, [user]);
  
  function getProfileUserSuccess(res) {
    console.log('요청 성공')
    console.log(user)
    setUserInfo(res.data);
  }

  function getProfileUserFail(err) {
    console.log('에러')
  }

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleOpen = () => {
    setOpen(true);
  };
  
  const movePage = useNavigate();

  // 취소 후 본인 프로필로 이동
  function cancelEdit() {
    movePage(`/profile/${user.idTag}`)
  }

  function changePassword(){
    movePage(`/`)
  }

  function editConfirm() {
    const birth = user.birth

  }

  return (
    // 프로필 설정에서 변경할 수 있는 모든 폼들이 모여있습니다.
    // 마진 관련 재설정 필요(px외 단위로), 위치 설정
    <Box sx={{ mx:6.5, my:2, display: 'flex',
    alignItems: 'flex-start',
    flexDirection: 'column',}}>
      <Box>
        <TextStyle size="medium">이름</TextStyle>
        <Input 
        size="large" 
        value={name} 
        onChange={(e) => {setName(e.target.value)}}></Input>
      </Box>
      <Box sx={{ my: 2 }}>
        <TextStyle size="medium">소개</TextStyle>
        <Box>
        <TextStyle size="small">최대 50자만 입력 가능합니다.</TextStyle>
        </Box>
        <Input size="large" value={profile} onChange={(e) => {setProfile(e.target.value)}}> </Input>
      </Box>
      <Box>
        <TextStyle size="medium">이메일</TextStyle>
        <Input size="large" disabled="true" value={email}></Input>
      </Box>
      <Box sx={{ mt:2, display: "flex", alignItems:"center" }}>
        <Box>
          <TextStyle size="medium">성별</TextStyle>
          <FormControl sx={{ minWidth: 120 }}>
            {/* <InputLabel id="gender"></InputLabel> */}
              <Select
                labelId="gender"
                id="gender-selector"
                open={open}
                onClose={handleClose}
                onOpen={handleOpen}
                label="setGender"
                value={gender}
                onChange={(e) => {setGender(e.target.value)}}
              >
                <MenuItem value="N">비공개</MenuItem>
                <MenuItem value="M">남자</MenuItem>
                <MenuItem value="F">여자</MenuItem>
              </Select>
          </FormControl>
        </Box>
        <Box sx={{ ml: 1 }}>
          <TextStyle size="medium">생년월일</TextStyle>
          <LocalizationProvider dateAdapter={AdapterDateFns}>
            <DatePicker
              onChange={(newBirth) => {
                setBirth(newBirth);
              }}
              renderInput={(params) => <TextField {...params} sx={{ width: '105%' }} />}
            />
          </LocalizationProvider>
        </Box>
      </Box>
        <Box>
          <ButtonStyle size="medium" variant="grey" onClick={ cancelEdit }>취소</ButtonStyle>
          <ButtonStyle size="medium" variant="primary" onClick={ cancelEdit }>저장</ButtonStyle>
        </Box>
        <Box sx={{ mt:2 }}>
            <Button color="info" size="small" sx={{ mr: 4 }} variant="text">비밀번호 변경하기</Button>
            <Button sx={{ ml: 4}} color="warning" size="small" variant="text">회원 탈퇴</Button>
        </Box>
    </Box>
  );
}
