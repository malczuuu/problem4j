package pl.malczuuu.problem4j.core;

import java.net.URI;

public class InsufficientStorageException extends ProblemException {

  private static final int STATUS_CODE = 507;
  private static final String REASON_PHRASE = "Insufficient storage";

  public InsufficientStorageException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public InsufficientStorageException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public InsufficientStorageException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public InsufficientStorageException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public InsufficientStorageException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public InsufficientStorageException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public InsufficientStorageException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public InsufficientStorageException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public InsufficientStorageException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public InsufficientStorageException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
