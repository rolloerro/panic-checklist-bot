package com.dwm.panicbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class App extends TelegramLongPollingBot {

    public static void main(String[] args) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new App());
        System.out.println("🚀 Digital World Medicine | Panic Checklist Bot активен!");
    }

    @Override
    public String getBotUsername() {
        return BotConfig.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (text) {
                case "/start" -> sendMainMenu(chatId);
                case "🧠 Чек-лист" -> sendChecklist(chatId);
                case "💨 Дыхание" -> sendBreathing(chatId);
                case "🪞 Заземление" -> sendGrounding(chatId);
                case "ℹ️ О проекте" -> sendAbout(chatId);
                default -> sendMessage(chatId, "Выбери пункт меню 👇");
            }
        }
    }

    private void sendMainMenu(long chatId) {
        SendMessage message = new SendMessage(String.valueOf(chatId),
                "Привет 🌿\n\n" +
                "Я бот-помощник в момент панической атаки.\n" +
                "Выбери действие, которое поможет тебе сейчас:");

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        keyboard.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("🧠 Чек-лист"));
        row1.add(new KeyboardButton("💨 Дыхание"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("🪞 Заземление"));
        row2.add(new KeyboardButton("ℹ️ О проекте"));

        rows.add(row1);
        rows.add(row2);
        keyboard.setKeyboard(rows);
        message.setReplyMarkup(keyboard);

        send(message);
    }

    private void sendChecklist(long chatId) {
        String checklist = """
                🧠 *Чек-лист при панической атаке*:

                1️⃣ Признай: это паническая атака, а не опасность.  
                2️⃣ Не борись с ощущением — просто наблюдай.  
                3️⃣ Сосредоточь внимание на дыхании.  
                4️⃣ Сделай 5 циклов: вдох 4 сек – задержка 2 сек – выдох 6 сек.  
                5️⃣ Назови 5 предметов, которые видишь вокруг.  
                6️⃣ Скажи себе: *“Я в безопасности. Это пройдет.”* 💙
                """;
        sendMessage(chatId, checklist);
    }

    private void sendBreathing(long chatId) {
        String breathing = """
                💨 *Техника дыхания 4–2–6*:

                ▪️ Вдохни через нос — 4 секунды  
                ▪️ Задержи дыхание — 2 секунды  
                ▪️ Выдохни через рот — 6 секунд  

                Повтори 5–7 циклов.
                Почувствуй, как напряжение снижается 🕊️
                """;
        sendMessage(chatId, breathing);
    }

    private void sendGrounding(long chatId) {
        String grounding = """
                🪞 *Техника заземления (5-4-3-2-1)*

                Посмотри вокруг:
                🔹 5 вещей, которые ты видишь  
                🔹 4 вещи, которые можешь потрогать  
                🔹 3 звука, которые слышишь  
                🔹 2 запаха, которые ощущаешь  
                🔹 1 вещь, за которую ты благодарен 🙏
                """;
        sendMessage(chatId, grounding);
    }

    private void sendAbout(long chatId) {
        String about = """
                🌍 *Digital World Medicine Project*  
                Разработано в рамках инициативы DWM 🧬  
                Цель — создать серию офлайн и онлайн помощников
                для экстренной поддержки при панических атаках, стрессе и тревоге.

                Автор: команда DWM (TARS & медбрат 👨‍🚀)
                Репозиторий: github.com/rolloerro
                """;
        sendMessage(chatId, about);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage(String.valueOf(chatId), text);
        message.enableMarkdown(true);
        send(message);
    }

    private void send(SendMessage message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
