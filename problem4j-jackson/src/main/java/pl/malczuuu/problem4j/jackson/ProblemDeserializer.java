package pl.malczuuu.problem4j.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.net.URI;
import pl.malczuuu.problem4j.core.Problem;
import pl.malczuuu.problem4j.core.ProblemBuilder;

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

    node.fieldNames()
        .forEachRemaining(
            field -> {
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
                  builder.extension(field, new SerializableJsonNode(node.get(field)));
                  break;
              }
            });
    return builder.build();
  }
}
