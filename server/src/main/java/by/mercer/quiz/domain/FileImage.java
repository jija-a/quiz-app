package by.mercer.quiz.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
public class FileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "data", columnDefinition = "LONGBLOB")
    private byte[] data;

    @OneToOne(optional = false, mappedBy = "image")
    private Question question;
}
