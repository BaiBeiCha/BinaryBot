package org.baibei.binarybot.Objects;

import lombok.extern.slf4j.Slf4j;
import org.baibei.binarybot.Data.ConvertInfo;
import org.baibei.binarybot.Repositories.ConvertInfoRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    public Bot(ConvertInfoRepository convertInfoRepository) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        String message = buildMessage(update);

        ConvertInfo info = new ConvertInfo();
        info.setChatId(update.getMessage().getChatId().toString());
        info.setBefore(update.getMessage().getText());
        info.setAfter(message);

        sendMsg(update.getMessage().getChatId().toString(), message, info);
    }

    private String buildMessage(Update update) {
        try {
            String mes = update.getMessage().getText();
            Command command = CommandHandler.handle(mes);
            return getString(command);
        } catch (Exception e) {
            return "❌";
        }
    }

    public static String buildMessage(Command command) {
        try {
            return getString(command);
        } catch (Exception e) {
            return "❌";
        }
    }

    private static String getString(Command command) {
        if (command.getCommand() != null) {
            command.setArguments();
        } else {
            command.setCommand("Invalid");
        }
        System.out.println(command.toString());

        System.out.println("First 2 args: [" + command.getFirstTwoArguments()[0] +
                ";" + command.getFirstTwoArguments()[1] + "]");

        return switch (command.getCommand()) {
            case "/help", "/HELP", "/Help", "/?" -> "/bin - Convert to binary\n/dec - Convert to Decimal";
            case "/bin", "/BIN", "/Bin" -> Convertor.convertStringToBinary(command.getArguments());
            case "/dec", "/DEC", "/Dec" -> Convertor.convertStringToDecimal(command.getArguments());
            case "/to", "/TO", "/To" ->
                    Convertor.convertStringTo(command.getOtherArguments(),
                            Integer.parseInt(command.getFirstTwoArguments()[0]),
                            Integer.parseInt(command.getFirstTwoArguments()[1]));
            default -> "❌";
        };
    }

    public synchronized void sendMsg(String chatId, String message, ConvertInfo info) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        return "DigitalToBinaryBot";
    }


    @Override
    public String getBotToken() {
        return "7768358124:AAGdpnO09ujVfZqHqJtUU1Rt3HmYJdhYfww";
    }
}
