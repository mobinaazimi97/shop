package com.mftplus.shop.config;
//This Class For : No dash for UuId in body

import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public Module uuidSanitizerModule() {
        return new UuIdSanitizerModule();
    }
}
