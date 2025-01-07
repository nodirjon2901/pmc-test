package uz.result.primemedicalcentre.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.AboutUsBannerCreateDTO;
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.AboutUsBannerMapper;
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.AboutUsBannerResponseDTO;
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.AboutUsBannerUpdateDTO;
import uz.result.primemedicalcentre.domain.entity.AboutUsBanner;
import uz.result.primemedicalcentre.exception.AlreadyExistsException;
import uz.result.primemedicalcentre.exception.NotFoundException;
import uz.result.primemedicalcentre.repository.AboutUsBannerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AboutUsBannerService {

    private final AboutUsBannerRepository bannerRepository;

    private final PhotoService photoService;

    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(AboutUsBannerService.class);

    public ResponseEntity<ApiResponse<AboutUsBannerResponseDTO>> create(String json, MultipartFile photoFile) {
        ApiResponse<AboutUsBannerResponseDTO> response = new ApiResponse<>();
        Optional<AboutUsBanner> first = bannerRepository.findAll().stream().findFirst();
        if (first.isPresent()) {
            throw new AlreadyExistsException("Banner already exists for About us Page. You can update its");
        }
        try {
            AboutUsBannerCreateDTO createDTO = objectMapper.readValue(json, AboutUsBannerCreateDTO.class);
            AboutUsBanner banner = new AboutUsBanner(createDTO);
            banner.setPhoto(photoService.save(photoFile));
            response.setMessage("Successfully created");
            response.setData(new AboutUsBannerResponseDTO(bannerRepository.save(banner)));
            return ResponseEntity.ok(response);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON for blog creation", e);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    public ResponseEntity<ApiResponse<?>> find(String lang) {
        AboutUsBanner banner = bannerRepository.findAll().stream().findFirst().orElseThrow(() -> {
            logger.warn("Banner is not found");
            return new NotFoundException("Banner is not found");
        });
        String message = "Successfully found";
        if (lang == null || lang.equals("-")) {
            ApiResponse<AboutUsBannerResponseDTO> response = new ApiResponse<>();
            response.setData(new AboutUsBannerResponseDTO(banner));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<AboutUsBannerMapper> response = new ApiResponse<>();
        response.setData(new AboutUsBannerMapper(banner, lang));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<AboutUsBannerResponseDTO>> update(AboutUsBannerUpdateDTO updateDTO) {
        ApiResponse<AboutUsBannerResponseDTO> response = new ApiResponse<>();
        AboutUsBanner fromDb = bannerRepository.findById(updateDTO.getId()).orElseThrow(() -> {
            logger.warn("Banner is not found with id: {}", updateDTO.getId());
            return new NotFoundException("Banner is not found with id: " + updateDTO.getId());
        });
        return null;
    }

}
