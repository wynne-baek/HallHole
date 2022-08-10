import React, { useEffect, useState } from "react";
import LikePerformances from "../organism/LikePerformance";
import ProfileDetail from "../organism/ProfileDetail";
import UserActivity from "../organism/UserActivity";

import { Box } from "@mui/system";
import MyActivity from "../organism/MyActivity";

import { useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { requestUserInfo } from "../../apis/user";

const likePerformanceList = [
  {
    id: 1,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 2,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 3,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 4,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 5,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 6,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  {
    id: 7,
    title: "웃는 남자",
    img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  },
  // {
  //   id: 8,
  //   title: "웃는 남자",
  //   img: "https://cdnticket.melon.co.kr/resource/image/upload/product/2019/11/2019112812171593082da1-3771-458f-ab20-7777146d2066.jpg",
  // },
];

const reviews = [
  {
    id: 1,
    title: "진짜 최고야!!!!!!!!",
    date: "2022. 05.12",
    star_eval: 4.5,
    performance_name: "웃는 남자",
  },
  {
    id: 2,
    title: "진짜 최고야!!!!!!!!",
    date: "2022. 05.12",
    star_eval: 4.5,
    performance_name: "웃는 남자",
  },
  {
    id: 3,
    title: "진짜 최고야!!!!!!!!",
    date: "2022. 05.12",
    star_eval: 4.5,
    performance_name: "웃는 남자",
  },
];

export default function Profile({ id }) {
  const user = useSelector(state => state.user.info);
  const [profileUser, setProfileUser] = useState([]); 
  // const { id } = useParams();

  useEffect(() => {
    requestUserInfo(id, getProfileUserSuccess, getProfileUserFail);
    console.log(user);
  }, []);

  function getProfileUserSuccess(res) {
    setProfileUser(res.data)
    console.log("프로필 유저 정보 조회 성공", res)
  }

  function getProfileUserFail(err) {
    console.log("프로필 유저 정보 조회 실패", err)
  }

  // profile user 정보 받아왔는지 확인
  function validateProfileUser(profileUser) {
    return profileUser !== []
  }

  return (
    <Box>
      {validateProfileUser ? (
        <Box>
          <ProfileDetail
            user={user}
            idTag={profileUser.idTag}
            name={profileUser.name}
            profile={profileUser.profile}
            followerCnt={profileUser.followerCnt}
            followingCnt={profileUser.followingCnt}
          />
          {/* <LikePerformances /> */}
          {/* <UserActivity username="경원" reviews={reviews}></UserActivity> */}
          {/* <MyActivity reviews={reviews}></MyActivity> */}
        </Box>
      ) : (
        <Box>로딩중</Box>
      )}
    </Box>
  );
}
