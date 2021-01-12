package ru.suhan.service.question;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.answer.Answer;
import ru.suhan.domain.answer.AnswerBase;
import ru.suhan.domain.question.Question;
import ru.suhan.domain.question.QuestionBase;
import ru.suhan.repository.AnswerRepository;
import ru.suhan.repository.QuestionRepository;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceBase implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Cacheable("questions")
    @Override
    public List<Question> all() {
        return this.questionRepository.findAll()
                .stream()
                .map(questionEntity -> {
                    final List<Answer> answers = answerRepository.findAllByQuestionEntityId(questionEntity.getId())
                            .stream()
                            .map(AnswerBase::new)
                            .collect(Collectors.toList());
                    return new QuestionBase(questionEntity, answers);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Question questionByLevel(Amount level) {
        final List<Integer> ids = this.all().stream().filter(a -> a.level().id().equals(level.id())).map(a ->a.id().intValue()).collect(Collectors.toList());
        Random rand = new Random();
        Integer randomElement = ids.get(rand.nextInt(ids.size()));
        return this.all()
                .stream()
                .filter(a -> a.id().equals(randomElement.longValue()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Question questionById(Long id) {
        return this.all().stream().filter(question -> question.id().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public Question replaceQuestion(Long questionId) {
        return this.all().stream().filter(question -> !question.id().equals(questionId)).findAny().orElseThrow(RuntimeException::new);
    }

}
