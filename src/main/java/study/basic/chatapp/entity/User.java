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
@Table(name = "users")
public class User extends BaseAuditingEntity{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "users")
    private List<UserChatroom> chatrooms = new ArrayList<>();

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
