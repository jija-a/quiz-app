package by.mercer.quiz.controller;

import by.mercer.quiz.domain.Quiz;
import by.mercer.quiz.payload.QuizMapper;
import by.mercer.quiz.payload.request.QuizResultRequest;
import by.mercer.quiz.payload.response.QuizResponse;
import by.mercer.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public QuizResponse fetchQuizById(@PathVariable("id") Long id) {
        Quiz quiz = quizService.fetchQuizById(id);
        return QuizMapper.mapToQuizResponse(quiz);
    }

    @GetMapping
    public List<QuizResponse> fetchAllQuizzesByCategory(@RequestParam("category") Long categoryId) {
        return quizService.fetchQuizzesByCategoryId(categoryId).stream()
                .map(quiz -> modelMapper.map(quiz, QuizResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/result")
    public void submitQuizResult(@RequestBody QuizResultRequest quizResultRequest) {
        quizService.submitQuizResult(quizResultRequest);
    }
}
