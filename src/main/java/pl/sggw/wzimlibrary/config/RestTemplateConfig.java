package pl.sggw.wzimlibrary.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate(
            final RestTemplateBuilder builder
    ) {
        return builder
                .rootUri("https://wolnelektury.pl/api/")
                .build();
    }
}
