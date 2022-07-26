import React, { Component } from "react";
import { styled } from "@mui/system";
import { Avatar } from "@mui/material";

const ThumbAvatar = styled(Avatar)({
  borderRadius: "50% 50% 0% 0%",
});

class ProfileImageThumb extends Component {
  render() {
    return <ThumbAvatar></ThumbAvatar>;
  }
}

export default ProfileImageThumb;
