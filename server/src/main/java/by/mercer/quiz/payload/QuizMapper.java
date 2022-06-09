package by.mercer.quiz.payload;

import by.mercer.quiz.domain.FileImage;
import by.mercer.quiz.domain.Option;
import by.mercer.quiz.domain.Question;
import by.mercer.quiz.domain.Quiz;
import by.mercer.quiz.payload.response.ImageResponse;
import by.mercer.quiz.payload.response.OptionResponse;
import by.mercer.quiz.payload.response.QuestionResponse;
import by.mercer.quiz.payload.response.QuizResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Slf4j
public class QuizMapper {

    public static QuizResponse mapToQuizResponse(Quiz quiz) {
        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setId(quiz.getId());
        quizResponse.setTitle(quiz.getTitle());

        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            QuestionResponse questionResponse = mapQuestion(question);
            questionResponses.add(questionResponse);
        }
        quizResponse.setQuestions(questionResponses);
        log.info("Response: {}", quizResponse);
        return quizResponse;
    }

    private static QuestionResponse mapQuestion(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setTitle(question.getTitle());
        questionResponse.setType(question.getType().name());

        mapQuestionImage(question, questionResponse);
        mapOptions(question, questionResponse);
        return questionResponse;
    }

    private static void mapQuestionImage(Question question, QuestionResponse questionResponse) {
        if (question.getImage() != null) {
            ImageResponse imageResponse = mapImage(question.getImage());
            questionResponse.setImage(imageResponse);
        }
    }

    private static void mapOptions(Question question, QuestionResponse questionResponse) {
        List<OptionResponse> optionResponseList = new ArrayList<>();
        for (Option option : question.getOptions()) {
            OptionResponse optionResponse = mapOption(option);
            optionResponseList.add(optionResponse);
        }
        questionResponse.setOptions(optionResponseList);
    }

    private static OptionResponse mapOption(Option option) {
        OptionResponse optionResponse = new OptionResponse();
        optionResponse.setId(option.getId());
        optionResponse.setTitle(option.getTitle());
        if (option.getImage() != null) {
            ImageResponse imageResponse = mapImage(option.getImage());
            optionResponse.setImage(imageResponse);
        }
        return optionResponse;
    }

    private static ImageResponse mapImage(FileImage image) {
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setId(image.getId());
        imageResponse.setName(image.getName());
        imageResponse.setType(image.getType());
        imageResponse.setData(Base64.getEncoder().encodeToString(image.getData()));
        log.info("Image: {}", imageResponse);
        return imageResponse;
    }
}
