package pl.malczuuu.problem4j.spring;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.malczuuu.problem4j.core.Problem;
import pl.malczuuu.problem4j.core.ProblemBuilder;
import pl.malczuuu.problem4j.core.ProblemException;
import pl.malczuuu.problem4j.core.UnauthorizedException;

@SuppressWarnings("NullableProblems")
@RestControllerAdvice
public class ProblemResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log =
      LoggerFactory.getLogger(ProblemResponseEntityExceptionHandler.class);

  private final ProblemSupplier problemSupplier;
  private final ProblemProperties properties;

  public ProblemResponseEntityExceptionHandler(
      ProblemSupplier problemSupplier, ProblemProperties properties) {
    this.problemSupplier = problemSupplier;
    this.properties = properties;
  }

  @ExceptionHandler({ProblemException.class})
  public ResponseEntity<Object> handleProblemException(ProblemException ex, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    HttpHeaders headers = new HttpHeaders();
    if (ex instanceof UnauthorizedException && properties.isShowLoginFormOnUnauthorized()) {
      headers.set("WWW-Authenticate", "Basic realm=\"Basic\", charset=\"UTF-8\"");
    }
    HttpStatus status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    HttpStatus status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
      ConversionNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    Problem problem = problemSupplier.from(ex).build();
    status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
    if (body instanceof Problem) {
      body = enhanceProblem((Problem) body);
      if (properties.isLogExceptions()) {
        log.warn(
            "Exception {} with message='{}' occurred, details={}",
            ex.getClass().getSimpleName(), ex.getMessage(), body, ex);
      }
    } else {
      if (properties.isLogExceptions()) {
        log.warn(
            "Exception {} with message='{}' occurred",
            ex.getClass().getSimpleName(), ex.getMessage(), ex);
      }
    }
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private Problem enhanceProblem(Problem problem) {
    ProblemBuilder builder = Problem.builder(problem);
    if (!problem.hasExtension("timestamp")) {
      builder.extension("timestamp", Instant.now());
    }
    return builder.build();
  }
}
