package pl.malczuuu.problem4j.spring;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.malczuuu.problem4j.jackson.ProblemModuleFactory;

@Configuration
@ComponentScan(basePackageClasses = {ProblemAutoConfiguration.class})
public class ProblemAutoConfiguration {

  @ConditionalOnMissingBean
  @Bean
  public ProblemSupplier problemSupplier() {
    return new DefaultProblemSupplier();
  }

  @ConditionalOnMissingBean
  @Bean
  public SimpleModule problemModule() {
    return new ProblemModuleFactory().create();
  }
}
