package io.github.malczuuu.problem4j.core;

import java.net.URI;

public class UnavailableForLegalReasonsException extends ProblemException {

  private static final int STATUS_CODE = 451;
  private static final String REASON_PHRASE = "Unavailable for legal reasons";

  public UnavailableForLegalReasonsException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public UnavailableForLegalReasonsException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public UnavailableForLegalReasonsException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public UnavailableForLegalReasonsException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public UnavailableForLegalReasonsException(
      String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public UnavailableForLegalReasonsException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public UnavailableForLegalReasonsException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public UnavailableForLegalReasonsException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public UnavailableForLegalReasonsException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public UnavailableForLegalReasonsException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
