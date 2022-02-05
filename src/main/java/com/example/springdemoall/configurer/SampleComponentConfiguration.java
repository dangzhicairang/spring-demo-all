package com.example.springdemoall.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleComponentConfiguration extends AbstractSampleComponentConfiguration {

    @Bean
    public SampleComponent sampleComponent() {
        SampleComponent sampleComponent = new SampleComponent();

        // 基于 SampleComponentConfigurer 进行配置
        sampleComponent.configurer(name, age);
        return sampleComponent;
    }

}
