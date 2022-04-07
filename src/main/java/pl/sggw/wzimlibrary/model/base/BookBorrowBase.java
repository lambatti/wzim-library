package pl.sggw.wzimlibrary.model.base;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@RequiredArgsConstructor
public abstract class BookBorrowBase {

    @EmbeddedId
    @NonNull
    protected final BookBorrowId id;

    public User getUser() {
        return id.getUser();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }

    public String getBookSlug() {
        return id.getBookSlug();
    }

    public void setBookSlug(String bookSlug) {
        this.id.setBookSlug(bookSlug);
    }

}
