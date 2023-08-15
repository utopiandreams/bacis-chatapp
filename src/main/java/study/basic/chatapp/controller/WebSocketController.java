package study.basic.chatapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import study.basic.chatapp.dto.ReceiveDto;
import study.basic.chatapp.dto.SendDto;
import study.basic.chatapp.service.ChatService;
import study.basic.chatapp.service.ChatroomService;
import study.basic.chatapp.service.UserDetailsImpl;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final ChatService chatService;
    private final ChatroomService chatroomService;

    @MessageMapping("/chat/{chatroomId}")
    @SendTo("/topic/chat/{chatroomId}")
    public SendDto message(ReceiveDto msg, Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        chatService.saveMessage(msg.getContent(), principal.getUsername(), principal.getUserId(), Long.parseLong(msg.getRoomId()));
        chatroomService.updateLastChatAt(Long.parseLong(msg.getRoomId()));
        return new SendDto(principal.getUsername(), principal.getUserId(), msg.getContent());
    }

}
