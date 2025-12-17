package org.example.reminderapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.reminderapp.entity.enums.Status;

import java.time.OffsetDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reminders")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name = "remind")
    private OffsetDateTime remindAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    //add type?

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

}
