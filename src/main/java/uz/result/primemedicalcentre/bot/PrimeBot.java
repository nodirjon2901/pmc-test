package uz.result.primemedicalcentre.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.result.primemedicalcentre.domain.entity.Application;
import uz.result.primemedicalcentre.domain.entity.Button;
import uz.result.primemedicalcentre.domain.entity.Counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PrimeBot extends TelegramLongPollingBot {

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.groupChatId}")
    private String groupChatId;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    public void handleSendApplication(Application application) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(groupChatId);
        sendMessage.setParseMode("Markdown");
        sendMessage.setText(
                "*Новая заявка*\n\n" +
                        "\uD83D\uDC64 *ФИО*: " + application.getFullName() + "\n" +
                        "\uD83D\uDCDE *Номер телефона*: " + application.getPhoneNum() + "\n" +
                        "\uD83D\uDCAC *Услуги*: " + application.getService() + "\n" +
                        "\uD83D\uDCAC*Комментарий*: "+application.getComment()+"\n"
        );
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendCounter(List<Counter> counters, Long totalApplications) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(groupChatId);
        StringBuilder textBuilder = new StringBuilder();

        textBuilder.append("<b>Еженедельный отчет \uD83D\uDCCB</b>\n\n");

        if ((counters == null || counters.isEmpty() && totalApplications == 0)) {
            textBuilder.append("<b>Поступившие заявки:</b> 0\n");
        } else {
            Map<Button, Long> buttonCountMap = new HashMap<>();
            long totalCalls = 0;
            long totalAppointments = 0;

            for (Counter counter : counters) {
                Button button = counter.getSection();
                long countCall = counter.getCountCall() != null ? counter.getCountCall() : 0;

                if (button == Button.MAKE_AN_APPOINTMENT || button == Button.SEND_APPLICATION) {
                    totalAppointments += countCall;
                } else {
                    buttonCountMap.put(button, buttonCountMap.getOrDefault(button, 0L) + countCall);
                }
                totalCalls += countCall;
            }

            textBuilder.append(String.format("<b>Запись на прием: </b> %d\n", totalAppointments));

            for (Map.Entry<Button, Long> entry : buttonCountMap.entrySet()) {
                textBuilder.append(String.format("<b>%s: </b> %d\n", getButtonDisplayName(entry.getKey()), entry.getValue()));
            }
            textBuilder.append(String.format("\n<b>Общее количество заявок:</b> %d\n", totalApplications));
        }
        String text = textBuilder.toString();
        sendMessage.setText(text);
        sendMessage.setParseMode("HTML");

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private String getButtonDisplayName(Button button) {
        return switch (button) {
            case MAKE_AN_APPOINTMENT -> "Запись на прием";
            case SEND_APPLICATION -> "Отправить";
            case TELEGRAM_FOR_BLOG -> "Подписывайтесь на наш блог в Telegram";
            case TELEGRAM_FOOTER -> "Telegram (Footer)";
            case INSTAGRAM_FOOTER -> "Instagram (Footer)";
            case YOUTUBE_FOOTER -> "YouTube (Footer)";
            case FACEBOOK_FOOTER -> "FaceBook (Footer)";
            default -> button.name();
        };
    }

}
