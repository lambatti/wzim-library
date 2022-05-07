package pl.sggw.wzimlibrary.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.User;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowProlongationRequestDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowRequestDto;
import pl.sggw.wzimlibrary.model.dto.user.UserPanelSummaryDto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(BookBorrow.class, BookBorrowDto.class)
                .addMapping(src -> src.getUser().getId(), BookBorrowDto::setUserId);

        modelMapper.createTypeMap(BookBorrowRequest.class, BookBorrowRequestDto.class)
                .addMapping(src -> src.getUser().getId(), BookBorrowRequestDto::setUserId);

        modelMapper.createTypeMap(BookBorrowProlongationRequest.class, BookBorrowProlongationRequestDto.class)
                .addMapping(src -> src.getUser().getId(), BookBorrowProlongationRequestDto::setUserId);

        modelMapper.createTypeMap(User.class, UserPanelSummaryDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getBorrowStatistics().getBorrowedBooks(), UserPanelSummaryDto::setBorrowedBooks);
                    mapper.map(src -> src.getBorrowStatistics().getReadBooks(), UserPanelSummaryDto::setReadBooks);
                });

        return modelMapper;
    }
}
