package com.project.domain.sign;

import com.project.domain.user.User;
import com.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Target;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(SignUpForm signUpForm) {
        User user = User.builder()
                .loginId(signUpForm.getLoginId())
                .userName(signUpForm.getUserName())
                .password(signUpForm.getPassword())
                .email(signUpForm.getEmail())
                .phone(signUpForm.getPhone())
                .address(signUpForm.getAddress())
                .sex(signUpForm.getSex())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public User findUser(String loginId) {
        Optional<User> user = userRepository.findByLoginId(loginId);
        return user.orElse(null);
    }

    @Transactional
    public User findLoginUser(String loginId, String password) {
        Optional<User> byLoginIdAndPassword = userRepository.findByLoginIdAndPassword(loginId, password);
        return byLoginIdAndPassword.orElse(null);
    }
}
