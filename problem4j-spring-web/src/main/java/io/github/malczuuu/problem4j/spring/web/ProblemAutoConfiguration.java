package io.github.malczuuu.problem4j.spring.web;

import com.fasterxml.jackson.databind.Module;
import io.github.malczuuu.problem4j.jackson.ProblemModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ProblemAutoConfiguration.class})
@EnableConfigurationProperties(ProblemProperties.class)
public class ProblemAutoConfiguration {

  @ConditionalOnMissingBean
  @Bean
  public ProblemSupplier problemSupplier() {
    return new DefaultProblemSupplier();
  }

  @ConditionalOnMissingBean
  @Bean
  public Module problemModule() {
    return new ProblemModule();
  }
}
