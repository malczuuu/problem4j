package io.github.malczuuu.problem4j.jackson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.malczuuu.problem4j.core.Problem;
import java.io.IOException;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  void shouldSerializeProblem() throws JsonProcessingException {
    String problemJson = mapper.writeValueAsString(problem);

    assertEquals(json, problemJson);
  }

  @Test
  void shouldDeserializeProblem() throws IOException {
    Problem problem = mapper.readValue(json, Problem.class);

    assertEquals(URI.create("http://localhost/FATAL"), problem.getType());
    assertEquals("problem", problem.getTitle());
    assertEquals(400, problem.getStatus());
    assertEquals("A serious problem", problem.getDetail());
    assertEquals(URI.create("http://localhost/endpoint/12"), problem.getInstance());

    assertEquals(2, problem.getExtensions().size());

    assertTrue(problem.hasExtension("userid"));
    assertTrue(problem.hasExtension("timestamp"));

    assertEquals(100, problem.getExtensionValue("userid"));
    assertEquals("2018-10-01T10:43:21.221Z", problem.getExtensionValue("timestamp"));
  }
}
