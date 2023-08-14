package study.basic.chatapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.basic.chatapp.entity.User;
import study.basic.chatapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetailServiceImpl.loadUserByUsername 실행");
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("회원 정보 없음")
        );
        System.out.println("Username = " + user.getUsername());
        System.out.println("Password = " + user.getPassword());
        return new UserDetailsImpl(user);
    }

}
