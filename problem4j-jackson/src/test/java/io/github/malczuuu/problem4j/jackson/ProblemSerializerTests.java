package io.github.malczuuu.problem4j.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.github.malczuuu.problem4j.core.Problem;

class ProblemSerializerTests {

  private final Instant timestamp =
      LocalDateTime.of(2018, 10, 1, 10, 43, 21, 221000000).toInstant(ZoneOffset.UTC);

  private final Problem problem =
      Problem.builder()
          .type(URI.create("http://localhost/FATAL"))
          .title("problem")
          .status(400)
          .detail("A serious problem")
          .instance(URI.create("http://localhost/endpoint/12"))
          .extension("userid", 100)
          .extension("timestamp", timestamp.toString())
          .build();

  private final String json =
      "{\"type\":\"http://localhost/FATAL\",\"title\":\"problem\",\"status\":400,\"detail\":\"A serious problem\",\"instance\":\"http://localhost/endpoint/12\",\"userid\":100,\"timestamp\":\"2018-10-01T10:43:21.221Z\"}";

  private final ObjectMapper mapper = new ObjectMapper();

  @BeforeEach
  void beforeEach() {
    mapper.registerModule(new ProblemModule());
  }

  @Test
  void testProblemSerializerOK() throws JsonProcessingException {
    String problemJson = mapper.writeValueAsString(problem);
    assertEquals(json, problemJson);
  }

  @Test
  void testDeserialize() throws IOException {
    Problem deserialized = mapper.readValue(json, Problem.class);
  }
}
