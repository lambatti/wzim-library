package pl.sggw.wzimlibrary.service.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowProlongationRequestRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRequestRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookBorrowCacheService {

    private final SqlBookBorrowRepository bookBorrowRepository;
    private final SqlBookBorrowRequestRepository bookBorrowRequestRepository;
    private final SqlBookBorrowProlongationRequestRepository bookBorrowProlongationRequestRepository;

}
