package pl.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.malczuuu.problem4j.core.Problem;
import java.io.IOException;

final class ProblemSerializer extends StdSerializer<Problem> {

  ProblemSerializer() {
    super(Problem.class);
  }

  @Override
  public void serialize(Problem value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    if (value.getType() != null) {
      gen.writeStringField("type", value.getType().toString());
    } else {
      gen.writeStringField("type", "about:blank");
    }
    if (value.getTitle() != null) {
      gen.writeStringField("title", value.getTitle());
    }
    gen.writeNumberField("status", value.getStatus());
    if (value.getDetail() != null) {
      gen.writeStringField("detail", value.getDetail());
    }
    if (value.getInstance() != null) {
      gen.writeStringField("instance", value.getInstance().toString());
    }
    for (String k : value.getExtensions()) {
      if (k != null && value.getExtensionValue(k) != null) {
        gen.writeObjectField(k, value.getExtensionValue(k));
      }
    }
    gen.writeEndObject();
  }
}
