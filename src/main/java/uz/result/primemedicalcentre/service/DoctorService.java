package uz.result.primemedicalcentre.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.doctor.*;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorCreateDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorMapper;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorResponseDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorUpdate;
import uz.result.primemedicalcentre.domain.entity.doctor.Doctor;
import uz.result.primemedicalcentre.domain.entity.doctor.Education;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;
import uz.result.primemedicalcentre.domain.entity.doctor.Specialization;
import uz.result.primemedicalcentre.exception.NotFoundException;
import uz.result.primemedicalcentre.repository.DoctorRepository;
import uz.result.primemedicalcentre.repository.EducationRepository;
import uz.result.primemedicalcentre.repository.ServiceOfDoctorRepository;
import uz.result.primemedicalcentre.repository.SpecializationRepository;
import uz.result.primemedicalcentre.util.SlugUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    private final SpecializationRepository specializationRepository;

    private final EducationRepository educationRepository;

    private final ServiceOfDoctorRepository serviceOfDoctorRepository;

    private final PhotoService photoService;

    private final ObjectMapper objectMapper;

    private final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> create(String json, MultipartFile photoFile) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        try {
            DoctorCreateDTO createDTO = objectMapper.readValue(json, DoctorCreateDTO.class);
            Doctor doctor = new Doctor(createDTO);
            doctor.setActive(true);
            doctor.setPhoto(photoService.save(photoFile));
            Doctor save = doctorRepository.save(doctor);
            String slug = save.getId() + "-" + SlugUtil.makeSlug(save.getFullNameRu());
            doctorRepository.updateSlug(save.getId(), slug);
            save.setSlug(slug);
            System.out.println(save);
            response.setData(new DoctorResponseDTO(save));
            response.setMessage("Successfully created");
            return ResponseEntity.status(201).body(response);
        } catch (JsonProcessingException e) {
            logger.error("Error processing JSON for blog creation", e);
            response.setMessage(e.getMessage());
            return ResponseEntity.status(400).body(response);
        }
    }

    public ResponseEntity<ApiResponse<?>> findBySlug(String slug, String lang) {
        Doctor doctor = doctorRepository.findBySlug(slug).orElseThrow(() -> {
            logger.warn("Doctor is not found with slug: {}", slug);
            return new NotFoundException("Doctor is not found with slug: " + slug);
        });
        String message = "Successfully found";
        if (lang == null || lang.equals("-")) {
            ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
            response.setData(new DoctorResponseDTO(doctor));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<DoctorMapper> response = new ApiResponse<>();
        response.setData(new DoctorMapper(doctor, lang));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> findById(Long id, String lang) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> {
            logger.warn("Doctor is not found with id: {}", id);
            return new NotFoundException("Doctor is not found with id: " + id);
        });
        String message = "Successfully found";
        if (lang == null || lang.equals("-")) {
            ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
            response.setData(new DoctorResponseDTO(doctor));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<DoctorMapper> response = new ApiResponse<>();
        response.setData(new DoctorMapper(doctor, lang));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> findAll(String lang, Integer limit) {
        List<Doctor> all = doctorRepository.findAll();
        if (limit != null) {
            all = all.stream().limit(limit).toList();
        }
        String message = "Successfully found: " + all.size();
        if (lang == null || lang.equals("-")) {
            ApiResponse<List<DoctorResponseDTO>> response = new ApiResponse<>();
            response.setData(new ArrayList<>());
            all.forEach(doctor -> response.getData().add(new DoctorResponseDTO(doctor)));
            response.setMessage(message);
            return ResponseEntity.ok(response);
        }
        ApiResponse<List<DoctorMapper>> response = new ApiResponse<>();
        response.setData(new ArrayList<>());
        all.forEach(doctor -> response.getData().add(new DoctorMapper(doctor, lang)));
        response.setMessage(message);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> update(DoctorUpdateDTO updateDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Doctor fromDb = doctorRepository.findById(updateDTO.getId()).orElseThrow(() -> {
            logger.warn("Doctor is not found with id: {}", updateDTO.getId());
            return new NotFoundException("Doctor is not found with id: " + updateDTO.getId());
        });
        Doctor doctor = DoctorMapper.updateDtoToEntity(updateDTO);

        if (doctor.getFullNameUz() != null) {
            fromDb.setFullNameUz(doctor.getFullNameUz());
        }
        if (doctor.getFullNameRu() != null) {
            fromDb.setFullNameRu(doctor.getFullNameRu());
            String slug = fromDb.getId() + "-" + SlugUtil.makeSlug(doctor.getFullNameRu());
            fromDb.setSlug(slug);
        }
        if (doctor.getDescriptionUz() != null) {
            fromDb.setDescriptionUz(doctor.getDescriptionUz());
        }
        if (doctor.getDescriptionRu() != null) {
            fromDb.setDescriptionRu(doctor.getDescriptionRu());
        }
        if (doctor.getExperienceUz() != null) {
            fromDb.setExperienceUz(doctor.getExperienceUz());
        }
        if (doctor.getExperienceRu() != null) {
            fromDb.setExperienceRu(doctor.getExperienceRu());
        }
        if (doctor.getReceptionTimeUz() != null) {
            fromDb.setReceptionTimeUz(doctor.getReceptionTimeUz());
        }
        if (doctor.getReceptionTimeRu() != null) {
            fromDb.setReceptionTimeRu(doctor.getReceptionTimeRu());
        }
        if (doctor.isActive() != fromDb.isActive()) {
            fromDb.setActive(doctor.isActive());
        }
        if (doctor.getSpecializationList() != null && !doctor.getSpecializationList().isEmpty()) {
            List<Specialization> specializationList = doctor.getSpecializationList();
            List<Specialization> dBpecializationList = fromDb.getSpecializationList();
            List<Specialization> removeSpecializationList = fromDb.getSpecializationList();

            for (Specialization specialization : specializationList) {
                if (specialization.getId() != null) {
                    for (Specialization dbSpecialization : dBpecializationList) {
                        if (dbSpecialization.getId().equals(specialization.getId())) {
                            if (specialization.getNameUz() != null) {
                                dbSpecialization.setNameUz(specialization.getNameUz());
                            }
                            if (specialization.getNameRu() != null) {
                                dbSpecialization.setNameRu(specialization.getNameRu());
                            }
                            if (specialization.getNameRu() == null && specialization.getNameUz() == null) {
                                removeSpecializationList.add(dbSpecialization);
                            }
                        }
                    }
                } else {
                    specialization.setDoctor(fromDb);
                    dBpecializationList.add(specialization);
                }
            }
//            for (Specialization removeSpecialization : removeSpecializationList) {
//                dBpecializationList.remove(removeSpecialization);
//                specializationRepository.deleteCustom(removeSpecialization.getId());
//            }
        }

        response.setData(new DoctorResponseDTO(doctorRepository.save(fromDb)));
        response.setMessage("Successfully updated");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> delete(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> {
            logger.warn("Doctor is not found with id: " + id);
            return new NotFoundException("Doctor is not found with id: " + id);
        });
        doctorRepository.delete(doctor);
        response.setMessage("Successfully delete");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addEducation(Long doctorId, EducationCreateDTO createDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Doctor is not found with id: " + doctorId));
        Education education = new Education(createDTO);
        education.setDoctor(doctor);
        educationRepository.save(education);
        response.setData(new DoctorResponseDTO(doctor));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateEducation(EducationResponseDTO updateDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Education dbEducation = educationRepository.findById(updateDTO.getId()).orElseThrow(() -> new NotFoundException("Education is not found with id: " + updateDTO.getId()));
        Education updateEducation = EducationMapper.updateDtoToEntity(updateDTO);
        if (updateEducation.getInstitutionUz() != null) {
            dbEducation.setInstitutionUz(updateEducation.getInstitutionUz());
        }
        if (updateEducation.getInstitutionRu() != null) {
            dbEducation.setInstitutionRu(updateEducation.getInstitutionRu());
        }
        if (updateEducation.getStartYear() != null) {
            dbEducation.setStartYear(updateEducation.getStartYear());
        }
        if (updateEducation.getFinishYear() != null) {
            dbEducation.setFinishYear(updateEducation.getFinishYear());
        }
        if (updateEducation.getQualificationUz() != null) {
            dbEducation.setQualificationUz(updateEducation.getQualificationUz());
        }
        if (updateEducation.getQualificationRu() != null) {
            dbEducation.setQualificationRu(updateEducation.getQualificationRu());
        }
        Education save = educationRepository.save(dbEducation);
        response.setData(new DoctorResponseDTO(save.getDoctor()));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> deleteEducation(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        educationRepository.deleteCustom(id);
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addService(Long doctorId, ServiceOfDoctorCreateDTO createDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Doctor is not found with id: " + doctorId));
        ServiceOfDoctor serviceOfDoctor = new ServiceOfDoctor(createDTO);
        serviceOfDoctor.setDoctor(doctor);
        serviceOfDoctor.setActive(true);
        serviceOfDoctorRepository.save(serviceOfDoctor);
        response.setData(new DoctorResponseDTO(doctor));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<List<ServiceOfDoctorResponseDTO>>> findAllServiceByDoctorId(Long doctorId) {
        ApiResponse<List<ServiceOfDoctorResponseDTO>> response = new ApiResponse<>();
        List<ServiceOfDoctorResponseDTO> serviceList = serviceOfDoctorRepository.findAllByDoctorId(doctorId).stream().map(
                ServiceOfDoctorResponseDTO::new
        ).toList();
        response.setData(serviceList);
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateService(ServiceOfDoctorUpdate updateDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        ServiceOfDoctor dbService = serviceOfDoctorRepository.findById(updateDTO.getId()).orElseThrow(() -> new NotFoundException("Service is not found with id: " + updateDTO.getId()));
        ServiceOfDoctor updateService = ServiceOfDoctorMapper.updateDtoToEntity(updateDTO);
        if (updateService.getNameUz() != null) {
            dbService.setNameUz(updateService.getNameUz());
        }
        if (updateService.getNameRu() != null) {
            dbService.setNameRu(updateService.getNameRu());
        }
        if (updateService.getPriceUz() != null) {
            dbService.setPriceUz(updateService.getPriceUz());
        }
        if (updateService.getPriceRu() != null) {
            dbService.setPriceRu(updateService.getPriceRu());
        }
        if (updateService.isActive() != dbService.isActive()) {
            dbService.setActive(updateService.isActive());
        }
        serviceOfDoctorRepository.save(dbService);
        response.setData(new DoctorResponseDTO(dbService.getDoctor()));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> deleteService(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        serviceOfDoctorRepository.deleteCustom(id);
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addSpecialization(Long doctorId, SpecializationCreateDTO createDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new NotFoundException("Doctor is not found with id: " + doctorId));
        Specialization specialization = new Specialization(createDTO);
        specialization.setDoctor(doctor);
        specializationRepository.save(specialization);
        response.setData(new DoctorResponseDTO(doctor));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateSpecialization(SpecializationResponseDTO updateDTO) {
        ApiResponse<DoctorResponseDTO> response = new ApiResponse<>();
        Specialization dbSpecialization = specializationRepository.findById(updateDTO.getId()).orElseThrow(() -> new NotFoundException("Specialization is not found id: " + updateDTO.getId()));
        Specialization updateSpecialization = SpecializationMapper.updateDtoToEntity(updateDTO);
        if (updateSpecialization.getNameUz() != null) {
            dbSpecialization.setNameUz(updateSpecialization.getNameUz());
        }
        if (updateSpecialization.getNameRu() != null) {
            dbSpecialization.setNameRu(updateSpecialization.getNameRu());
        }
        specializationRepository.save(dbSpecialization);
        response.setData(new DoctorResponseDTO(dbSpecialization.getDoctor()));
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponse<?>> deleteSpecialization(Long id) {
        ApiResponse<?> response = new ApiResponse<>();
        specializationRepository.deleteCustom(id);
        response.setMessage("Success");
        return ResponseEntity.ok(response);
    }

}
