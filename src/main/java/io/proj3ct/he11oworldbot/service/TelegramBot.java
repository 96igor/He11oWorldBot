package io.proj3ct.he11oworldbot.service;

import io.proj3ct.he11oworldbot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    final BotConfig config;
    static final String START = "MAIN MENU He11o World Bot *_* \n\n/button1\n\n/button2\n\n/next";
    static final String BUTTON1 = "You pressed button1";
    static final String BUTTON2 = "You pressed button2";
    static final String SECOND_MENU = "He11o World bot menu # 2 \n\n/button1\n\n/button2\n\n/back";

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                        sendMessage (chatId, START);
                    break;
                case "/button1":
                    sendMessage (chatId, BUTTON1);
                    break;
                case "/button2":
                    sendMessage (chatId, BUTTON2);
                    break;
                case "/next":
                        sendMessage (chatId, SECOND_MENU);
                    break;
                case "/back":
                    sendMessage (chatId, START);
                    break;
                default:
                        sendMessage(chatId, "Sorry, command was not recognized");
            }
        }
    }

    private void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try{
            execute(message);
        }
        catch (TelegramApiException e){

        }
    }
}