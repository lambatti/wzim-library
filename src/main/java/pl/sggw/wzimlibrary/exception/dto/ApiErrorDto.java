package pl.sggw.wzimlibrary.exception.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ApiErrorDto {

    protected final String timestamp;
    protected final Integer status;
    protected final String error;
    protected final String message;

}
