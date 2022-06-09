package by.mercer.quiz.payload.response;

import lombok.Data;

@Data
public class OptionResponse {

    private Long id;

    private String title;

    private ImageResponse image;
}
