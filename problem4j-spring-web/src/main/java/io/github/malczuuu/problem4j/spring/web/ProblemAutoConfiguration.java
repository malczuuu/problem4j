package io.github.malczuuu.problem4j.spring.web;

import io.github.malczuuu.problem4j.jackson.ProblemModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ProblemAutoConfiguration.class})
public class ProblemAutoConfiguration {

  @ConditionalOnMissingBean
  @Bean
  public ProblemModule problemModule() {
    return new ProblemModule();
  }
}
