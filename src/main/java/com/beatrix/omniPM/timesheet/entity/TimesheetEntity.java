package com.beatrix.omniPM.timesheet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "timesheets")
public class TimesheetEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timesheet_id", length = 10)
    private Long timesheetId;

    //private UserEntity user;

    //private ProjectEntity project;

    @NotNull
    private LocalDate workDate;

    @NotNull
    @Positive(message = "Time must be greater than zero")
    @Column(name = "minutes_logged", nullable = false, length = 3)
    private Integer minutesLogged;

    @Column(columnDefinition = "TEXT", length = 50)
    private String description;

    @Column(name = "issue_key", nullable = false, length = 20)
    private String issueKey;

    @Enumerated(EnumType.STRING)
    private WorkCategory category;

    private boolean billable = true;

    @Enumerated(EnumType.STRING)
    private TimesheetStatus status;

    @Column(columnDefinition = "TEXT", length = 50)
    private String rejectionReason;

    @Version
    private Integer version;

    private boolean deleted = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime lastModified;


}
