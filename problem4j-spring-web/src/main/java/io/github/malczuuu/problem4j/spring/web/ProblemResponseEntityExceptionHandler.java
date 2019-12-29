package io.github.malczuuu.problem4j.spring.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy;
import io.github.malczuuu.problem4j.core.Problem;
import io.github.malczuuu.problem4j.core.ProblemBuilder;
import io.github.malczuuu.problem4j.core.ProblemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.MimeType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProblemResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger log =
      LoggerFactory.getLogger(ProblemResponseEntityExceptionHandler.class);

  private final JacksonProperties jacksonProperties;

  public ProblemResponseEntityExceptionHandler(JacksonProperties jacksonProperties) {
    this.jacksonProperties = jacksonProperties;
  }

  @ExceptionHandler({ProblemException.class})
  public ResponseEntity<Object> handleProblemException(ProblemException ex, WebRequest request) {
    Problem problem = ex.getProblem();
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder()
            .type(Problem.BLANK_TYPE)
            .title(status.getReasonPhrase())
            .status(status.value())
            .build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.METHOD_NOT_ALLOWED;
    ProblemBuilder builder =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Method " + ex.getMethod() + " not supported");
    if (ex.getSupportedMethods() != null) {
      builder.extension("supported", Arrays.asList(ex.getSupportedMethods()));
    }
    Problem problem = builder.build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Media type " + ex.getContentType() + " not supported")
            .extension("supported", new ArrayList<>(ex.getSupportedMediaTypes()))
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
      HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.NOT_ACCEPTABLE;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail(
                "Media type "
                    + headers.getAccept().stream()
                        .map(MimeType::toString)
                        .collect(Collectors.joining(", "))
                    + " not supported")
            .extension("supported", new ArrayList<>(ex.getSupportedMediaTypes()))
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Missing " + ex.getVariableName() + " path variable")
            .extension("name", ex.getVariableName())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail(
                "Missing "
                    + ex.getParameterName()
                    + " request param of type "
                    + ex.getParameterType())
            .extension("param", ex.getParameterName())
            .extension("type", ex.getParameterType().toLowerCase())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail(ex.getMessage())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
      ConversionNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(status.getReasonPhrase()).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Type mismatch of " + ex.getPropertyName() + " property")
            .extension("type", ex.getRequiredType())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Message not readable")
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(status.getReasonPhrase()).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        from(ex.getBindingResult()).title(status.getReasonPhrase()).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .type(Problem.BLANK_TYPE)
            .title(status.getReasonPhrase())
            .status(status.value())
            .detail("Missing " + ex.getRequestPartName() + " request part")
            .extension("param", ex.getRequestPartName())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        from(ex.getBindingResult()).title(status.getReasonPhrase()).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ProblemBuilder from(BindingResult bindingResult) {
    ArrayList<ValidationError> details = new ArrayList<>();
    bindingResult
        .getFieldErrors()
        .forEach(
            f -> details.add(new ValidationError(fieldName(f.getField()), f.getDefaultMessage())));
    return Problem.builder().detail("Validation failed").extension("errors", details);
  }

  private String fieldName(String field) {
    switch (jacksonProperties.getPropertyNamingStrategy()) {
      case "SNAKE_CASE":
        return ((SnakeCaseStrategy) PropertyNamingStrategy.SNAKE_CASE).translate(field);
      case "UPPER_CAMEL_CASE":
        return ((UpperCamelCaseStrategy) PropertyNamingStrategy.UPPER_CAMEL_CASE).translate(field);
      case "KEBAB_CASE":
        return ((KebabCaseStrategy) PropertyNamingStrategy.KEBAB_CASE).translate(field);
      case "LOWER_CASE":
        return ((LowerCaseStrategy) PropertyNamingStrategy.LOWER_CASE).translate(field);
      case "LOWER_CAMEL_CASE":
      default:
        return field;
    }
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    status = HttpStatus.NOT_FOUND;
    Problem problem =
        Problem.builder()
            .type(Problem.BLANK_TYPE)
            .title(status.getReasonPhrase())
            .status(status.value())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder()
            .type(Problem.BLANK_TYPE)
            .title(status.getReasonPhrase())
            .status(status.value())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    if (body instanceof Problem) {
      headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
    }
    log(request, ex);
    return super.handleExceptionInternal(ex, body, headers, status, request);
  }

  private void log(WebRequest request, Exception ex) {
    if (request instanceof ServletWebRequest) {
      log((ServletWebRequest) request, ex);
    } else {
      if (log.isDebugEnabled()) {
        log.debug(
            "Unhandled exception {} : {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
      } else {
        log.info("Unhandled exception {} : {}", ex.getClass().getSimpleName(), ex.getMessage());
      }
    }
  }

  private void log(ServletWebRequest request, Exception ex) {
    log(request.getRequest(), ex);
  }

  private void log(HttpServletRequest request, Exception ex) {
    if (log.isDebugEnabled()) {
      log.debug(
          "Unhandled exception {} : {} on {} {}",
          ex.getClass().getSimpleName(),
          ex.getMessage(),
          request.getMethod(),
          path(request),
          ex);
    } else {
      log.info(
          "Unhandled exception {} : {} on {} {}",
          ex.getClass().getSimpleName(),
          ex.getMessage(),
          request.getMethod(),
          path(request));
    }
  }

  private String path(HttpServletRequest req) {
    String result = req.getServletPath();
    if (req.getQueryString() != null && !req.getQueryString().isEmpty()) {
      result += "?" + req.getQueryString();
    }
    return result;
  }
}
