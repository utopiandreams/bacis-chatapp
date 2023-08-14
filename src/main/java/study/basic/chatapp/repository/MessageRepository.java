package study.basic.chatapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.basic.chatapp.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m ORDER BY m.createdDate DESC ")
    List<Message> findRecentMessage(Pageable pageable);
}
