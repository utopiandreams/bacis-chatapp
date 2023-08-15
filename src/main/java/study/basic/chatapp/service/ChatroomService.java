package study.basic.chatapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basic.chatapp.repository.ChatroomRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatroomService {

    private final ChatroomRepository chatroomRepository;

    @Transactional
    public void updateLastChatAt(Long chatroomId) {
        chatroomRepository.updateLastModified(LocalDateTime.now(), chatroomId);
    }

}
