package ru.suhan.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = false)
    private String login;

    @OneToOne(targetEntity = AmountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "guarantee_amount_id")
    private AmountEntity guaranteeAmountEntity;

    @OneToOne(targetEntity = AmountEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_amount_id")
    private AmountEntity currentAmountEntity;

    @Column(name = "is_end_game", nullable = false, unique = false)
    private Boolean isEndGame;

    @ManyToMany(targetEntity = HelpEntity.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_helps",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "help_id")
    )
    private Set<HelpEntity> helpEntitySet;

    @OneToOne(targetEntity = QuestionEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id_mistake_right")
    private QuestionEntity questionEntityMistakeRight;

}
