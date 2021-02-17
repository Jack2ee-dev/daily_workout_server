package com.jack2ee.dailyworkout.domain;

import com.jack2ee.dailyworkout.domain.users.UserRepository;
import com.jack2ee.dailyworkout.domain.users.Users;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    private static final String FAKE_USER_NAME = "fakename";
    private static final String FAKE_AUTH_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void 유저저장_불러오기() {
        // given
        userRepository.save(Users.builder()
            .name(FAKE_USER_NAME)
            .authToken(FAKE_AUTH_TOKEN)
            .build());

        // when
        List<Users> usersList = userRepository.findAll();

        // then
        Users user = usersList.get(0);
        assertThat(user.getName()).isEqualTo(FAKE_USER_NAME);
        assertThat(user.getAuthToken()).isEqualTo(FAKE_AUTH_TOKEN);
    }
}
