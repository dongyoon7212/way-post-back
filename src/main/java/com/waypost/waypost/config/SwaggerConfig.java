package com.waypost.waypost.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Way Post API")
                        .description("위치기반 여행 게시물 커뮤니티 API 문서")
                        .version("v1.0"));
    }
}
