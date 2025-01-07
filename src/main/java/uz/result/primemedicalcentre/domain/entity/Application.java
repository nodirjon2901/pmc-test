package uz.result.primemedicalcentre.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import uz.result.primemedicalcentre.domain.dto.application.ApplicationCreateDTO;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName;

    String phoneNum;

    String service;

    @Column(length = 5000)
    String comment;

    @CreationTimestamp
    LocalDateTime createdDate;

    public Application(ApplicationCreateDTO createDTO) {
        if (createDTO == null)
            return;
        this.fullName = createDTO.getFullName();
        this.phoneNum = createDTO.getPhoneNum();
        this.service = createDTO.getService();
        this.comment=createDTO.getComment();
    }

}
