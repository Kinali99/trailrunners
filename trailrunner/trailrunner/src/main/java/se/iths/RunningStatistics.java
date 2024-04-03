package se.iths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RunningStatistics {

    private Map<String, Double> runDistances = new HashMap<>();
    private Map<String, Double> runTimes = new HashMap<>();

    public String createRun() {
        String runID = generateUniqueID();
        runDistances.put(runID, 0.0);
        runTimes.put(runID, 0.0);
        return runID;
    }

    public String createRunWithID(String customID) {
        if (runDistances.containsKey(customID)) {
            throw new IllegalArgumentException("Run ID already exists");
        }
        runDistances.put(customID, 0.0);
        runTimes.put(customID, 0.0);
        return customID;
    }

    public void recordRunDistance(String runID, double distanceInKilometers) {
        if (!runDistances.containsKey(runID)) {
            throw new IllegalArgumentException("Run ID does not exist");
        }
        runDistances.put(runID, runDistances.get(runID) + distanceInKilometers);
    }

    public void recordRunTime(String runID, double timeInHours) {
        if (!runTimes.containsKey(runID)) {
            throw new IllegalArgumentException("Run ID does not exist");
        }
        runTimes.put(runID, runTimes.get(runID) + timeInHours);
    }

    public double calculateAverageSpeed(String runID) {
        if (!runTimes.containsKey(runID) || runTimes.get(runID) == 0) {
            return 0.0;
        }
        return runDistances.get(runID) / runTimes.get(runID);
    }

    public double calculateKilometerTime(String runID) {
        if (!runDistances.containsKey(runID) || runDistances.get(runID) == 0) {
            return 0.0;
        }
        return runTimes.get(runID) / runDistances.get(runID);
    }

    public double calculateTotalDistance() {
        return runDistances.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateAverageDistance() {
        if (runDistances.isEmpty()) {
            return 0.0;
        }
        return calculateTotalDistance() / runDistances.size();
    }

    public String printRunDetails(String runID) {
        if (!runDistances.containsKey(runID) || !runTimes.containsKey(runID)) {
            throw new IllegalArgumentException("Run ID does not exist");
        }
    
        double distance = runDistances.get(runID);
        double time = runTimes.get(runID) * 60;  // Konvertera tid till minuter
    
        return String.format("Run ID: %s\nDistance: %.1f km\nTime: %.1f minutes", runID, distance, time)
                .replace(",", ".");
    }

    private String generateUniqueID() {
        String runID;
        do {
            runID = UUID.randomUUID().toString();
        } while (runDistances.containsKey(runID));
        return runID;
    }

    public void deleteRunById(String runID) {
        if (!runDistances.containsKey(runID) || !runTimes.containsKey(runID)) {
            throw new IllegalArgumentException("Run ID does not exist");
        }

        runDistances.remove(runID);
        runTimes.remove(runID);
    }
}



