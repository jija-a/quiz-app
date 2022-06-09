package by.mercer.quiz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quizzes")
@SelectBeforeUpdate
@DynamicUpdate
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="category_id", nullable=false, updatable=false)
    private Category category;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="quiz")
    private List<Question> questions;
}
