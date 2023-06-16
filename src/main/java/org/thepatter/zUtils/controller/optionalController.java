package org.thepatter.zUtils.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/optional")
public class optionalController {

    public static Logger getLog() {
        return log;
    }

    @GetMapping("/else")
    public int optionalElse() {
        Optional<Integer> optionalInt = Optional.empty();

        return optionalInt.orElseThrow(() -> {
                return new RuntimeException("异常了");
        });
    }

    @GetMapping("flatMap")
    public String optionalMap() {
        Optional<String> res = Optional.of(12).flatMap(this::doubleInt).flatMap(this::intToStr);
        return res.get();
    }

    public Optional<String> intToStr(int x) {
        return Optional.ofNullable(String.valueOf(x));
    }

    public Optional<Integer> doubleInt(int x) {
        return Optional.ofNullable(x << 1);
    }
}
