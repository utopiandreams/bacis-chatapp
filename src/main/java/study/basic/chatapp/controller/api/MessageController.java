package study.basic.chatapp.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.basic.chatapp.dto.SendDto;
import study.basic.chatapp.entity.Message;
import study.basic.chatapp.repository.MessageRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageRepository messageRepository;

    @GetMapping("/chat/recent")
    public List<SendDto> initRoom() {
        List<Message> recentMessage = messageRepository.findRecentMessage(PageRequest.of(0, 10));
        return recentMessage.stream().map(msg ->
                new SendDto(msg.getSenderName(), msg.getSenderId(), msg.getContent(), msg.getCreatedDate())).collect(Collectors.toList());
    }
}
