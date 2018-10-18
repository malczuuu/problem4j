package pl.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.malczuuu.problem4j.core.Problem;
import pl.malczuuu.problem4j.core.ProblemBuilder;
import java.io.IOException;
import java.net.URI;

public final class ProblemDeserializer extends StdDeserializer<Problem> {

  public ProblemDeserializer() {
    this(Problem.class);
  }

  protected ProblemDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Problem deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    ProblemBuilder builder = Problem.builder();
    if (node.has("type")) {
      builder.type(URI.create(node.get("type").textValue()));
    }
    if (node.has("title")) {
      builder.title(node.get("title").textValue());
    }
    if (node.has("status")) {
      builder.status(node.get("status").intValue());
    }
    if (node.has("detail")) {
      builder.detail(node.get("detail").textValue());
    }
    if (node.has("instance")) {
      builder.instance(URI.create(node.get("instance").textValue()));
    }
    // TODO deserialize extensions
    return builder.build();
  }
}
