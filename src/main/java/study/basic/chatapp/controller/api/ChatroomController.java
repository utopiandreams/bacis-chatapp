package study.basic.chatapp.controller.api;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import study.basic.chatapp.dto.ChatroomDto;
import study.basic.chatapp.dto.CreateRoomDto;
import study.basic.chatapp.entity.Chatroom;
import study.basic.chatapp.repository.ChatroomRepository;
import study.basic.chatapp.service.UserDetailsImpl;
import study.basic.chatapp.config.WebSocketChannelInterceptor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chatroom")
@RequiredArgsConstructor
public class ChatroomController {
    private final ChatroomRepository chatroomRepository;
    private final WebSocketChannelInterceptor webSocketChannelInterceptor;

    @PostMapping("")
    public ResponseEntity<String> createChatroom(@RequestBody CreateRoomDto dto,
                               Authentication authentication) throws IOException {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Long userId = principal.getUserId();

        Chatroom newRoom = Chatroom.builder().title(dto.getTitle()).roomLimit(dto.getLimit()).hostId(userId).lastChatAt(LocalDateTime.now()).build();
        Chatroom saved = chatroomRepository.save(newRoom);
        return ResponseEntity.ok().body("/chat/" + saved.getId());
    }

    @GetMapping("")
    public ResponseEntity<List<ChatroomDto>> chatroomList() {
        List<Chatroom> all = chatroomRepository.findAll();
        List<ChatroomDto> result = all.stream().map(ChatroomDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{chatroomId}")
    public ResponseEntity<String> chatroomInfo(@PathVariable("chatroomId") Long chatroomId) {
        Chatroom findChatroom = chatroomRepository.findById(chatroomId).orElseThrow(() ->
                new IllegalArgumentException("찾으시는 채팅방이 존재하지 않습니다.")
        );
        return ResponseEntity.ok().body(findChatroom.getTitle());
    }

    @GetMapping("/{chatroomId}/sub")
    public ResponseEntity<Integer> chatroomCurrentUser(@PathVariable("chatroomId") String chatroomId) {
        int subscriberCount = webSocketChannelInterceptor.getSubscriberCount("/topic/chat/" + chatroomId);
        return ResponseEntity.ok(Integer.valueOf(subscriberCount));
    }

}
