package io.github.malczuuu.problem4j.core;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public interface Problem {

  URI BLANK_TYPE = URI.create("about:blank");
  String CONTENT_TYPE = "application/problem+json";

  static ProblemBuilder builder() {
    return new ProblemBuilderImpl();
  }

  static ProblemBuilder builder(Problem problem) {
    ProblemBuilder builder = new ProblemBuilderImpl();
    builder.type(problem.getType());
    builder.title(problem.getTitle());
    builder.status(problem.getStatus());
    builder.detail(problem.getDetail());
    builder.instance(problem.getInstance());
    problem.getExtensions().forEach(e -> builder.extension(e, problem.getExtensionValue(e)));
    return builder;
  }

  static DefaultProblem.Extension extension(String key, Object value) {
    return new DefaultProblem.Extension(key, value);
  }

  URI getType();

  String getTitle();

  int getStatus();

  String getDetail();

  URI getInstance();

  Set<String> getExtensions();

  Object getExtensionValue(String name);

  boolean hasExtension(String extension);

  interface Extension extends Map.Entry<String, Object> {}
}
