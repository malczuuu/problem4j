package io.github.malczuuu.problem4j.spring.web;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
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
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import io.github.malczuuu.problem4j.core.ProblemBuilder;
import io.github.malczuuu.problem4j.core.ProblemException;

public interface ProblemSupplier {

  ProblemBuilder from(ProblemException ex);

  ProblemBuilder from(HttpRequestMethodNotSupportedException ex);

  ProblemBuilder from(HttpMediaTypeNotSupportedException ex);

  ProblemBuilder from(HttpMediaTypeNotAcceptableException ex);

  ProblemBuilder from(MissingPathVariableException ex);

  ProblemBuilder from(MissingServletRequestParameterException ex);

  ProblemBuilder from(ServletRequestBindingException ex);

  ProblemBuilder from(ConversionNotSupportedException ex);

  ProblemBuilder from(TypeMismatchException ex);

  ProblemBuilder from(HttpMessageNotReadableException ex);

  ProblemBuilder from(HttpMessageNotWritableException ex);

  ProblemBuilder from(MethodArgumentNotValidException ex);

  ProblemBuilder from(MissingServletRequestPartException ex);

  ProblemBuilder from(BindException ex);

  ProblemBuilder from(NoHandlerFoundException ex);

  ProblemBuilder from(AsyncRequestTimeoutException ex);

  ProblemBuilder from(Exception ex);
}
