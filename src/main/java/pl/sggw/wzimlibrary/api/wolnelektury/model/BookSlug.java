package pl.sggw.wzimlibrary.api.wolnelektury.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BookSlug {
    @JsonProperty("slug")
    private String slug;
}
