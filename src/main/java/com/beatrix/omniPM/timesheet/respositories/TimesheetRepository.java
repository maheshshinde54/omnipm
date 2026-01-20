package com.beatrix.omniPM.timesheet.respositories;

import com.beatrix.omniPM.timesheet.entity.TimesheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<TimesheetEntity, Long>
{


}
