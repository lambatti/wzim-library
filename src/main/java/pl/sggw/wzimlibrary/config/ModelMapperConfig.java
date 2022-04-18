package pl.sggw.wzimlibrary.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sggw.wzimlibrary.model.BookBorrow;
import pl.sggw.wzimlibrary.model.dto.bookborrow.BookBorrowDto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<BookBorrow, BookBorrowDto> bookBorrowTypeMap = modelMapper.createTypeMap(BookBorrow.class, BookBorrowDto.class);
        bookBorrowTypeMap.addMapping(src -> src.getUser().getId(), BookBorrowDto::setUserId);

        return modelMapper;
    }
}
