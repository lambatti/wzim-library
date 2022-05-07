package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowProlongationRequestRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRepository;
import pl.sggw.wzimlibrary.adapter.SqlBookBorrowRequestRepository;
import pl.sggw.wzimlibrary.exception.BookBorrowConflictException;
import pl.sggw.wzimlibrary.exception.BookBorrowNotFoundException;
import pl.sggw.wzimlibrary.exception.UserNotFoundException;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.base.BookBorrowBase;
import pl.sggw.wzimlibrary.model.constant.BookBorrowConstant;
import pl.sggw.wzimlibrary.model.constant.SchedulingConstant;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowActionDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowProlongationRequestDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowRequestDto;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookBorrowService {

    private final SqlBookBorrowRepository bookBorrowRepository;
    private final SqlBookBorrowRequestRepository bookBorrowRequestRepository;
    private final SqlBookBorrowProlongationRequestRepository bookBorrowProlongationRequestRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Async
    public CompletableFuture<List<BookBorrow>> findAllBookBorrows() {
        return CompletableFuture.completedFuture(bookBorrowRepository.findAll());
    }

    @Async
    public CompletableFuture<List<BookBorrow>> findAllBookBorrowsByUserId(Integer userId) {
        return CompletableFuture.completedFuture(bookBorrowRepository.findAllByUser_Id(userId));
    }

    @Async
    public CompletableFuture<Optional<BookBorrow>> findBookBorrowByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRepository.findByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<List<BookBorrowRequest>> findAllRequests() {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.findAll());
    }

    @Async
    public CompletableFuture<List<BookBorrowRequest>> findAllRequestsByUserId(Integer userId) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.findAllByUser_Id(userId));
    }

    @Async
    public CompletableFuture<Optional<BookBorrowRequest>> findRequestByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.findByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<List<BookBorrowProlongationRequest>> findAllProlongationRequests() {
        return CompletableFuture.completedFuture(bookBorrowProlongationRequestRepository.findAll());
    }

    @Async
    public CompletableFuture<List<BookBorrowProlongationRequest>> findAllProlongationRequestsByUserId(Integer userId) {
        return CompletableFuture.completedFuture(bookBorrowProlongationRequestRepository.findAllByUser_Id(userId));
    }


    @Async
    public CompletableFuture<Optional<BookBorrowProlongationRequest>> findProlongationRequestByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowProlongationRequestRepository.findByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<BookBorrowRequest> save(BookBorrowRequest bookBorrowRequest) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.save(bookBorrowRequest));
    }

    @Async
    public CompletableFuture<BookBorrow> save(BookBorrow bookBorrow) {
        return CompletableFuture.completedFuture(bookBorrowRepository.save(bookBorrow));
    }

    @Async
    public CompletableFuture<Boolean> requestExistsByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowRequestRepository.existsByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<Boolean> prolongationRequestExistsByUserIdAndBookSlug(Integer userId, String bookSlug) {
        return CompletableFuture.completedFuture(bookBorrowProlongationRequestRepository.existsByUser_IdAndBookSlug(userId, bookSlug));
    }

    @Async
    public CompletableFuture<Integer> borrowDeleteFromUserByReturnDate(Integer userId, Date currentDate) {
        return CompletableFuture.completedFuture(bookBorrowRepository.deleteAllFromUserByReturnDate(userId, currentDate));
    }

    public List<BookBorrowDto> getAllBookBorrows() throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllBookBorrows().get(), BookBorrowDto.class);
    }

    public List<BookBorrowDto> getAllBookBorrowsByUserId(Integer userId) throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllBookBorrowsByUserId(userId).get(), BookBorrowDto.class);
    }


    public BookBorrowDto getBookBorrowByUserIdAndBookSlug(Integer userId, String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {

        // TODO: 07.05.2022 check if the book exists

        BookBorrow bookBorrow = findBookBorrowByUserIdAndBookSlug(userId, bookSlug).get()
                .orElseThrow(() -> new BookBorrowNotFoundException("User with the id: " + userId
                        + " has not borrowed the book: " + bookSlug));

        return convertEntityToDto(bookBorrow, BookBorrowDto.class);
    }


    public List<BookBorrowRequestDto> getAllRequests() throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllRequests().get(), BookBorrowRequestDto.class);
    }

    public List<BookBorrowRequestDto> getAllRequestsByUserId(Integer userId) throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllRequestsByUserId(userId).get(), BookBorrowRequestDto.class);
    }


    public BookBorrowRequestDto getRequestByUserIdAndBookSlug(Integer userId, String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {

        // TODO: 07.05.2022 check if the book exists

        BookBorrowRequest request = findRequestByUserIdAndBookSlug(userId, bookSlug).get()
                .orElseThrow(() -> new BookBorrowNotFoundException("User with the id: " + userId
                        + " has not sent a request for the book: " + bookSlug));

        return convertEntityToDto(request, BookBorrowRequestDto.class);
    }

    public List<BookBorrowProlongationRequestDto> getAllProlongationRequests() throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllProlongationRequests().get(), BookBorrowProlongationRequestDto.class);
    }

    public List<BookBorrowProlongationRequestDto> getAllProlongationRequestsByUserId(Integer userId) throws ExecutionException, InterruptedException {
        return convertBookBorrowListToDto(findAllProlongationRequestsByUserId(userId).get(), BookBorrowProlongationRequestDto.class);
    }

    public BookBorrowProlongationRequestDto getProlongationRequestByUserIdAndBookSlug(Integer userId, String bookSlug)
            throws ExecutionException, InterruptedException, BookBorrowNotFoundException {

        // TODO: 07.05.2022 check if the book exists

        BookBorrowProlongationRequest request = findProlongationRequestByUserIdAndBookSlug(userId, bookSlug).get()
                .orElseThrow(() -> new BookBorrowNotFoundException("User with the id: " + userId
                        + " has not sent a prolongation request for the book: " + bookSlug));

        return convertEntityToDto(request, BookBorrowProlongationRequestDto.class);

    }


    private <T, S extends BookBorrowBase> List<T> convertBookBorrowListToDto(List<S> bookBorrowList, Class<T> dtoClass) {
        return bookBorrowList.stream().map(bookBorrow -> convertEntityToDto(bookBorrow, dtoClass)).collect(Collectors.toList());
    }

    private <T, S extends BookBorrowBase> T convertEntityToDto(S bookBorrow, Class<T> dtoClass) {
        return modelMapper.map(bookBorrow, dtoClass);
    }

    @Transactional
    public BookBorrowRequest addBookBorrowRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        User user = getUserFromUserDetails(userDetails);

        if (requestExistsByUserIdAndBookSlug(user.getId(), bookSlug).get()) {
            throw new BookBorrowConflictException("User: " + user.getEmail()
                    + " already has sent the request for the book: " + bookSlug);
        }

        if (getBookBorrowByBookSlug(user, bookSlug).isPresent()) {
            throw new BookBorrowConflictException("User: " + user.getEmail()
                    + " has the book already: " + bookSlug);
        }

        BookBorrowRequest bookBorrowRequest = BookBorrowRequest.builder()
                .user(user).bookSlug(bookSlug).requestDate(BookBorrowConstant.CURRENT_DATE).build();

        userService.addBookBorrowRequestToUser(user, bookBorrowRequest).get();

        return bookBorrowRequest;

    }

    @Transactional
    public BookBorrowProlongationRequest addBookBorrowProlongationRequest(UserDetails userDetails, String bookSlug)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        User user = getUserFromUserDetails(userDetails);

        if (prolongationRequestExistsByUserIdAndBookSlug(user.getId(), bookSlug).get()) {
            throw new BookBorrowConflictException("User: " + user.getEmail() + " has already sent a prolongation " +
                    "request for the book: " + bookSlug);
        }

        BookBorrow bookBorrow = getBookBorrowByBookSlug(user, bookSlug)
                .orElseThrow(() -> new BookBorrowConflictException("User: " + user.getEmail()
                        + " has not borrowed the book: " + bookSlug));

        // TODO: 17.04.2022 check if the book exists

        BookBorrowProlongationRequest request = BookBorrowProlongationRequest.builder()
                .user(user).bookSlug(bookSlug).borrowDate(bookBorrow.getBorrowDate())
                .requestDate(BookBorrowConstant.CURRENT_DATE)
                .prolongationDate(bookBorrow.getReturnDate().plusDays(BookBorrowConstant.BOOK_BORROW_DAYS))
                .build();

        userService.addBookBorrowProlongationRequestToUser(user, request).get();

        return request;
    }

    public void rejectBookBorrowRequest(BookBorrowActionDto bookBorrowActionDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowActionDto);

        deleteBookBorrowRequest(request);

    }

    public void acceptBookBorrowRequest(BookBorrowActionDto bookBorrowActionDto)
            throws UserNotFoundException, BookBorrowConflictException, ExecutionException, InterruptedException {

        BookBorrowRequest request = getBookBorrowRequest(bookBorrowActionDto);

        User user = request.getUser();

        BookBorrow bookBorrow = BookBorrow.builder().user(user)
                .bookSlug(request.getBookSlug())
                .borrowDate(BookBorrowConstant.CURRENT_DATE)
                .returnDate(BookBorrowConstant.RETURN_DATE)
                .build();

        addBookBorrowToUser(user, request, bookBorrow);

    }

    public void rejectBookBorrowProlongationRequest(BookBorrowActionDto bookBorrowActionDto)
            throws ExecutionException, InterruptedException, UserNotFoundException, BookBorrowConflictException {

        BookBorrowProlongationRequest request = getBookBorrowProlongationRequest(bookBorrowActionDto);

        deleteBookBorrowProlongationRequest(request);

    }

    public void acceptBookBorrowProlongationRequest(BookBorrowActionDto bookBorrowActionDto)
            throws ExecutionException, InterruptedException, UserNotFoundException, BookBorrowConflictException {

        BookBorrowProlongationRequest request = getBookBorrowProlongationRequest(bookBorrowActionDto);

        deleteBookBorrowProlongationRequest(request);

        String email = bookBorrowActionDto.getEmail();
        String bookSlug = bookBorrowActionDto.getBookSlug();

        User user = userService.findByEmail(email).get()
                .orElseThrow(() -> new UserNotFoundException("User with the email: " + email + " not found."));

        BookBorrow bookBorrow = getBookBorrowByBookSlug(user, bookSlug)
                .orElseThrow(() -> new BookBorrowConflictException("There is no such book borrow with the user: "
                        + email + " and the book: " + bookSlug));

        updateBookBorrowReturnDate(bookBorrow);
    }

    @Transactional
    void addBookBorrowToUser(User user, BookBorrowRequest request, BookBorrow bookBorrow)
            throws ExecutionException, InterruptedException {
        userService.addBookBorrowToUser(user, request, bookBorrow).get();
    }

    @Transactional
    void updateBookBorrowReturnDate(BookBorrow bookBorrow) throws ExecutionException, InterruptedException {
        userService.updateBookBorrowReturnDate(bookBorrow).get();
    }


    @Transactional
    BookBorrowRequest getBookBorrowRequest(BookBorrowActionDto bookBorrowActionDto)
            throws UserNotFoundException, ExecutionException, InterruptedException, BookBorrowConflictException {

        String email = bookBorrowActionDto.getEmail();
        String bookSlug = bookBorrowActionDto.getBookSlug();

        User user = userService.findByEmail(email).get()
                .orElseThrow(() -> new UserNotFoundException("User with email: "
                        + bookBorrowActionDto.getEmail() + " not found"));

        // TODO: 08.04.2022 check if the book exists

        return findRequestByUserIdAndBookSlug(user.getId(), bookSlug).get()
                .orElseThrow(() -> new BookBorrowConflictException("There is no such request with the email: "
                        + email + " and the book: " + bookSlug));
    }

    @Transactional
    BookBorrowProlongationRequest getBookBorrowProlongationRequest(BookBorrowActionDto bookBorrowActionDto)
            throws ExecutionException, InterruptedException, UserNotFoundException, BookBorrowConflictException {

        String email = bookBorrowActionDto.getEmail();
        String bookSlug = bookBorrowActionDto.getBookSlug();

        User user = userService.findByEmail(email).get()
                .orElseThrow(() -> new UserNotFoundException("User with email: "
                        + bookBorrowActionDto.getEmail() + " not found"));

        // TODO: 08.04.2022 check if the book exists

        return findProlongationRequestByUserIdAndBookSlug(user.getId(), bookSlug).get()
                .orElseThrow(() -> new BookBorrowConflictException("There is no such prolongation request with the email: "
                        + email + " and the book: " + bookSlug));
    }

    void deleteBookBorrowRequest(BookBorrowRequest bookBorrowRequest) throws ExecutionException, InterruptedException {

        User user = bookBorrowRequest.getUser();

        userService.removeBookBorrowRequestFromUser(user, bookBorrowRequest).get();

    }

    void deleteBookBorrowProlongationRequest(BookBorrowProlongationRequest request)
            throws ExecutionException, InterruptedException {

        User user = request.getUser();
        userService.removeBookBorrowProlongationRequestFromUser(user, request).get();

    }

    private Optional<BookBorrow> getBookBorrowByBookSlug(User user, String bookSlug) {

        return user.getBookBorrows().stream().filter(bookBorrow -> bookBorrow.getBookSlug().equals(bookSlug)).findAny();
    }

    private User getUserFromUserDetails(UserDetails userDetails) throws UserNotFoundException {
        return userService.getUserFromUserDetails(userDetails);
    }

    @Scheduled(cron = SchedulingConstant.EVERY_DAY_AT_MIDNIGHT, zone = SchedulingConstant.TIMEZONE)
    @Transactional
    public void returnBooksFromAllUsers() throws ExecutionException, InterruptedException {

        List<User> allUsers = userService.findAll().get();

        Date currentDate = Date.valueOf(BookBorrowConstant.CURRENT_DATE);

        for (User user : allUsers) {
            int readBooks = borrowDeleteFromUserByReturnDate(user.getId(), currentDate).get();

            userService.updateReadBooksByUser(user, readBooks).get();
        }
    }
}
