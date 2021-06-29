package com.mindoktor.mindoktorassignment.model.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class OccurrenceCase {

    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid2")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "officer_id", referencedColumnName = "id")
    private Officer officer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "incident_id", referencedColumnName = "id")
    private Incident incident;

}
