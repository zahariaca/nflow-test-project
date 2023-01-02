package com.zahariaca.nflowtest.config;

import com.zahariaca.nflowtest.flow.ExampleWorkflow;
import io.nflow.engine.config.EngineConfiguration;
import io.nflow.rest.config.RestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Slf4j
@Import({EngineConfiguration.class, RestConfiguration.class})
@Configuration
public class ApplicationConfig {
    @Bean
    RouterFunction<ServerResponse> staticResourceRouter(){
        return RouterFunctions.resources("/**", new ClassPathResource("static/"));
    }
}
