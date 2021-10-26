package com.kungca.user.User;

import com.kungca.user.common.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @AllArgsConstructor
    enum USER_SPEC {
        USER_ID("userId");

        @Getter
        String value;
    }

    public CommonResponse login(HttpServletRequest request, UserDto userInfo) {

        int status = 0;
        Object response = "성공";
        int maxTime = 5;

        Optional<User> checkUser = userRepository.findByUserId(userInfo.getUserId());

        if (checkUser.isEmpty()) {
            status = 101;
            response = "유저가 없습니다.";
        } else {
            if (!userInfo.getPassword().equals(checkUser.get().getPassword())) {
                status = 102;
                response = "비밀번호가 틀렸습니다.";

            } else {
                HttpSession session = request.getSession();
                if (!checkSession(request)) {
                    session.setAttribute(USER_SPEC.USER_ID.getValue(), userInfo.getUserId());
                }
                session.setMaxInactiveInterval(maxTime);
            }
        }

        return CommonResponse.builder()
                .status(status)
                .data(response)
                .build();
    }


    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        // have to add one more method
    }

    public boolean checkSession(HttpServletRequest request) {
        boolean result = true;
        if (request.getSession().getAttribute(USER_SPEC.USER_ID.getValue()) == null) {
            result = false;
        }
        return result;
    }
}
