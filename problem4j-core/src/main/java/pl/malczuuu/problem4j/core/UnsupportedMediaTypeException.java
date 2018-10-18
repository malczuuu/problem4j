package pl.malczuuu.problem4j.core;

import java.net.URI;

public class UnsupportedMediaTypeException extends ProblemException {

  private static final int STATUS_CODE = 415;
  private static final String REASON_PHRASE = "Unsupported media type";

  public UnsupportedMediaTypeException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public UnsupportedMediaTypeException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public UnsupportedMediaTypeException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public UnsupportedMediaTypeException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public UnsupportedMediaTypeException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public UnsupportedMediaTypeException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public UnsupportedMediaTypeException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public UnsupportedMediaTypeException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public UnsupportedMediaTypeException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public UnsupportedMediaTypeException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
