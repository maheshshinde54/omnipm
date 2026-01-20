package com.beatrix.omniPM.timesheet.dto;

import com.beatrix.omniPM.timesheet.entity.TimesheetStatus;
import com.beatrix.omniPM.timesheet.entity.WorkCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimesheetDTO
{
    private Long timesheetId;

    private LocalDate workDate;

    private Integer minutesLogged;

    private String description;

    private String issueKey;

    private WorkCategory category;

    private Boolean billable;

    private TimesheetStatus status;

    private String rejectionReason;

    private Integer version;

    private LocalDateTime createdAt;

    private LocalDateTime lastModified;





}
