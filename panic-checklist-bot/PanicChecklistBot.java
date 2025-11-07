package com.dwm.panicbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;

public class PanicChecklistBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            sendMainMenu(chatId);
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String data = update.getCallbackQuery().getData();

            switch (data) {
                case "diagnostics" -> sendText(chatId, PanicContent.DIAGNOSTICS);
                case "lifestyle" -> sendText(chatId, PanicContent.LIFESTYLE);
                case "self_help" -> sendText(chatId, PanicContent.SELF_HELP);
                case "help_others" -> sendText(chatId, PanicContent.HELP_OTHERS);
                default -> sendMainMenu(chatId);
            }
        }
    }

    private void sendMainMenu(String chatId) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = List.of(
                List.of(InlineKeyboardButton.builder().text("🩺 Диагностика").callbackData("diagnostics").build()),
                List.of(InlineKeyboardButton.builder().text("🌿 Образ жизни").callbackData("lifestyle").build()),
                List.of(InlineKeyboardButton.builder().text("💨 Помоги себе").callbackData("self_help").build()),
                List.of(InlineKeyboardButton.builder().text("❤️ Помоги другому").callbackData("help_others").build())
        );
        markup.setKeyboard(rows);

        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text("🧘 *Чек-лист при панической атаке*\n\nВыбери нужный раздел👇")
                .parseMode("Markdown")
                .replyMarkup(markup)
                .build();

        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendText(String chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode("Markdown")
                .build();
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
