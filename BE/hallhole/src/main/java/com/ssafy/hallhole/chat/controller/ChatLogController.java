package com.ssafy.hallhole.chat.controller;

import com.ssafy.hallhole.chat.domain.ChatLog;
import com.ssafy.hallhole.chat.service.ChatLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat/logs")
public class ChatLogController {

    private final ChatLogService chatLogService;

    @GetMapping("/{id}")
    public List<ChatLog> find(@PathVariable(name = "id") String id, Pageable pageable) {
        List<ChatLog> msgs = chatLogService.getLogs(id, pageable);
        return msgs;
    }
}
