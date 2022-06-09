package by.mercer.quiz.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_results")
@SelectBeforeUpdate
@DynamicUpdate
@Data
@Builder
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mark", nullable = false)
    private Double mark;

    @Column(name = "percent", nullable = false)
    private Double percent;

    @Column(name = "submit_date")
    private LocalDateTime submitDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false, updatable = false)
    private AppUser student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id", nullable = false, updatable = false)
    private Quiz quiz;
}
