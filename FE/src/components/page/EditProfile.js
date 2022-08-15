import React from "react";
import { Box } from "@mui/system";
import CharacterProfile from "../molecule/CharacterProfile";
import ProfileEdit from "../molecule/ProfileEdit"
import ButtonStyle from "../atom/Button";
import TextStyle from "../atom/Text";

export default function EditProfile(props) {

  return (
    <Box>
      <CharacterProfile></CharacterProfile>
      <ProfileEdit></ProfileEdit>
    </Box>  
  );
}
