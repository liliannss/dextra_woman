package br.com.dextra.woman.exceptions;

import br.com.dextra.woman.models.dtos.ErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorDTO responseStatusException(ResponseStatusException ex) {
        return getErrorDTO(ex.getStatus(),
                Collections.singletonList(ex.getCause().getMessage()),
                httpServletRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return handleExceptionInternal(
                ex,
                getErrorDTO(status, Collections.singletonList(ex.getMessage()), httpServletRequest),
                headers, status,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> fieldErrorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return handleExceptionInternal(
                ex,
                getErrorDTO(status, fieldErrorList, httpServletRequest),
                headers,
                status,
                request);
    }

    private ErrorDTO getErrorDTO(
            HttpStatus httpStatus,
            List<String> message,
            HttpServletRequest httpServletRequest) {
        return ErrorDTO
                .builder()
                .status(httpStatus)
                .path(httpServletRequest.getRequestURL().toString())
                .method(httpServletRequest.getMethod())
                .message(message)
                .errorId(UUID.randomUUID().toString())
                .instantCreated(Timestamp.from(Instant.now()))
                .build();
    }

}