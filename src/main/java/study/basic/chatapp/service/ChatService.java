package study.basic.chatapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basic.chatapp.entity.Chatroom;
import study.basic.chatapp.entity.Message;
import study.basic.chatapp.repository.ChatroomRepository;
import study.basic.chatapp.repository.MessageRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final MessageRepository messageRepository;
    private final ChatroomRepository chatroomRepository;

    @Transactional
    public void saveMessage(String content, String senderName, Long senderId, Long roomId) {
        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() ->
                new IllegalArgumentException("채팅방을 찾을 수 없습니다.")
        );

        Message msg = Message.builder()
                .content(content)
                .senderId(senderId)
                .senderName(senderName)
                .chatroom(chatroom)
                .build();

        messageRepository.save(msg);
    }

}
