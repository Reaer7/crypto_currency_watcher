package com.gmail.brutskiart.watcher.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.gmail.brutskiart.watcher.controller.ControllerConstants.MESSAGE_KEY;
import static com.gmail.brutskiart.watcher.controller.ControllerConstants.STATUS_KEY;
import static com.gmail.brutskiart.watcher.exception.ExceptionMessageConstants.JSON_PARSE_ERROR;
import static com.gmail.brutskiart.watcher.exception.ExceptionMessageConstants.WRONG_CRYPTO_SYMBOL_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SymbolDeniedException.class})
    protected ResponseEntity<Object> handleSymbolDeniedException(
            SymbolDeniedException ex,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE_KEY, WRONG_CRYPTO_SYMBOL_ERROR);
        body.put(STATUS_KEY, HttpStatus.BAD_REQUEST.value());
        log.error(ex.getMessage());
        return handleExceptionInternal(
                ex,
                body,
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
            ConversionNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
            MissingServletRequestPartException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
            TypeMismatchException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
            HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
            AsyncRequestTimeoutException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest webRequest
    ) {
        Map<String, Object> body = getBodyWithErrorAndCode(ex, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE_KEY, JSON_PARSE_ERROR);
        body.put(STATUS_KEY, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, Object> body = new LinkedHashMap<>();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put(MESSAGE_KEY, errors);
        body.put(STATUS_KEY, status);
        log.error(ex.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    private Map<String, Object> getBodyWithErrorAndCode(Exception ex, HttpStatusCode status) {
        Map<String, Object> body = new LinkedHashMap<>();
        String error = ex.getMessage();
        body.put(MESSAGE_KEY, error);
        body.put(STATUS_KEY, status);
        return body;
    }
}