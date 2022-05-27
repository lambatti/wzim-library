package pl.sggw.wzimlibrary.model.dto.user;

import lombok.Data;

@Data
public class UserBorrowStatisticsDto {
    private int borrowedBooks;
    private int readBooks;
}
