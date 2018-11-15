package io.github.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import io.github.malczuuu.problem4j.core.Problem;
import java.util.Collections;

public class ProblemModule extends SimpleModule {

  public ProblemModule() {
    super("ProblemModule");
    _serializers = new SimpleSerializers(Collections.singletonList(new ProblemSerializer()));
    _deserializers =
        new SimpleDeserializers(Collections.singletonMap(Problem.class, new ProblemDeserializer()));
  }
}
