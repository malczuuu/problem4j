package pl.malczuuu.problem4j.core;

import java.net.URI;

public class NotFoundException extends ProblemException {

  private static final int STATUS_CODE = 404;
  private static final String REASON_PHRASE = "Not found";

  public NotFoundException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public NotFoundException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public NotFoundException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public NotFoundException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public NotFoundException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public NotFoundException(URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public NotFoundException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public NotFoundException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public NotFoundException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
