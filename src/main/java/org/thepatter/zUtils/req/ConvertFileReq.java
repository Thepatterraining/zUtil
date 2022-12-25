package org.thepatter.zUtils.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class ConvertFileReq implements Serializable {

    @NotNull
    private MultipartFile file;

    private String beConvertField;

    private String convertField;

    @Override
    public String toString() {
        return "ConvertFileReq{" +
                "file=" + file +
                ", beConvertField='" + beConvertField + '\'' +
                ", convertField='" + convertField + '\'' +
                '}';
    }
}
