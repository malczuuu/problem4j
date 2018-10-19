package pl.malczuuu.problem4j.spring.web;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "problem", ignoreInvalidFields = true)
public class ProblemProperties {

  private boolean showLoginFormOnUnauthorized = true;
  private boolean logExceptions = true;

  public boolean isShowLoginFormOnUnauthorized() {
    return showLoginFormOnUnauthorized;
  }

  public boolean isLogExceptions() {
    return logExceptions;
  }

  public void setShowLoginFormOnUnauthorized(boolean showLoginFormOnUnauthorized) {
    this.showLoginFormOnUnauthorized = showLoginFormOnUnauthorized;
  }

  public void setLogExceptions(boolean logExceptions) {
    this.logExceptions = logExceptions;
  }
}
