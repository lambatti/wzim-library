package pl.sggw.wzimlibrary.controller.advice;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.sggw.wzimlibrary.exception.dto.ExpiredJwtExceptionErrorDto;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExpiredJwtExceptionErrorDto> handleExpiredJwtToken(ExpiredJwtException ex) {

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(httpStatus).body(
                new ExpiredJwtExceptionErrorDto(new Timestamp(System.currentTimeMillis()).toString(),
                        httpStatus.value(), httpStatus.name(),
                        "JWT token for the mail: " + ex.getClaims().getSubject() + " has expired."));

    }

}
