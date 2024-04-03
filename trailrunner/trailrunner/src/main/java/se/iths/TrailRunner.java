package se.iths;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrailRunner {

    private final DatabaseAPI database;
    private final List<String> localRuns = new ArrayList<>();

    public TrailRunner(DatabaseAPI database) {
        this.database = database;
    }

    public String createRun() {
        String runID = generateLocalUniqueID();
        localRuns.add(runID);
        return runID;
    }

    public void deleteRunById(String runID) {
        if (!localRuns.contains(runID)) {
            throw new IllegalArgumentException("Run ID does not exist");
        }

        localRuns.remove(runID);
    }

    public boolean containsRun(String runID) {
        return localRuns.contains(runID);
    }

    public Collection<String> getRuns() {
        return new ArrayList<>(localRuns);
    }

    public void saveRun(double distance, int hours, int minutes, int seconds, LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }

        int totalTimeInSeconds = hours * 3600 + minutes * 60 + seconds;
        String id = generateRunId();

        // Save to local storage
        localRuns.add(id);

        // Save to database
        database.createRecord(id, distance, totalTimeInSeconds, date);
    }

    private String generateRunId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String generateLocalUniqueID() {
        return String.valueOf(System.currentTimeMillis());
    }
}
