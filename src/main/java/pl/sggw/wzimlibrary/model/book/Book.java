
package pl.sggw.wzimlibrary.model.book;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title"
//        "language",
//        "epochs",
//        "genres",
//        "kinds",
//        "authors",
//        "translators",
//        "txt",
//        "cover"
})
public class Book {
    @JsonProperty("title")
    private String title;
//    @JsonProperty("language")
//    private String language;
//    @JsonProperty("epochs")
//    private List<Epoch> epochs = null;
//    @JsonProperty("genres")
//    private List<Genre> genres = null;
//    @JsonProperty("kinds")
//    private List<Kind> kinds = null;
//    @JsonProperty("authors")
//    private List<Author> authors = null;
//    @JsonProperty("translators")
//    private List<Translator> translators = null;
//    @JsonProperty("txt")
//    private String txt;
//    @JsonProperty("cover")
//    private String cover;

//    @JsonProperty("txt")
//    public String getTxt() {
//        return txt;
//    }
//
//    @JsonProperty("txt")
//    public void setTxt(String txt) {
//        this.txt = txt;
//    }
//
//    @JsonProperty("cover")
//    public String getCover() {
//        return cover;
//    }
//
//    @JsonProperty("cover")
//    public void setCover(String cover) {
//        this.cover = cover;
//    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

//    @JsonProperty("language")
//    public String getLanguage() {
//        return language;
//    }
//
//    @JsonProperty("language")
//    public void setLanguage(String language) {
//        this.language = language;
//    }
//
//    @JsonProperty("epochs")
//    public List<Epoch> getEpochs() {
//        return epochs;
//    }
//
//    @JsonProperty("epochs")
//    public void setEpochs(List<Epoch> epochs) {
//        this.epochs = epochs;
//    }
//
//    @JsonProperty("genres")
//    public List<Genre> getGenres() {
//        return genres;
//    }
//
//    @JsonProperty("genres")
//    public void setGenres(List<Genre> genres) {
//        this.genres = genres;
//    }
//
//    @JsonProperty("kinds")
//    public List<Kind> getKinds() {
//        return kinds;
//    }
//
//    @JsonProperty("kinds")
//    public void setKinds(List<Kind> kinds) {
//        this.kinds = kinds;
//    }
//
//    @JsonProperty("authors")
//    public List<Author> getAuthors() {
//        return authors;
//    }
//
//    @JsonProperty("authors")
//    public void setAuthors(List<Author> authors) {
//        this.authors = authors;
//    }
//
//    @JsonProperty("translators")
//    public List<Translator> getTranslators() {
//        return translators;
//    }
//
//    @JsonProperty("translators")
//    public void setTranslators(List<Translator> translators) {
//        this.translators = translators;
//    }

}
