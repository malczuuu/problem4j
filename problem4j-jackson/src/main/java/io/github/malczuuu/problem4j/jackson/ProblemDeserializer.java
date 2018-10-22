package io.github.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import io.github.malczuuu.problem4j.core.Problem;
import io.github.malczuuu.problem4j.core.ProblemBuilder;

final class ProblemDeserializer extends StdDeserializer<Problem> {

  ProblemDeserializer() {
    super(Problem.class);
  }

  @Override
  public Problem deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = jp.getCodec().readTree(jp);
    ProblemBuilder builder = Problem.builder();

    Iterator<String> fieldNames = node.fieldNames();
    while (fieldNames.hasNext()) {
      String field = fieldNames.next();
      switch (field) {
        case "type":
          builder.type(URI.create(node.get("type").textValue()));
          break;
        case "title":
          builder.title(node.get("title").textValue());
          break;
        case "status":
          builder.status(node.get("status").intValue());
          break;
        case "detail":
          builder.detail(node.get("detail").textValue());
          break;
        case "instance":
          builder.instance(URI.create(node.get("instance").textValue()));
          break;
        default:
          if (jp.getCodec() instanceof ObjectMapper) {
            ObjectMapper mapper = (ObjectMapper) jp.getCodec();
            builder.extension(field, mapper.treeToValue(node.get(field), Object.class));
          }
          break;
      }
    }
    return builder.build();
  }
}
