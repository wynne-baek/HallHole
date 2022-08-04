import { List, useRadioGroup } from "@mui/material";
import { Box } from "@mui/system";
import React from "react";

import TextStyle from "../atom/Text";
import ProfileItem from "../molecule/ProfileItem";
import CategorySelectButton from "../molecule/CategorySelectButton";

// 팔로워 팔로잉
const follow = [
  { name: "팔로워", followerCount: 5, selected: true }, //length(user.follower)},
  { name: "팔로잉", followingCount: 6, selected: false }, //length(user.following)},
];

const followList = [
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
];
const followerList = [
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
  { username: "경원" },
];

// onClick에 달아둔 함수로, 누를 경우 아래의 리스트가 변경되어야 합니다!
function selectFollowList(e) {
  e.preventDefault();
}

export default function FollowList(props) {
  return (
    <Box sx={{ width: "95%", marginLeft: 1 }}>
      <TextStyle size="large" variant="black">
        {props.username}
      </TextStyle>
      {/* CATEGORY SELECTOR */}
      <Box sx={{ display: "flex" ,marginY : 2 }}>
        {follow.map((followlist, i) => (
          <CategorySelectButton
            key={i}
            category={followlist.name}
            selected={followlist.selected}
            onClick={selectFollowList}
          ></CategorySelectButton>
        ))}
      </Box>
      {/* profileitem map해서 보여주기 */}
      <Box sx={{ marginTop: 1 }}>
        {followList.map((profile, i) => (
          <ProfileItem key={i} username={profile.username}></ProfileItem>
        ))}
      </Box>
    </Box>
  );
}
