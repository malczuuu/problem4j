package pl.malczuuu.problem4j.spring.web;

import java.net.URI;
import java.util.ArrayList;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import pl.malczuuu.problem4j.core.Problem;
import pl.malczuuu.problem4j.core.ProblemBuilder;
import pl.malczuuu.problem4j.core.ProblemException;

public abstract class AbstractProblemSupplier implements ProblemSupplier {

  @Override
  public ProblemBuilder from(ProblemException ex) {
    return Problem.builder(ex.getProblem());
  }

  @Override
  public ProblemBuilder from(HttpRequestMethodNotSupportedException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Method not allowed")
        .status(405)
        .detail("Method " + ex.getMethod() + " is not supported")
        .extension("supported", ex.getSupportedMethods());
  }

  @Override
  public ProblemBuilder from(HttpMediaTypeNotSupportedException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Unsupported media type")
        .status(415)
        .detail("Request with media type " + ex.getContentType() + " cannot be processed")
        .extension("supported", new ArrayList<>(ex.getSupportedMediaTypes()));
  }

  @Override
  public ProblemBuilder from(HttpMediaTypeNotAcceptableException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Not acceptable")
        .status(406)
        .detail(
            "Cannot generate response that is acceptable by the client, check out your Accept header")
        .extension("supported", new ArrayList<>(ex.getSupportedMediaTypes()));
  }

  @Override
  public ProblemBuilder from(MissingPathVariableException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail("Request without path variable " + ex.getVariableName() + " cannot be processed")
        .extension("variable", ex.getVariableName());
  }

  @Override
  public ProblemBuilder from(MissingServletRequestParameterException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail(
            "Request without param "
                + ex.getParameterName()
                + " of type "
                + ex.getParameterType()
                + " cannot be processed")
        .extension("param", ex.getParameterName())
        .extension("type", ex.getParameterType().toLowerCase());
  }

  @Override
  public ProblemBuilder from(ServletRequestBindingException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail(ex.getMessage());
  }

  @Override
  public ProblemBuilder from(ConversionNotSupportedException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Internal server error")
        .status(500)
        .detail("Failed to return a response for the client");
  }

  @Override
  public ProblemBuilder from(TypeMismatchException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail(
            "Property " + ex.getPropertyName() + " should be of " + ex.getRequiredType() + " type");
  }

  @Override
  public ProblemBuilder from(HttpMessageNotReadableException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail("Request is not readable");
  }

  @Override
  public ProblemBuilder from(HttpMessageNotWritableException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Internal server error")
        .status(500)
        .detail("Failed to return a response for the client");
  }

  @Override
  public ProblemBuilder from(MethodArgumentNotValidException ex) {
    return from(ex.getBindingResult());
  }

  private ProblemBuilder from(BindingResult bindingResult) {
    ArrayList<ValidationError> details = new ArrayList<>();
    ArrayList<String> fields = new ArrayList<>();
    bindingResult
        .getFieldErrors()
        .forEach(
            f -> {
              details.add(new ValidationError(f.getField(), f.getDefaultMessage()));
              fields.add(f.getField());
            });
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Unprocessable entity")
        .status(422)
        .detail(
            "Validation failed for fields "
                + fields.stream().reduce((s1, s2) -> s1 + ", " + s2).orElse(""))
        .extension("errors", details);
  }

  @Override
  public ProblemBuilder from(MissingServletRequestPartException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Bad request")
        .status(400)
        .detail("Request without part " + ex.getRequestPartName() + " cannot be processed")
        .extension("part", ex.getRequestPartName());
  }

  @Override
  public ProblemBuilder from(BindException ex) {
    return from(ex.getBindingResult());
  }

  @Override
  public ProblemBuilder from(NoHandlerFoundException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Not found")
        .status(404)
        .detail("Handler not found")
        .instance(URI.create(ex.getRequestURL()));
  }

  @Override
  public ProblemBuilder from(AsyncRequestTimeoutException ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Internal server error")
        .status(500)
        .detail("Server timeout");
  }

  @Override
  public ProblemBuilder from(Exception ex) {
    return Problem.builder()
        .type(Problem.BLANK_TYPE)
        .title("Internal server error")
        .status(500)
        .detail("An unknown error occurred");
  }
}
