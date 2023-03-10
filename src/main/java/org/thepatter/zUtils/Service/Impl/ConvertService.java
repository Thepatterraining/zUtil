package org.thepatter.zUtils.Service.Impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.thepatter.zUtils.Service.IConvertService;
import org.thepatter.zUtils.req.ConvertFileReq;

import java.awt.geom.Area;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@ConfigurationProperties(prefix = "convert")
@Setter
@Getter
public class ConvertService implements IConvertService {

    private Map<Character,Character> strMap;

    private String defaultBeConvertedField;

    private String defaultConvertedField;

    public String convertPath;

    ConvertService() {
        Map<Character,Character> strMap = MapUtil.builder('R','0')
                .put('J','1')
                .put('I','2')
                .put('N','3')
                .put('G','4')
                .put('Y','5')
                .put('O','6')
                .put('U','7')
                .put('C','8')
                .put('A','9')
                .put('1','J')
                .put('2','I')
                .put('3','N')
                .put('4','G')
                .put('5','Y')
                .put('6','O')
                .put('7','U')
                .put('8','C')
                .put('9','A')
                .put('0','R').build();
        this.strMap = strMap;

    }

    @Override
    public String getConvertPath() {
        return convertPath;
    }

    @Override
    public String convert(String str) {
        if (!ObjectUtils.isEmpty(str)) {
            return "";
        }
        log.info("convert map:{}",strMap.toString());
        str = str.toUpperCase();
        log.info("convert string param:{}",str);
        char[] convertRes = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);
            Character newStr = strMap.get(s);
            if (ObjectUtils.isEmpty(newStr)) {
                convertRes[i] = s;
            } else {
                convertRes[i] = newStr;
            }
        }
        if (convertRes.length == 0) {
            return "error, convert result is empty";
        }
        return String.valueOf(convertRes);
    }

    @Override
    @Async
    public void convert(ConvertFileReq fileReq, String fileName) {
        log.info("convert file param:{},fileName:{}", fileReq, fileName);

        try{
            int sheetIndex = 0;
            ExcelReader reader = null;

            do{
                reader = ExcelUtil.getReader(fileReq.getFile().getInputStream(),sheetIndex);
                List<Map<String, Object>> list = reader.readAll();
                for (Map<String, Object> map : list) {
                    //???????????????????????????????????????????????????
                    String beConvertedField = defaultBeConvertedField;
                    if (!ObjectUtils.isEmpty(fileReq.getBeConvertField())) {
                        beConvertedField = fileReq.getBeConvertField();
                    }
                    if (!ObjectUtils.isEmpty(map.get(beConvertedField))) {
                        //???????????????
                        String newStr = convert(map.get(beConvertedField).toString());
                        //????????????????????????????????????????????????
                        String convertedField = defaultConvertedField;
                        if (!ObjectUtils.isEmpty(fileReq.getConvertField())) {
                            convertedField = fileReq.getConvertField();
                        }
                        //???????????????????????????
                        map.put(convertedField, newStr);
                    }
                }

                //??????excel
                ExcelWriter writer = ExcelUtil.getWriter(fileName, reader.getSheet().getSheetName());
                writer.write(list);
                // ??????writer???????????????
                writer.close();
                //????????????sheet
                ++sheetIndex;
            } while (reader.getSheetCount() > sheetIndex);
        } catch (IOException e) {
            log.error("upload file is error");
        }
    }

}
