package by.mercer.quiz.payload.response;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class QuizResponse {

    private Long id;

    private String title;

    private List<QuestionResponse> questions;
}
