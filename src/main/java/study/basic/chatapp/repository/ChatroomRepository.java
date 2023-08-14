package study.basic.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.basic.chatapp.entity.Chatroom;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
}
