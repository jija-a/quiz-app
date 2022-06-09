package by.mercer.quiz.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {

    private Long id;

    private String title;

    private ImageResponse image;

    private String type;

    private List<OptionResponse> options;
}
