package pl.malczuuu.problem4j.core;

import java.io.Serializable;
import java.net.URI;

public interface ProblemBuilder {

  ProblemBuilder type(URI type);

  ProblemBuilder title(String title);

  ProblemBuilder status(int status);

  ProblemBuilder detail(String detail);

  ProblemBuilder instance(URI instance);

  ProblemBuilder extension(String name, Serializable value);

  Problem build();
}
