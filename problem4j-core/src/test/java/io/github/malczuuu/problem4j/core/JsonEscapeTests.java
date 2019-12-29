package io.github.malczuuu.problem4j.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class JsonEscapeTests {

  private static class EscapingData {

    public static EscapingData from(String string) {
      String[] splited = string.split(",");
      return new EscapingData(splited[0], splited[1]);
    }

    private final String given;
    private final String expected;

    private EscapingData(String given, String expected) {
      this.given = given;
      this.expected = expected;
    }
  }

  @ParameterizedTest
  @ValueSource(
      strings = {"\",\\\"", "\\,\\\\", "\b,\\b", "\f,\\f", "\n,\\n", "\r,\\r", "\t,\\t", "/,\\/"})
  void shouldEscapeSpecialCharactersWithSlash(String value) {
    EscapingData data = EscapingData.from(value);

    String result = JsonEscape.escape(data.given);

    assertEquals(data.expected, result, "Failure in escaping \"" + data.expected + "\"");
  }
}
