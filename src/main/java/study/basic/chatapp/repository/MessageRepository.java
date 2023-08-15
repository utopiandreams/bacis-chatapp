package study.basic.chatapp.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.basic.chatapp.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE m.chatroom.id = :chatroomId ORDER BY m.createdDate DESC")
    List<Message> findRecentMessageByChatroomId(@Param("chatroomId") Long chatroomId, Pageable pageable);

}
