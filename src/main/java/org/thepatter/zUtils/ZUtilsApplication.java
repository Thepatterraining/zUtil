package org.thepatter.zUtils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.thepatter.zUtils.Service.Impl.ConvertService;

@SpringBootApplication
@Slf4j
public class ZUtilsApplication {

    public static void main(String[] args) {

        SpringApplication.run(ZUtilsApplication.class, args);
        log.info("ZUtils run success");
    }
}