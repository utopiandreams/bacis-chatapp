package study.basic.chatapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import study.basic.chatapp.entity.Chatroom;
import study.basic.chatapp.repository.ChatroomRepository;

@Component
@RequiredArgsConstructor
public class DBInit implements ApplicationRunner {

    private final ChatroomRepository chatroomRepository;

    @Override
    public void run(ApplicationArguments args) {
        addChatroom();
    }

    private void addChatroom() {
        chatroomRepository.save(new Chatroom());
    }
}
