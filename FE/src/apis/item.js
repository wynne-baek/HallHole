import api from "./api";

// 현재 캐릭터의 모습에서 다른 모습으로 변경함
function changeCharacter(idTag, itemnumber, success, fail) {
  api.post("item/change", {idTag:idTag, item_id:itemnumber}).then(success).catch(fail);
}

export { changeCharacter };
