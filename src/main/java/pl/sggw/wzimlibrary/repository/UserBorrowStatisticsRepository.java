package pl.sggw.wzimlibrary.repository;

import pl.sggw.wzimlibrary.model.UserBorrowStatistics;

public interface UserBorrowStatisticsRepository {

    <S extends UserBorrowStatistics> S save(S s);

}
