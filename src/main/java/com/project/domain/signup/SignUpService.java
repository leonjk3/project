package com.project.domain.signup;

import com.project.domain.user.User;
import com.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {

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
    public boolean findUser(String loginId) {
        Optional<User> findUser = userRepository.findByLoginId(loginId);
        return findUser.isPresent();
    }
}
