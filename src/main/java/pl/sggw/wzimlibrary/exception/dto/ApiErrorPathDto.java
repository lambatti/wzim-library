package pl.sggw.wzimlibrary.exception.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ApiErrorPathDto extends ApiErrorDto {

    private final String path;

}
