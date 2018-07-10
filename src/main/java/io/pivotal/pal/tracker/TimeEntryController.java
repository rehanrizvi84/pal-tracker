package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {


        return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

    }

    @GetMapping("/time-entries/{l}")
    public ResponseEntity<TimeEntry> read(@PathVariable long l) {
        TimeEntry timeEntry = timeEntryRepository.find(l);
        if(timeEntry != null)
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{l}")
    public ResponseEntity<TimeEntry> update(@PathVariable long l, @RequestBody TimeEntry expected) {
        TimeEntry timeEntry = timeEntryRepository.update(l, expected);

        if(timeEntry != null)
            return new ResponseEntity<>(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{l}")
    public ResponseEntity delete(@PathVariable long l) {
        timeEntryRepository.delete(l);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
