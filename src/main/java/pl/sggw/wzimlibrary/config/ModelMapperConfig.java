package pl.sggw.wzimlibrary.config;

import org.modelmapper.ModelMapper;
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

        modelMapper.createTypeMap(BookBorrow.class, BookBorrowDto.class)
                .addMapping(src -> src.getUser().getId(), BookBorrowDto::setUserId);

        modelMapper.createTypeMap(BookBorrowRequest.class, BookBorrowRequestDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getUser().getId(), BookBorrowRequestDto::setUserId);
                    mapper.map(src -> src.getUser().getFirstName(), BookBorrowRequestDto::setFirstName);
                    mapper.map(src -> src.getUser().getLastName(), BookBorrowRequestDto::setLastName);
                });

        modelMapper.createTypeMap(BookBorrowProlongationRequest.class, BookBorrowProlongationRequestDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getUser().getId(), BookBorrowProlongationRequestDto::setUserId);
                    mapper.map(src -> src.getUser().getFirstName(), BookBorrowProlongationRequestDto::setFirstName);
                    mapper.map(src -> src.getUser().getLastName(), BookBorrowProlongationRequestDto::setLastName);
                });

        return modelMapper;
    }
}
