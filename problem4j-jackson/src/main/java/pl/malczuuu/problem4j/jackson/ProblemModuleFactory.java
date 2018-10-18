package pl.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.malczuuu.problem4j.core.Problem;

public final class ProblemModuleFactory {

  public SimpleModule create() {
    SimpleModule module = new SimpleModule("ProblemModule");
    module.addSerializer(Problem.class, new ProblemSerializer());
    module.addDeserializer(Problem.class, new ProblemDeserializer());
    return module;
  }
}
