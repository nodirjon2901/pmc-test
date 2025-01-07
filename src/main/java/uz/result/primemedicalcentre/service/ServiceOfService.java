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
import uz.result.primemedicalcentre.domain.dto.service.ServiceCreateDTO;
import uz.result.primemedicalcentre.domain.dto.service.ServiceMapper;
import uz.result.primemedicalcentre.domain.dto.service.ServiceResponseDTO;
import uz.result.primemedicalcentre.domain.dto.service.ServiceUpdateDTO;
import uz.result.primemedicalcentre.exception.NotFoundException;
import uz.result.primemedicalcentre.repository.DoctorRepository;
import uz.result.primemedicalcentre.repository.ServiceRepository;
import uz.result.primemedicalcentre.util.SlugUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfService {

    private final ServiceRepository serviceRepository;

    private final DoctorRepository doctorRepository;

    private final ObjectMapper objectMapper;

    private final PhotoService photoService;

    private final Logger logger = LoggerFactory.getLogger(ServiceOfService.class);

    public ResponseEntity<ApiResponse<ServiceResponseDTO>> create(String json, MultipartFile photoFile) {
        ApiResponse<ServiceResponseDTO> response = new ApiResponse<>();
        try {
            ServiceCreateDTO createDTO = objectMapper.readValue(json, ServiceCreateDTO.class);
            uz.result.primemedicalcentre.domain.entity.Service service = new uz.result.primemedicalcentre.domain.entity.Service(createDTO);
            service.setActive(true);
            service.setPhoto(photoService.save(photoFile));
            uz.result.primemedicalcentre.domain.entity.Service save = serviceRepository.save(service);
            String slug = save.getId() + "-" + SlugUtil.makeSlug(save.getNameRu());
            serviceRepository.updatedSlug(save.getId(), slug);
            save.setSlug(slug);
            response.setData(new ServiceResponseDTO(save));
            response.setMessage("Successfully created");
            return ResponseEntity.status(201).body(response);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON for blog creation", e);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    public ResponseEntity<ApiResponse<?>> findBySlug(String slug, String lang) {
        uz.result.primemedicalcentre.domain.entity.Service service = serviceRepository.findBySlug(slug).orElseThrow(() -> {
            logger.warn("Service is not found with SLUG: {}", slug);
            return new NotFoundException("Service is not found with slug: " + slug);
        });
        String message = "Successfully found";
        if (lang == null || lang.equals("-")) {
            ApiResponse<ServiceResponseDTO> response = new ApiResponse<>();
            response.setData(new ServiceResponseDTO(service));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<ServiceMapper> response = new ApiResponse<>();
        response.setData(new ServiceMapper(service, lang));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> findById(Long id, String lang) {
        uz.result.primemedicalcentre.domain.entity.Service service = serviceRepository.findById(id).orElseThrow(() -> {
            logger.warn("Service is not found with ID: {}", id);
            return new NotFoundException("Service is not found with id: " + id);
        });
        String message = "Successfully found";
        if (lang == null || lang.equals("-")) {
            ApiResponse<ServiceResponseDTO> response = new ApiResponse<>();
            response.setData(new ServiceResponseDTO(service));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<ServiceMapper> response = new ApiResponse<>();
        response.setData(new ServiceMapper(service, lang));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> findAll(String lang, Integer limit) {
        List<uz.result.primemedicalcentre.domain.entity.Service> all = serviceRepository.findAll();
        if (limit != null) {
            all = all.stream().limit(limit).toList();
        }
        String message = "Successfully found: " + all.size();
        if (lang == null || lang.equals("-")) {
            ApiResponse<List<ServiceResponseDTO>> response = new ApiResponse<>();
            response.setData(new ArrayList<>());
            all.forEach(service -> response.getData().add(new ServiceResponseDTO(service)));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<List<ServiceMapper>> response = new ApiResponse<>();
        response.setData(new ArrayList<>());
        all.forEach(service -> response.getData().add(new ServiceMapper(service, lang)));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<ServiceResponseDTO>> update(ServiceUpdateDTO updateDTO) {
        ApiResponse<ServiceResponseDTO> response = new ApiResponse<>();
        uz.result.primemedicalcentre.domain.entity.Service fromDb = serviceRepository.findById(updateDTO.getId()).orElseThrow(() -> {
            logger.warn("Service is not found with ID: {}", updateDTO.getId());
            return new NotFoundException("Service is not found with id: " + updateDTO.getId());
        });
        uz.result.primemedicalcentre.domain.entity.Service service = ServiceMapper.updateDtoToEntity(updateDTO);
        if (service.isActive() != fromDb.isActive()) {
            fromDb.setActive(service.isActive());
        }
        if (service.getNameUz() != null) {
            fromDb.setNameUz(service.getNameUz());
        }
        if (service.getNameRu() != null) {
            fromDb.setNameRu(service.getNameRu());
        }
        if (service.getDescriptionUz() != null) {
            fromDb.setDescriptionUz(fromDb.getDescriptionUz());
        }
        if (service.getDescriptionRu() != null) {
            fromDb.setDescriptionRu(service.getDescriptionRu());
        }
        response.setData(new ServiceResponseDTO(serviceRepository.save(fromDb)));
        response.setMessage("Successfully updated");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> delete(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        uz.result.primemedicalcentre.domain.entity.Service service = serviceRepository.findById(id).orElseThrow(() -> {
            logger.warn("Service is not found with id: {}", id);
            return new NotFoundException("Service is not found with id: " + id);
        });
        serviceRepository.delete(service);
        response.setMessage("Successfully delete");
        return ResponseEntity.ok(response);
    }

}
