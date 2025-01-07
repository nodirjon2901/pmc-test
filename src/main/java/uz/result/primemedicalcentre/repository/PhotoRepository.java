package uz.result.primemedicalcentre.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.result.primemedicalcentre.domain.entity.Photo;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Optional<Photo> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "delete from photo where id=:id", nativeQuery = true)
    void deleteByCustom(@Param("id") Long id);

    Optional<Photo> findByHttpUrl(String url);
}
