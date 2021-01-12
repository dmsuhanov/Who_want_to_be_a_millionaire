package ru.suhan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_text", nullable = false, unique = false)
    private String answerText;

    @ManyToOne(targetEntity = QuestionEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    @Column(name = "is_true_answer", nullable = false, unique = false)
    private Boolean isTrueAnswer;

    @Column(name = "people_help_percent", nullable = false, unique = false)
    private Integer peopleHelpPercent;

    @Column(name = "is_friend_choice", nullable = false, unique = false)
    private Boolean isFriendChoice;

}
