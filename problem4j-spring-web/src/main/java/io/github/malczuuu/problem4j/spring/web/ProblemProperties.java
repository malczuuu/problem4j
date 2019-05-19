package io.github.malczuuu.problem4j.spring.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "problem", ignoreInvalidFields = true)
public class ProblemProperties {

  private String wwwAuthenticateRealm;

  public String getWwwAuthenticateRealm() {
    return wwwAuthenticateRealm;
  }

  public void setWwwAuthenticateRealm(String wwwAuthenticateRealm) {
    this.wwwAuthenticateRealm = wwwAuthenticateRealm;
  }
}
