package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReqresResponseGetModels {
    private String page;
    private String per_page;
    private String total;
    private String total_pages;
}
