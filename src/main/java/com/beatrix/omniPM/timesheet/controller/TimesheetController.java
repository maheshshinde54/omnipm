package com.beatrix.omniPM.timesheet.controller;

import com.beatrix.omniPM.timesheet.dto.TimesheetDTO;
import com.beatrix.omniPM.timesheet.entity.TimesheetEntity;
import com.beatrix.omniPM.timesheet.services.TimesheetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")
@AllArgsConstructor
public class TimesheetController
{
    private TimesheetService timesheetService;

    @GetMapping("/getalltimesheet")
    public List<TimesheetDTO> allTimesheets()
    {
        return timesheetService.findAll();
    }

    @PostMapping("/addtimesheet")
    public ResponseEntity<TimesheetDTO> addTimesheet(@RequestBody TimesheetDTO timesheetDTO)
    {
        TimesheetDTO savedTimesheet = timesheetService.addTimesheet(timesheetDTO);
        return ResponseEntity.ok()
                             .body(savedTimesheet);

    }
    @PutMapping("/{timesheetId}")
    public ResponseEntity<TimesheetDTO> updateTimesheet(@RequestBody TimesheetDTO timesheetDTO, @PathVariable Long timesheetId) throws Exception
    {
        TimesheetDTO updatedTimesheet = timesheetService.updateTimesheet( timesheetDTO, timesheetId);
        return new ResponseEntity<>(updatedTimesheet, HttpStatus.OK);
    }

    @DeleteMapping("/{timesheetId}")
    public ResponseEntity<TimesheetDTO> deleteTimesheetById(@PathVariable Long timesheetId)
    {
        return ResponseEntity.ok().body(timesheetService.deleteTimesheetById(timesheetId));
    }

}
