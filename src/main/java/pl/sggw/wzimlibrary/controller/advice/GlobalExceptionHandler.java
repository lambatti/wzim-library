package pl.sggw.wzimlibrary.controller.advice;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sggw.wzimlibrary.exception.dto.ApiErrorDto;
import pl.sggw.wzimlibrary.exception.dto.ApiErrorWithPathDto;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        StringBuilder message = new StringBuilder("Validation failed due to errors: ");

        for (String error : errors) {
            message.append(error).append(", ");
        }

        message.delete(message.length() - 2, message.length());

        return ResponseEntity.status(httpStatus).body(createApiErrorWithPath(httpStatus, message.toString(),
                httpServletRequest.getRequestURI()));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtToken(ExpiredJwtException ex) {

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus,
                "JWT token for the mail: " + ex.getClaims().getSubject() + " has expired."));
    }

    private ApiErrorDto createApiError(HttpStatus httpStatus, String message) {
        return ApiErrorDto.builder()
                .timestamp(new Timestamp(System.currentTimeMillis()).toString())
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(message)
                .build();
    }

    private ApiErrorWithPathDto createApiErrorWithPath(HttpStatus httpStatus, String message, String path) {
        return ApiErrorWithPathDto.builder()
                .timestamp(new Timestamp(System.currentTimeMillis()).toString())
                .status(httpStatus.value())
                .error(httpStatus.name())
                .message(message)
                .path(path)
                .build();
    }

}
