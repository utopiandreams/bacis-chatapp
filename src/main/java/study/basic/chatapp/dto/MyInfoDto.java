package study.basic.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.basic.chatapp.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoDto {
    private Long userId;
    private String username;

    public MyInfoDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
