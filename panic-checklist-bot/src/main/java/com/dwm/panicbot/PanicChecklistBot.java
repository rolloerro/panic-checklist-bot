package com.dwm.panicbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PanicChecklistBot extends TelegramLongPollingBot {

    // 🧠 вставь сюда свой токен
    private static final String TOKEN = "ТВОЙ_ТОКЕН_СЮДА";
    private static final String USERNAME = "Cheklistpanic_bot";

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();

            String reply;
            switch (text.toLowerCase()) {
                case "/start":
                    reply = "Привет 👋! Я бот для помощи при панической атаке.\n" +
                            "Напиши /help, чтобы узнать, что я умею.";
                    break;
                case "/help":
                    reply = "Я могу:\n" +
                            "🧩 Дать чек-лист действий при панической атаке\n" +
                            "💡 Рассказать, что делать в момент приступа\n" +
                            "🏃‍♂️ Посоветовать, как восстанавливаться между приступами";
                    break;
                case "/checklist":
                    reply = "🧘 Чек-лист:\n" +
                            "1️⃣ Сосредоточься на дыхании (вдох — 4, выдох — 6)\n" +
                            "2️⃣ Назови 5 предметов, которые видишь\n" +
                            "3️⃣ Почувствуй опору под ногами\n" +
                            "4️⃣ Напомни себе: это безопасно и пройдёт 🙌";
                    break;
                default:
                    reply = "Не совсем понял 😅 Напиши /help или /checklist.";
            }

            SendMessage message = new SendMessage(chatId, reply);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
