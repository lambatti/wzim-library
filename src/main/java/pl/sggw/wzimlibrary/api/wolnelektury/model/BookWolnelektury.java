
package pl.sggw.wzimlibrary.api.wolnelektury.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class BookWolnelektury {
    @JsonProperty("title")
    private String title;
    @JsonProperty("language")
    private String language;
    @JsonProperty("epochs")
    private List<NameProp> epochs = null;
    @JsonProperty("genres")
    private List<NameProp> genres = null;
    @JsonProperty("kinds")
    private List<NameProp> kinds = null;
    @JsonProperty("authors")
    private List<NameProp> authors = null;
    @JsonProperty("translators")
    private List<NameProp> translators = null;
    @JsonProperty("txt")
    private String txt;
    @JsonProperty("cover")
    private String cover;
}
