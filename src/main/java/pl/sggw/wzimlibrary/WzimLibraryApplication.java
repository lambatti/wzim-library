package pl.sggw.wzimlibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class WzimLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(WzimLibraryApplication.class, args);
    }

}
