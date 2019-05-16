package com.realtime.nyc.subway.configuration;

import static com.realtime.nyc.subway.utils.Constants.REST_CLIENT_TIMEOUT;

import com.googlecode.protobuf.format.JsonFormat;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        restTemplateBuilder.setConnectTimeout(Duration.ofMinutes(REST_CLIENT_TIMEOUT));
        restTemplateBuilder.setReadTimeout(Duration.ofMinutes(REST_CLIENT_TIMEOUT));
        return restTemplateBuilder.build();
    }

    @Bean
    public JsonFormat jsonFormat() {
        return new JsonFormat();
    }
}
