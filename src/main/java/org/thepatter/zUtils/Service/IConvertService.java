package org.thepatter.zUtils.Service;

import org.springframework.web.multipart.MultipartFile;
import org.thepatter.zUtils.req.ConvertFileReq;

public interface IConvertService {

    String convert(String str);

    void convert(ConvertFileReq file, String fileName);

    public String getConvertPath();
}
