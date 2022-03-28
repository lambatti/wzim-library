package pl.sggw.wzimlibrary.exception.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExpiredJwtExceptionErrorDto {
    private final String timestamp;
    private final Integer status;
    private final String error;
    private final String message;
}
