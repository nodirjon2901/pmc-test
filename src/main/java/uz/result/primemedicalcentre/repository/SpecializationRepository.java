package uz.result.primemedicalcentre.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.result.primemedicalcentre.domain.entity.doctor.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from doctor_specialization where id=:id", nativeQuery = true)
    void deleteCustom(@Param("id") Long id);

}
