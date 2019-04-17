package kursevicius.karolis.dbforms.bean;

import lombok.Value;

import java.util.Map;

@Value
public class GenerateRequest {
    String templateName;
    Map<String, ?> data;
}
