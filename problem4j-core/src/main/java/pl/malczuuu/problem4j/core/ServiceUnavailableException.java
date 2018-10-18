package pl.malczuuu.problem4j.core;

import java.net.URI;

public class ServiceUnavailableException extends ProblemException {

  private static final int STATUS_CODE = 503;
  private static final String REASON_PHRASE = "Service unavailable";

  public ServiceUnavailableException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public ServiceUnavailableException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public ServiceUnavailableException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public ServiceUnavailableException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public ServiceUnavailableException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public ServiceUnavailableException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public ServiceUnavailableException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public ServiceUnavailableException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public ServiceUnavailableException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public ServiceUnavailableException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
