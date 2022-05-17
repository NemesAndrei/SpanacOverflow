package com.example.spanacoverflow.model;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "created")
    private java.sql.Timestamp created;

    @Column(name = "votes", columnDefinition = "integer default 0")
    private Integer votes;

    @Column(name = "questionTitle")
    private String questionTitle;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "questionid", referencedColumnName = "id")
    private Question question;

}
