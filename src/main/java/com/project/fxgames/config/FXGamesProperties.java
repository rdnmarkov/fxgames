package com.project.fxgames.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "fxgames")
public class FXGamesProperties {

    private Long maxSize;
    private Long sizeColumn;

    public Long needSize(){
        return maxSize/sizeColumn;
    }
}
