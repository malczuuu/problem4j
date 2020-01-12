package io.github.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.malczuuu.problem4j.core.Problem;
import java.io.IOException;

class ProblemSerializer extends StdSerializer<Problem> {

  ProblemSerializer() {
    super(Problem.class);
  }

  @Override
  public void serialize(Problem problem, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    jsonGenerator.writeStartObject();
    if (problem.getType() != null) {
      jsonGenerator.writeStringField("type", problem.getType().toString());
    } else {
      jsonGenerator.writeStringField("type", Problem.BLANK_TYPE.toString());
    }
    if (problem.getTitle() != null) {
      jsonGenerator.writeStringField("title", problem.getTitle());
    }
    jsonGenerator.writeNumberField("status", problem.getStatus());
    if (problem.getDetail() != null) {
      jsonGenerator.writeStringField("detail", problem.getDetail());
    }
    if (problem.getInstance() != null) {
      jsonGenerator.writeStringField("instance", problem.getInstance().toString());
    }
    for (String k : problem.getExtensions()) {
      if (k != null && problem.getExtensionValue(k) != null) {
        jsonGenerator.writeObjectField(k, problem.getExtensionValue(k));
      }
    }
    jsonGenerator.writeEndObject();
  }
}
