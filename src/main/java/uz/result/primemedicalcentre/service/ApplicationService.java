package uz.result.primemedicalcentre.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.result.primemedicalcentre.bot.PrimeBot;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.application.ApplicationCreateDTO;
import uz.result.primemedicalcentre.domain.entity.Application;
import uz.result.primemedicalcentre.repository.ApplicationRepository;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final PrimeBot bot;

    public ResponseEntity<ApiResponse<Application>> create(ApplicationCreateDTO createDTO) {
        ApiResponse<Application> response = new ApiResponse<>();
        Application application = new Application(createDTO);
        Application app = applicationRepository.save(application);
        bot.handleSendApplication(app);
        response.setData(app);
        response.setMessage("Successfully created");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> delete(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application is not found with id: " + id));
        applicationRepository.delete(application);
        response.setMessage("Successfully deleted");
        return ResponseEntity.ok(response);
    }

}
