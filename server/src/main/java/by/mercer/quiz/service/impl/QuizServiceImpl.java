package by.mercer.quiz.service.impl;

import by.mercer.quiz.domain.*;
import by.mercer.quiz.payload.request.QuizResultRequest;
import by.mercer.quiz.repository.AppUserRepository;
import by.mercer.quiz.repository.QuizRepository;
import by.mercer.quiz.repository.QuizResultRepository;
import by.mercer.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final AppUserRepository appUserRepository;

    private final QuizRepository quizRepository;

    private final QuizResultRepository quizResultRepository;

    private final DecimalFormat decimalFormat;

    @Override
    public Quiz fetchQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }

    @Override
    public List<Quiz> fetchQuizzesByCategoryId(Long categoryId) {
        return quizRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public void submitQuizResult(QuizResultRequest result) {
        Quiz quiz = quizRepository.findById(result.getQuizId())
                .orElseThrow(() -> new EntityNotFoundException("Quiz with id:" + result.getQuizId() + " not found"));

        int count = quiz.getQuestions().size();
        long rightAnswers = calculateRightAnswers(result, quiz);

        double percent = calculatePercentage(rightAnswers, count);
        double mark = Math.ceil(percent / 10);

        QuizResult quizResult = generateQuizResult(percent, mark, result.getQuizId());
        quizResultRepository.save(quizResult);
        log.info("Quiz submitted, percent; {}, mark: {}", percent, mark);
    }

    private QuizResult generateQuizResult(double percent, double mark, Long quizId) {
        String username = getCurrentPrincipalUsername();
        AppUser user = appUserRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(EntityNotFoundException::new);

        return QuizResult.builder()
                .quiz(quiz)
                .percent(percent)
                .student(user)
                .submitDate(LocalDateTime.now())
                .mark(mark)
                .build();
    }

    private String getCurrentPrincipalUsername() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }

    private long calculateRightAnswers(QuizResultRequest result, Quiz quiz) {
        return quiz.getQuestions().stream()
                .filter(question -> isAnswerRight(question, result.getAnswers().get(question.getId())))
                .count();
    }

    private boolean isAnswerRight(Question question, List<String> answers) {
        List<String> trimAnswers = answers.stream()
                .map(answer -> answer.trim().toLowerCase())
                .collect(Collectors.toList());
        return question.getOptions().stream()
                .filter(Option::getIsRight)
                .allMatch(option -> trimAnswers.contains(option.getTitle().trim().toLowerCase()));
    }

    public double calculatePercentage(double obtained, double total) {
        decimalFormat.setMaximumFractionDigits(2);
        return Double.parseDouble(decimalFormat.format(obtained * 100 / total));
    }
}
