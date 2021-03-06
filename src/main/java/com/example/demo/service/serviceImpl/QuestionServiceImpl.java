package com.example.demo.service.serviceImpl;


import com.example.demo.dto.question.AddQuestionDTO;
import com.example.demo.dto.question.UpdateQuestionDTO;
import com.example.demo.model.Activity;
import com.example.demo.model.Question;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ActivityRepository activityRepository;

    @Override
    public Set<Question> getQuestions(long parseLong) {
        return questionRepository.getQuestions(parseLong);
    }

    @Override
    public void addQuestion(AddQuestionDTO addQuestionDTO) {
        Activity activity = activityRepository.findById(addQuestionDTO.getActivityId()).get();
        if(activity.getStartDate().isBefore(LocalDateTime.now())) throw new RuntimeException("The activity in the past can not update");
        Question question = questionRepository.save(new Question(addQuestionDTO.getText(),Set.of()));
        activity.getAskedQuestions().add(question);
        activityRepository.save(activity);
    }

    @Override
    public void deleteQuestion(Long id) {
        Activity activity = activityRepository.findById(questionRepository.getActivityId(id)).get();
        if(activity.getStartDate().isBefore(LocalDateTime.now())) throw new RuntimeException("The activity in the past can not update");
        questionRepository.deleteById(id);
    }



}
