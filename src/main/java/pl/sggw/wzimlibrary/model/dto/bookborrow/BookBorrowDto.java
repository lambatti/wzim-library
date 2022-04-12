package pl.sggw.wzimlibrary.model.dto.bookborrow;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@RequiredArgsConstructor
@Getter
public class BookBorrowDto {

    @NotEmpty
    @Email
    private final String email;

    @NotEmpty
    private final String bookSlug;

}
