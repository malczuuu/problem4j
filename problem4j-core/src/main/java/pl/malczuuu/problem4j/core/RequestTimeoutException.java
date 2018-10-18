package pl.malczuuu.problem4j.core;

import java.net.URI;

public class RequestTimeoutException extends ProblemException {

  private static final int STATUS_CODE = 408;
  private static final String REASON_PHRASE = "Request timeout";

  public RequestTimeoutException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public RequestTimeoutException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public RequestTimeoutException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public RequestTimeoutException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public RequestTimeoutException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public RequestTimeoutException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public RequestTimeoutException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public RequestTimeoutException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public RequestTimeoutException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public RequestTimeoutException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
