package pl.sggw.wzimlibrary.model.base;

import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.id.BookBorrowId;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BookBorrowBase {

    @EmbeddedId
    protected BookBorrowId id;

    protected User getUser() {
        return id.getUser();
    }

    protected void setUser(User user) {
        this.id.setUser(user);
    }

    protected String getBookSlug() {
        return id.getBookSlug();
    }

    protected void setBookSlug(String bookSlug) {
        this.id.setBookSlug(bookSlug);
    }
}
