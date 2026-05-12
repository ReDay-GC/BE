package ReDay.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AiServerConfig {

    @Value("${ai.server.base-url}")
    private String baseUrl;

    @Bean
    public RestClient aiServerRestClient() {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
