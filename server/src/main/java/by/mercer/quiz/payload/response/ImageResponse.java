package by.mercer.quiz.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private Long id;

    private String name;

    private String type;

    private String data;
}
