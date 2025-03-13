package io.proj3ct.he11oworldbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("application.properties")
public class BotConfig {

    @Value("${Bot.name}")
    String botName;

    @Value("${Bot.token}")
    String token;

}
