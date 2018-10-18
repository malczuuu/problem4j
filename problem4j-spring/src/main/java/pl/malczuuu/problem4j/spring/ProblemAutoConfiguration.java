package pl.malczuuu.problem4j.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {ProblemAutoConfiguration.class})
public class ProblemAutoConfiguration {

  @Bean
  @ConditionalOnClass({ProblemSupplier.class})
  public ProblemSupplier method() {
    return new DefaultProblemSupplier();
  }
}
