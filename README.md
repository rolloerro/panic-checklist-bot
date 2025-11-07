# 🧠 Panic Checklist Bot v1.0 (DWM Small Assembly)

**Panic Checklist Bot** — Telegram-бот, созданный в рамках экосистемы **DWM**.  
Он помогает пользователю при панических атаках, предоставляя пошаговые инструкции и технику самопомощи в режиме диалога.

---

## ⚙️ Технологии
- Java 17  
- Maven  
- TelegramBots API (org.telegram.telegrambots)  
- Конфигурация через `config.properties`

---

## 📦 Структура проекта
panic-checklist-bot/
├── src/main/java/com/dwm/panicbot/
│ ├── App.java
│ ├── BotConfig.java
│ └── PanicChecklistBot.java
├── pom.xml
├── config.properties.example
└── target/

yaml
Копировать код

---

## 🔐 Конфигурация

Создай файл `config.properties` (на основе `config.properties.example`) и добавь туда свои данные:

```properties
BOT_TOKEN=YOUR_TELEGRAM_BOT_TOKEN_HERE
BOT_USERNAME=YOUR_BOT_USERNAME_HERE
⚠️ Никогда не коммить реальные токены в репозиторий.

🚀 Запуск
bash
Копировать код
mvn clean package
java -jar target/panic-checklist-bot-1.0-SNAPSHOT-jar-with-dependencies.jar
🧩 Экосистема DWM
Проект входит в серию DWM Small Assemblies — компактных и функциональных сборок для Telegram и медицинских решений.

© 2025 Digital WM Core
Разработано при участии [TARS] и [Rolloerro].

yaml
Копировать код
