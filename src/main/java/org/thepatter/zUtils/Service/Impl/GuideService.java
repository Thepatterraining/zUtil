package org.thepatter.zUtils.Service.Impl;

import org.springframework.stereotype.Service;
import org.thepatter.zUtils.Service.IGuideNewService;
import org.thepatter.zUtils.Service.IGuideService;

import java.time.ZonedDateTime;

@Service
public class GuideService implements IGuideService, IGuideNewService {

    @Override
    public Integer getNum() {
        return IGuideService.super.getNum();
    }
}
