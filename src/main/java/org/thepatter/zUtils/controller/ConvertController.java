package org.thepatter.zUtils.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thepatter.zUtils.Service.IConvertService;
import org.thepatter.zUtils.Service.Impl.ConvertService;
import org.thepatter.zUtils.req.ConvertFileReq;
import org.thepatter.zUtils.utils.LocalDateTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/convert")
@Slf4j
public class ConvertController {

    @Autowired
    private IConvertService convertService;

    @GetMapping("/string/{str}")
    public String convertString(@PathVariable String str) {
        if (str.length() == 0) {
            return "application error,because params is null";
        }
        return convertService.convert(str);
    }

    @PostMapping("/file")
    public String convertFile(ConvertFileReq fileReq) {
        String fileName = convertService.getConvertPath() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "convert.xlsx";
        convertService.convert(fileReq, fileName);
        return fileName;
    }

    @GetMapping("/timeTest")
    public void timeTest() throws ParseException {
        Date date = new Date(1669759566L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,10,30,6,6,6);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("Date:{}",date);

        log.info("Date format:{}",format.format(date));
        log.info("Calendar:{}",calendar.getTime());
        log.info("Caldendar format:{}",format.format(calendar.getTime()));
        log.info("Date of time:{}",date.getTime());
//        LocalDateTime time = LocalDateTime.now();
        LocalDateTime time5 = LocalDateTime.of(2022,12,30,6,6,6);
//        long millisecondsSystem = System.currentTimeMillis();
        log.info("time:{}",time5);
        log.info(LocalDateTimeUtil.format(time5));
//        log.info(LocalDateTimeUtil.format(time5, "YYYY-MM-dd HH:mm:ss"));
//        log.info(LocalDateTimeUtil.format(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        long milliseconds = LocalDateTimeUtil.toTimestampAtMilliseconds(time);
//        long seconds = LocalDateTimeUtil.toTimestampAtSeconds(time);
//        log.info("毫秒：{}",milliseconds);
//        log.info("毫秒2：{}",millisecondsSystem);
//        log.info("秒：{}",seconds);
//        LocalDateTime time2 = LocalDateTimeUtil.toLocalDateTimeByMillis(milliseconds);
//        log.info("毫秒转换:{}",LocalDateTimeUtil.format(time2));
//        LocalDateTime time3 = LocalDateTimeUtil.toLocalDateTimeBySeconds(seconds);
//        log.info("秒转换:{}",LocalDateTimeUtil.format(time3));
    }

}
