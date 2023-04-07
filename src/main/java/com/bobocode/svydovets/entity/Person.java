package com.bobocode.svydovets.entity;

import com.bobocode.svydovets.bibernate.annotation.Column;
import com.bobocode.svydovets.bibernate.annotation.Entity;
import com.bobocode.svydovets.bibernate.annotation.GeneratedValue;
import com.bobocode.svydovets.bibernate.annotation.Id;
import com.bobocode.svydovets.bibernate.annotation.OneToMany;
import com.bobocode.svydovets.bibernate.annotation.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table("persons")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column
    private String team;

    @OneToMany
    private List<Note> notes = new ArrayList<>();

}
