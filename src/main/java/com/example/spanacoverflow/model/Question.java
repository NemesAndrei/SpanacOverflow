package com.example.spanacoverflow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "created")
    private java.sql.Timestamp created;

    @Column(name = "votes", columnDefinition = "integer default 0")
    private Integer votes;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;


    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private Set<Answer> answerSet;

    @ManyToMany
    @JoinTable(name = "question_tag", joinColumns = @JoinColumn(name = "questionid"), inverseJoinColumns = @JoinColumn(name = "tagid"))
    private Set<Tag> tags;
}
