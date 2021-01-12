package ru.suhan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text", nullable = false, unique = false)
    private String questionText;

    @OneToOne(targetEntity = AmountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "level_amount_id")
    private AmountEntity levelAmountEntity;

}
