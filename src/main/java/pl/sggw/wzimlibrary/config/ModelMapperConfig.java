package pl.sggw.wzimlibrary.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.BookBorrowProlongationRequest;
import pl.sggw.wzimlibrary.model.BookBorrowRequest;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowProlongationRequestDto;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowRequestDto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<BookBorrow, BookBorrowDto> bookBorrowTypeMap = modelMapper.createTypeMap(BookBorrow.class, BookBorrowDto.class);
        bookBorrowTypeMap.addMapping(src -> src.getUser().getId(), BookBorrowDto::setUserId);

        TypeMap<BookBorrowRequest, BookBorrowRequestDto> requestTypeMap
                = modelMapper.createTypeMap(BookBorrowRequest.class, BookBorrowRequestDto.class);
        requestTypeMap.addMapping(src -> src.getUser().getId(), BookBorrowRequestDto::setUserId);

        TypeMap<BookBorrowProlongationRequest, BookBorrowProlongationRequestDto> prolongationTypeMap
                = modelMapper.createTypeMap(BookBorrowProlongationRequest.class, BookBorrowProlongationRequestDto.class);
        prolongationTypeMap.addMapping(src -> src.getUser().getId(), BookBorrowProlongationRequestDto::setUserId);

        return modelMapper;
    }
}
