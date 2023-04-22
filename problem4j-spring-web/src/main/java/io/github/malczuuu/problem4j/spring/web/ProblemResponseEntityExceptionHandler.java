package io.github.malczuuu.problem4j.spring.web;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.KebabCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.LowerCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.UpperCamelCaseStrategy;
import io.github.malczuuu.problem4j.core.Problem;
import io.github.malczuuu.problem4j.core.ProblemBuilder;
import io.github.malczuuu.problem4j.core.ProblemException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.http.*;
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
    HttpStatusCode status = HttpStatus.valueOf(problem.getStatus());
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
    HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  private String getReasonPhrase(HttpStatusCode statusCode) {
    HttpStatus status = HttpStatus.resolve(statusCode.value());
    if (status != null) {
      return status.getReasonPhrase();
    }
    return "";
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.METHOD_NOT_ALLOWED;
    ProblemBuilder builder =
        Problem.builder()
            .title(getReasonPhrase(status))
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
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
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
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.NOT_ACCEPTABLE;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
            .status(status.value())
            .detail(
                "Media type "
                    + headers.getAccept().stream()
                        .map(MimeType::toString)
                        .collect(Collectors.joining(", "))
                    + " not acceptable")
            .extension("acceptable", new ArrayList<>(ex.getSupportedMediaTypes()))
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(
      MissingPathVariableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
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
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
            .status(status.value())
            .detail(
                "Missing "
                    + ex.getParameterName()
                    + " request param of type "
                    + ex.getParameterType().toLowerCase())
            .extension("param", ex.getParameterName())
            .extension("type", ex.getParameterType().toLowerCase())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
            .status(status.value())
            .detail(ex.getMessage())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(
      ConversionNotSupportedException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(
      TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    ProblemBuilder problemBuilder =
        Problem.builder()
            .title(getReasonPhrase(status))
            .status(status.value())
            .detail("Type mismatch of " + ex.getPropertyName() + " property");
    if (ex.getRequiredType() != null) {
      problemBuilder =
          problemBuilder.extension("type", ex.getRequiredType().getSimpleName().toLowerCase());
    }
    return handleExceptionInternal(ex, problemBuilder.build(), headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(
      HttpMessageNotWritableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        from(ex.getBindingResult()).title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      MissingServletRequestPartException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        Problem.builder()
            .title(getReasonPhrase(status))
            .status(status.value())
            .detail("Missing " + ex.getRequestPartName() + " request part")
            .extension("param", ex.getRequestPartName())
            .build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    status = HttpStatus.BAD_REQUEST;
    Problem problem =
        from(ex.getBindingResult()).title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ProblemBuilder from(BindingResult bindingResult) {
    ArrayList<Violation> details = new ArrayList<>();
    bindingResult
        .getFieldErrors()
        .forEach(f -> details.add(new Violation(fieldName(f.getField()), f.getDefaultMessage())));
    return Problem.builder().detail("Validation failed").extension("errors", details);
  }

  private String fieldName(String field) {
    if (jacksonProperties.getPropertyNamingStrategy() == null) {
      return field;
    }
    return switch (jacksonProperties.getPropertyNamingStrategy()) {
      case "SNAKE_CASE" -> ((SnakeCaseStrategy) PropertyNamingStrategies.SNAKE_CASE)
          .translate(field);
      case "UPPER_CAMEL_CASE" -> ((UpperCamelCaseStrategy)
              PropertyNamingStrategies.UPPER_CAMEL_CASE)
          .translate(field);
      case "KEBAB_CASE" -> ((KebabCaseStrategy) PropertyNamingStrategies.KEBAB_CASE)
          .translate(field);
      case "LOWER_CASE" -> ((LowerCaseStrategy) PropertyNamingStrategies.LOWER_CASE)
          .translate(field);
      default -> field;
    };
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(
      NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    status = HttpStatus.NOT_FOUND;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
      AsyncRequestTimeoutException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    status = HttpStatus.INTERNAL_SERVER_ERROR;
    Problem problem =
        Problem.builder().title(getReasonPhrase(status)).status(status.value()).build();
    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
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
