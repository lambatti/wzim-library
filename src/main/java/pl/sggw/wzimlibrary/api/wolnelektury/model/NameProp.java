
package pl.sggw.wzimlibrary.api.wolnelektury.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class NameProp {
    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NameProp nameProp = (NameProp) o;
        return Objects.equals(name, nameProp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
