package pl.malczuuu.problem4j.core;

import java.net.URI;

public class GatewayTimeoutException extends ProblemException {

  private static final int STATUS_CODE = 504;
  private static final String REASON_PHRASE = "Gateway timeout";

  public GatewayTimeoutException() {
    super(REASON_PHRASE, STATUS_CODE);
  }

  public GatewayTimeoutException(Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, cause);
  }

  public GatewayTimeoutException(String message) {
    super(REASON_PHRASE, STATUS_CODE, message);
  }

  public GatewayTimeoutException(String message, Throwable cause) {
    super(REASON_PHRASE, STATUS_CODE, message, cause);
  }

  public GatewayTimeoutException(String title, String detail) {
    super(title, STATUS_CODE, detail);
  }

  public GatewayTimeoutException(String title, String detail, Throwable cause) {
    super(title, STATUS_CODE, detail, cause);
  }

  public GatewayTimeoutException(String title, String detail, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, extensions);
  }

  public GatewayTimeoutException(
      String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(title, STATUS_CODE, detail, cause, extensions);
  }

  public GatewayTimeoutException(
      URI type, String title, String detail, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, extensions);
  }

  public GatewayTimeoutException(
      URI type, String title, String detail, Throwable cause, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, cause, extensions);
  }

  public GatewayTimeoutException(
      URI type, String title, String detail, URI instance, Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, extensions);
  }

  public GatewayTimeoutException(
      URI type,
      String title,
      String detail,
      URI instance,
      Throwable cause,
      Problem.Extension... extensions) {
    super(type, title, STATUS_CODE, detail, instance, cause, extensions);
  }
}
