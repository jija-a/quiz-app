package by.mercer.quiz.repository;

import by.mercer.quiz.domain.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<FileImage, Long> {

    Optional<FileImage> findByName(String name);
}
