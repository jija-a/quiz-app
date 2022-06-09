package by.mercer.quiz.service;

import by.mercer.quiz.domain.Quiz;
import by.mercer.quiz.payload.request.QuizResultRequest;

import java.util.List;

public interface QuizService {

    Quiz fetchQuizById(Long id);

    List<Quiz> fetchQuizzesByCategoryId(Long categoryId);

    void submitQuizResult(QuizResultRequest result);
}
