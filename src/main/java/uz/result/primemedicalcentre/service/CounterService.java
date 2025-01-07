package uz.result.primemedicalcentre.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.result.primemedicalcentre.bot.PrimeBot;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.entity.Button;
import uz.result.primemedicalcentre.domain.entity.Counter;
import uz.result.primemedicalcentre.repository.ApplicationRepository;
import uz.result.primemedicalcentre.repository.CounterRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository counterRepository;

    private final ApplicationRepository applicationRepository;

    private final PrimeBot bot;

    public ResponseEntity<ApiResponse<?>> addCallNumber(Button button) {
        ApiResponse<?> response = new ApiResponse<>();
        Counter counter = Counter.builder()
                .section(button)
                .countCall(1L)
                .build();
        counterRepository.save(counter);
        response.setMessage("Success. Button " + button.name() + " count incremented.");
        return ResponseEntity.status(201).body(response);
    }

//    @Scheduled(cron = "0 * * * * *")//every minute
    @Scheduled(cron = "0 0 0 * * MON",zone = "Asia/Tashkent")
    public void checkAndSendCounter() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneMinuteAgo = now.minusWeeks(1);

        List<Counter> counterList = counterRepository.findAllByCreatedDateBetween(oneMinuteAgo, now);

        Map<Button, Long> aggregatedCounters = new HashMap<>();
        for (Counter counter : counterList) {
            aggregatedCounters.put(
                    counter.getSection(),
                    aggregatedCounters.getOrDefault(counter.getSection(), 0L) + counter.getCountCall()
            );
        }

        List<Counter> savedCounters = new ArrayList<>();
        for (Map.Entry<Button, Long> entry : aggregatedCounters.entrySet()) {
            Counter aggregatedCounter = Counter.builder()
                    .section(entry.getKey())
                    .countCall(entry.getValue())
                    .build();
            savedCounters.add(aggregatedCounter);
        }

        counterRepository.deleteAll(counterList);

        Long applicationCount = applicationRepository.countApplicationInOneWeek(oneMinuteAgo, now);
        bot.sendCounter(savedCounters, applicationCount);
    }

}
