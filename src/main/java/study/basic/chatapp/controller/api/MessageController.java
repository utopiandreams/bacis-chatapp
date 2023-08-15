package study.basic.chatapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.basic.chatapp.dto.SendDto;
import study.basic.chatapp.entity.Message;
import study.basic.chatapp.repository.ChatroomRepository;
import study.basic.chatapp.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;

    @GetMapping("/{chatroomId}/recent")
    public List<SendDto> initRoom(@PathVariable("chatroomId") Long chatroomId) {
        List<Message> recentMessage = messageRepository.findRecentMessageByChatroomId(chatroomId, PageRequest.of(0, 10));
        return recentMessage.stream().map(msg ->
                new SendDto(msg.getSenderName(), msg.getSenderId(), msg.getContent(), msg.getCreatedDate())).collect(Collectors.toList());
    }
}
