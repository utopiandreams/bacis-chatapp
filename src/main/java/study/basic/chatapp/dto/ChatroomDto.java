package study.basic.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.basic.chatapp.entity.Chatroom;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatroomDto {
    private Long id;
    private String title;
    private Long hostId;
    private int limit;
    private LocalDateTime lastChatAt;
    public ChatroomDto(Chatroom chatroom){
        id = chatroom.getId();
        title = chatroom.getTitle();
        hostId = chatroom.getHostId();
        limit = chatroom.getRoomLimit();
        lastChatAt = chatroom.getLastChatAt();
    }
}
