function createWebSocket() {
  return new WebSocket("ws://i7a401.p.ssafy.io/ws/chat");
}

export { createWebSocket };
