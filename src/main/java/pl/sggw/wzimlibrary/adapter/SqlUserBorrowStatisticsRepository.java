package pl.sggw.wzimlibrary.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sggw.wzimlibrary.model.UserBorrowStatistics;
import pl.sggw.wzimlibrary.repository.UserBorrowStatisticsRepository;

public interface SqlUserBorrowStatisticsRepository extends UserBorrowStatisticsRepository,
        JpaRepository<UserBorrowStatistics, Integer> {

    @Override
    <S extends UserBorrowStatistics> S save(S s);

}
