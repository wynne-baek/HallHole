import React from "react";

import { Link } from "react-router-dom";

import storage from "../../helper/storage";

export default function NoMatch() {
  function logout() {
    storage.remove("token");
  }
  return (
    <div>
      <p>잘못 된 경로입니다.</p>
      <Link to="/">
        <button>돌아가기</button>
        <button onClick={logout}>로그 아웃</button>
      </Link>
    </div>
  );
}
