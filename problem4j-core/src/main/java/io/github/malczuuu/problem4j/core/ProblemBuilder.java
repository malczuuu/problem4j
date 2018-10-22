package io.github.malczuuu.problem4j.core;

import java.net.URI;

public interface ProblemBuilder {

  ProblemBuilder type(URI type);

  ProblemBuilder title(String title);

  ProblemBuilder status(int status);

  ProblemBuilder detail(String detail);

  ProblemBuilder instance(URI instance);

  ProblemBuilder extension(String name, Object value);

  Problem build();
}
