package com.kungca.user.User;

import com.kungca.user.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public CommonResponse login(HttpServletRequest request, @RequestBody UserDto userInfo) {
        return userService.login(request, userInfo);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        userService.logout(request);
    }

    @GetMapping
    public boolean checkSession(HttpServletRequest request) {
        return userService.checkSession(request);
    }
}
