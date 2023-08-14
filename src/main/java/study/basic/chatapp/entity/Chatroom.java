package study.basic.chatapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Chatroom extends BaseAuditingEntity {
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;

    @OneToMany(mappedBy = "chatroom")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "chatroom")
    private List<UserChatroom> users = new ArrayList<>();

    public void addMsg(Message message) {
        messages.add(message);
    }
}
