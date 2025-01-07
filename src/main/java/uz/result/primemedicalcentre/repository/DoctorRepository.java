package uz.result.primemedicalcentre.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.result.primemedicalcentre.domain.entity.doctor.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Modifying
    @Transactional
    @Query(value = "update doctor set slug=:slug where id=:id", nativeQuery = true)
    void updateSlug(@Param("id") Long id, @Param("slug") String slug);

    Optional<Doctor> findBySlug(String slug);

}
