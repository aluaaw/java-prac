package com.kungca.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final BuildProperties properties;

    @GetMapping
    public String version(@Value("${server.version}") String version) {
        return properties.getName() + " / " + version;
    }
}
