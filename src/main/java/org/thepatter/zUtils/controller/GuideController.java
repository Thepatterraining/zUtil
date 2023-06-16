package org.thepatter.zUtils.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thepatter.zUtils.Service.IGuideService;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/guide")
public class GuideController {

    @Autowired
    private IGuideService guideService;

    public static Logger getLog() {
        return log;
    }

    @GetMapping("/default")
    public int optionalElse() {
        return guideService.getNum();
    }

}
