package study.basic.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.basic.chatapp.entity.Chatroom;

import java.time.LocalDateTime;

public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    @Modifying
    @Query("UPDATE Chatroom c SET c.lastChatAt = :now WHERE c.id = :id")
    int updateLastModified(@Param("now") LocalDateTime now, @Param("id") Long id);
}
