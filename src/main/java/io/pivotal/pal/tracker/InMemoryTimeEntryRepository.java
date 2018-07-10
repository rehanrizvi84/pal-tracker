package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private Long timeEntrySeqid = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(timeEntrySeqid);
        timeEntries.put(timeEntry.getId(), timeEntry);
        timeEntrySeqid++;
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<TimeEntry>(timeEntries.values());
        return list;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }
}
