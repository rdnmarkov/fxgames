package com.project.fxgames.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "fxgames")
public class FXGamesProperties {

    private Long limit;
}
