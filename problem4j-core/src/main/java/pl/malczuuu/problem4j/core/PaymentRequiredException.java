package pl.malczuuu.problem4j.core;

import java.net.URI;

public class PaymentRequiredException extends ProblemException {

  private static final int STATUS_CODE = 402;
  private static final String REASON_PHRASE = "Payment required";

  public PaymentRequiredException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public PaymentRequiredException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public PaymentRequiredException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public PaymentRequiredException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public PaymentRequiredException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public PaymentRequiredException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public PaymentRequiredException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public PaymentRequiredException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public PaymentRequiredException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public PaymentRequiredException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
