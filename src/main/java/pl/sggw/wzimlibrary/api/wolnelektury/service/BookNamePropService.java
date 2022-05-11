package pl.sggw.wzimlibrary.api.wolnelektury.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sggw.wzimlibrary.api.wolnelektury.model.NameProp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookNamePropService {
    private final RestTemplate restTemplate;

    public List<NameProp> getNameProp(String prop) {
        return Arrays.stream(Objects.requireNonNull
                        (restTemplate.getForObject("https://wolnelektury.pl/api/" + prop + "/", NameProp[].class)))
                .collect(Collectors.toList());
    }
}
