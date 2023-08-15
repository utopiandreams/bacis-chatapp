package study.basic.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class Chatroom extends BaseAuditingEntity{
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;
    private String title;
    private Long hostId;
    private int roomLimit;
    private LocalDateTime lastChatAt;

    @OneToMany(mappedBy = "chatroom")
    private List<UserChatroom> users = new ArrayList<>();

}
