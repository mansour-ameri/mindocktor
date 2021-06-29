package com.mindoktor.mindoktorassignment.model.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Officer {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid2")
    private UUID id;

    private String name;

    private String lastname;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean assigned;

}
