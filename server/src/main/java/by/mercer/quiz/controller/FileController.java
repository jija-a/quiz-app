package by.mercer.quiz.controller;

import by.mercer.quiz.domain.FileImage;
import by.mercer.quiz.payload.response.ImageResponse;
import by.mercer.quiz.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.Base64;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final ImageRepository imageRepository;

    @GetMapping(path = {"/{id}"})
    public ImageResponse getImage(@PathVariable("id") Long id) {

        FileImage retrievedImage = imageRepository.findById(id)
                .orElseThrow(EntityExistsException::new);

        return new ImageResponse(
                retrievedImage.getId(),
                retrievedImage.getName(),
                retrievedImage.getType(),
                Base64.getEncoder().encodeToString(retrievedImage.getData())
        );
    }
}
