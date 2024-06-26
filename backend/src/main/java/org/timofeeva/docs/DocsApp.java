package org.timofeeva.docs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class DocsApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DocsApp.class);
        ConfigurableApplicationContext context = app.run(args);
        Environment env = context.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running!.\n\tAccess URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "Swagger: \thttp://localhost:{}/swagger-ui.html\n\t" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.port")
        );
    }
}
