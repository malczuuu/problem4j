package io.github.malczuuu.problem4j.spring.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "problem", ignoreInvalidFields = true)
public class ProblemProperties {

  private String wwwAuthenticate;

  public String getWwwAuthenticate() {
    return wwwAuthenticate;
  }

  public void setWwwAuthenticate(String wwwAuthenticate) {
    this.wwwAuthenticate = wwwAuthenticate;
  }
}
