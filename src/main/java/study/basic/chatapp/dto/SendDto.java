package study.basic.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SendDto {
    private String senderName;
    private Long senderId;
    private String content;
    private LocalDateTime sentAt;

    public SendDto(String senderName, Long senderId, String content) {
        this.senderName = senderName;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = LocalDateTime.now();
    }
}
