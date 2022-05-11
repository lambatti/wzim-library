
package pl.sggw.wzimlibrary.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Book {
    @JsonProperty("title")
    private String title;
    @JsonProperty("language")
    private String language;
    @JsonProperty("epochs")
    private List<String> epochs = null;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("kinds")
    private List<String> kinds = null;
    @JsonProperty("authors")
    private List<String> authors = null;
    @JsonProperty("translators")
    private List<String> translators = null;
    @JsonProperty("txt")
    private String txt;
    @JsonProperty("cover")
    private String cover;
}
