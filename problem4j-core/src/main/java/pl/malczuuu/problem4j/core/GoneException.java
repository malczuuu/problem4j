package pl.malczuuu.problem4j.core;

import java.net.URI;

public class GoneException extends ProblemException {

  private static final int STATUS_CODE = 410;
  private static final String REASON_PHRASE = "Gone";

  public GoneException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public GoneException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public GoneException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public GoneException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public GoneException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public GoneException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public GoneException(URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public GoneException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public GoneException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public GoneException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
