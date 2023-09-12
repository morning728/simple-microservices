package com.morning.numapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "record",  schema = "num_note")
@Data
public class Record extends BaseEntity {


    @Column(name = "username")
    private String username;

    @Column(name = "description")
    private String description;

    @Column(name = "mark")
    private Integer mark;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Float height;

    @Column(name = "mood_mark")
    private Integer moodMark;

    @Column(name = "steps")
    private Integer steps;

    @Column(name = "sheets")
    private Integer sheets;

    @Column(name = "money")
    private Integer money;


}
