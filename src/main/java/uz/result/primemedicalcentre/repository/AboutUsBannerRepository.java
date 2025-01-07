package uz.result.primemedicalcentre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.result.primemedicalcentre.domain.entity.AboutUsBanner;

@Repository
public interface AboutUsBannerRepository extends JpaRepository<AboutUsBanner, Long> {
}
