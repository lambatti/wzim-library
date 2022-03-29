package pl.sggw.wzimlibrary.controller.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JsonMappingException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sggw.wzimlibrary.exception.UserAlreadyExistsException;
import pl.sggw.wzimlibrary.exception.dto.ApiError;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex,
                                                                          HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.METHOD_NOT_ALLOWED;

        String message = ex.getMessage();

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, message,
                httpServletRequest));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex,
                                                           HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        String message = "Bad credentials.";

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, message,
                httpServletRequest));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                   HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<String> errors = createErrorList(ex);

        String message = "Validation failed due to errors: " + createStringFromErrorList(errors);

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, message,
                httpServletRequest));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtToken(ExpiredJwtException ex, HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        String message = "JWT token for the mail: " + ex.getClaims().getSubject() + " has expired.";

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, message,
                httpServletRequest));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex,
                                                              HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.CONFLICT;

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, ex.getMessage(),
                httpServletRequest));
    }

    @ExceptionHandler({JsonProcessingException.class, JsonMappingException.class,
            JsonEOFException.class, JsonParseException.class, IOException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleJsonExceptions(HttpServletRequest httpServletRequest) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        String message = "There has been an error while processing the JSON. Check the JSON format.";

        return ResponseEntity.status(httpStatus).body(createApiError(httpStatus, message,
                httpServletRequest));
    }


    private ApiError createApiError(HttpStatus httpStatus, String message, HttpServletRequest request) {
        return new ApiError(new Timestamp(System.currentTimeMillis()).toString(), httpStatus.value(),
                httpStatus.name(), message, request.getRequestURI());
    }

    private List<String> createErrorList(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return errors;
    }

    private String createStringFromErrorList(List<String> errors) {

        StringBuilder message = new StringBuilder();

        for (String error : errors) {
            message.append(error).append(", ");
        }

        message.delete(message.length() - 2, message.length());

        return message.toString();
    }

}
