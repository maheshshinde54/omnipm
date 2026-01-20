package com.beatrix.omniPM.timesheet.services;

import com.beatrix.omniPM.timesheet.dto.TimesheetDTO;
import com.beatrix.omniPM.timesheet.entity.TimesheetEntity;
import com.beatrix.omniPM.timesheet.exceptions.ResourceNotFound;
import com.beatrix.omniPM.timesheet.respositories.TimesheetRepository;
import com.beatrix.omniPM.user.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class TimesheetService
{
    private TimesheetRepository timesheetRepository;

    private ModelMapper modelMapper;

    private boolean isExist(Long timesheetId)
    {
        if (timesheetRepository.existsById(timesheetId))
            return true;
        else throw new ResourceNotFound("Timesheet with id " + timesheetId + "not found");
    }

    public List<TimesheetDTO> findAll()
    {
        return timesheetRepository.findAll()
                                  .stream()
                                  .map(TimesheetEntity -> modelMapper.map(TimesheetEntity, TimesheetDTO.class))
                                  .toList();

    }

    public TimesheetDTO addTimesheet(TimesheetDTO timesheetDTO)
    {
        TimesheetEntity toAdd = modelMapper.map(timesheetDTO, TimesheetEntity.class);
        TimesheetEntity addedTimesheet = timesheetRepository.save(toAdd);
        return modelMapper.map(addedTimesheet, TimesheetDTO.class);
    }

//    public TimesheetDTO updateTimesheet(TimesheetDTO timesheetDTO, Long timesheetId) throws Exception
//    {
//        TimesheetEntity toUpdateTimesheet = timesheetRepository.findById(timesheetId)
//                                                               .orElseThrow(() -> new ResourceNotFound("Timesheet not found"));
//        modelMapper.map(timesheetDTO, TimesheetEntity.class);
//        TimesheetEntity updatedTimesheet = timesheetRepository.save(toUpdateTimesheet);
//        return modelMapper.map(updatedTimesheet, TimesheetDTO.class);
//
//    }

//    public TimesheetDTO deleteTimesheetById(Long timesheetId)
//    {
//        isExist(timesheetId);
//        TimesheetDTO timesheetToBeDeleted = modelMapper.map(timesheetRepository.findById(timesheetId)
//                                                                               .get(), TimesheetDTO.class);
//        timesheetRepository.deleteById(timesheetId);
//        return timesheetToBeDeleted;
//    }

    public TimesheetDTO updateTimesheet(TimesheetDTO timesheetDTO, Long timesheetId)
    {
        // 1. Fetch the existing entity (One DB call)
        TimesheetEntity existingEntity = timesheetRepository.findById(timesheetId)
                                                            .orElseThrow(() -> new ResourceNotFoundException("Timesheet not found with id: " + timesheetId));
        // 2. Update the entity with DTO data
        // This tells ModelMapper to copy fields from timesheetDTO INTO existingEntity
        modelMapper.map(timesheetDTO, existingEntity);
        // Ensure the ID stays the same as the path variable
        existingEntity.setTimesheetId(timesheetId);
        // 3. Save the changed entity
        TimesheetEntity updatedEntity = timesheetRepository.save(existingEntity);
        return modelMapper.map(updatedEntity, TimesheetDTO.class);
    }


    public TimesheetDTO deleteTimesheetById(Long timesheetId)
    {
        // Fetch first so we can return the data being deleted
        TimesheetEntity entity = timesheetRepository.findById(timesheetId)
                                                    .orElseThrow(() -> new ResourceNotFoundException("Cannot delete: Timesheet not found"));
        TimesheetDTO deletedData = modelMapper.map(entity, TimesheetDTO.class);
        timesheetRepository.deleteById(timesheetId);
        return deletedData;
    }
}
