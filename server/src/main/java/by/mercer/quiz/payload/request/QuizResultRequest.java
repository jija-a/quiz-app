package by.mercer.quiz.payload.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuizResultRequest {

    private Long quizId;

    Map<Long, List<String>> answers;
}
