package io.github.malczuuu.problem4j.spring.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WwwAuthenticateFilter implements Filter {

  private final ProblemProperties problemProperties;

  public WwwAuthenticateFilter(ProblemProperties problemProperties) {
    this.problemProperties = problemProperties;
  }

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(request, response);
    if (((HttpServletResponse) response).getStatus() == HttpStatus.UNAUTHORIZED.value()
        && problemProperties.getWwwAuthenticate() != null) {
      ((HttpServletResponse) response)
          .setHeader("WWW-Authenticate", problemProperties.getWwwAuthenticate());
    }
  }

  @Override
  public void destroy() {}
}
