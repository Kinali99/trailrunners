package se.iths;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.Locale;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TrailRunnerTest {

    @Test
    void saveRun_ShouldCallCreateRecordWithCorrectParameters() {   //Användare skall kunna spara en löprunda med följande attribut:
        // Arrange
        DatabaseAPI databaseMock = mock(DatabaseAPI.class);
        TrailRunner trailRunner = new TrailRunner(databaseMock);

        double distance = 10.5;
        int hours = 1;
        int minutes = 30;
        int seconds = 45;
        LocalDate date = LocalDate.of(2024, 1, 8);

        // Act
        trailRunner.saveRun(distance, hours, minutes, seconds, date);

        // Assert
        verify(databaseMock).createRecord(anyString(), eq(distance), eq(5445), eq(date));
    }

    @Test // Användaren skall kunna ställa in sin längd (cm) och vikt (kg).
    public void createUser() {
        // Arrange
        double expectedLength = 175.0;  // Ange den förväntade längden
        double expectedWeight = 70.0;   // Ange den förväntade vikten

        // Act
        User user = new User(expectedLength, expectedWeight);

        // Assert
        assertEquals(expectedLength, user.getLength());
        assertEquals(expectedWeight, user.getWeight());
    }
    @Test // Användaren skall kunna beräkna sitt BMI (Body Mass Index) 
    public void calculateBMIForValidUser() {
        // Arrange
        double length = 175.0;  // Längd i cm
        double weight = 70.0;   // Vikt i kg
        double expectedBMI = weight / Math.pow(length / 100, 2);  // Beräknat BMI

        User user = new User(length, weight);

        // Act
        double calculatedBMI = user.calculateBMI();

        // Assert
        assertEquals(expectedBMI, calculatedBMI, 0.001);  // Använd 0.001 tolerans för jämförelse
    }
    @Test
    public void calculateAverageSpeedForValidRun() {
        // Arrange
        String runID = "testRun";
        double distanceInKilometers = 10.0;
        double timeInHours = 0.75;  // 45 minuter omvandlat till timmar

        RunningStatistics runningStats = new RunningStatistics();
        runningStats.createRunWithID(runID);
        runningStats.recordRunDistance(runID, distanceInKilometers);
        runningStats.recordRunTime(runID, timeInHours);

        // Act
        double calculatedAverageSpeed = runningStats.calculateAverageSpeed(runID);

        // Assert
        double expectedAverageSpeed = distanceInKilometers / timeInHours;
        assertEquals(expectedAverageSpeed, calculatedAverageSpeed, 0.001);
    }

    @Test
    public void calculateKilometerTimeForValidRun() {
        // Arrange
        String runID = "testRun";
        double distanceInKilometers = 10.0;
        double timeInMinutes = 45.0;

        RunningStatistics runningStats = new RunningStatistics();
        runningStats.createRunWithID(runID);
        runningStats.recordRunDistance(runID, distanceInKilometers);
        runningStats.recordRunTime(runID, timeInMinutes / 60);  // Omvandla tid till timmar

        // Act
        double calculatedKilometerTime = runningStats.calculateKilometerTime(runID);

        // Assert
        double expectedKilometerTime = timeInMinutes / 60 / distanceInKilometers;
        assertEquals(expectedKilometerTime, calculatedKilometerTime, 0.001);
    }
    
    @Test // Användare skall kunna spara en löprunda med följande attribut:
    public void createRunWithUniqueID() {
        // Arrange
        RunningStatistics runningStats = new RunningStatistics();

        // Act
        String run1ID = runningStats.createRun();
        String run2ID = runningStats.createRun();

        // Assert
        assertNotNull(run1ID);
        assertNotNull(run2ID);
        assertNotEquals(run1ID, run2ID);
    }

    @Test // Användare skall kunna spara en löprunda med följande attribut:
    public void preventDuplicateRunIDs() {
        // Arrange
        RunningStatistics runningStats = new RunningStatistics();

        // Act
        String runID = runningStats.createRun();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> runningStats.createRunWithID(runID));

        // Assert
        assertEquals("Run ID already exists", exception.getMessage());
    }
    @Test // Det skall gå att beräkna den totala distansen för ens sparade löprundor
    public void calculateTotalDistanceForRuns() {
        // Arrange
        RunningStatistics runningStats = new RunningStatistics();
        String run1ID = runningStats.createRun();
        String run2ID = runningStats.createRun();
        double distance1 = 5.0;
        double distance2 = 7.0;

        // Act
        runningStats.recordRunDistance(run1ID, distance1);
        runningStats.recordRunDistance(run2ID, distance2);

        // Assert
        assertEquals(distance1 + distance2, runningStats.calculateTotalDistance(), 0.001);
    }

    @Test // Det skall gå att beräkna medeldistansen för ens sparade löprundor
    public void calculateAverageDistanceForRuns() {
        // Arrange
        RunningStatistics runningStats = new RunningStatistics();
        String run1ID = runningStats.createRun();
        String run2ID = runningStats.createRun();
        double distance1 = 5.0;
        double distance2 = 7.0;

        // Act
        runningStats.recordRunDistance(run1ID, distance1);
        runningStats.recordRunDistance(run2ID, distance2);

        // Assert
        assertEquals((distance1 + distance2) / 2, runningStats.calculateAverageDistance(), 0.001);
    }
    @Test // Det skall gå att printa ut detaljerna för en löprunda genom att ange ett korrekt
    // identifikationsnummer
    public void printRunDetailsForValidRun() {
        // Arrange
        String runID = "testRun";
        double distanceInKilometers = 10.0;
        double timeInMinutes = 45.0;

        RunningStatistics runningStats = new RunningStatistics();
        runningStats.createRunWithID(runID);
        runningStats.recordRunDistance(runID, distanceInKilometers);
        runningStats.recordRunTime(runID, timeInMinutes / 60);  // Omvandla tid till timmar

        // Act
        String printedDetails = runningStats.printRunDetails(runID);
        

        // Assert
        String expectedDetails = "Run ID: testRun\nDistance: 10.0 km\nTime: 45.0 minutes";
        assertEquals(expectedDetails, printedDetails);
    }

    @Test // Det skall gå att radera en löprunda genom att ange dess identifikationsnummer.
public void deleteRunById_RemoveRunFromStatistics() {
    // Arrange
    RunningStatistics runningStats = new RunningStatistics();
    String runID = runningStats.createRun();
    
    // Act
    runningStats.deleteRunById(runID);

    // Assert
    assertThrows(IllegalArgumentException.class, () -> runningStats.printRunDetails(runID));
}

    @Test // Det skall gå att radera en löprunda genom att ange dess identifikationsnummer.
    public void deleteNonexistentRunById() {
        // Arrange
        RunningStatistics runningStats = new RunningStatistics();
        String nonExistentRunID = "nonExistentRun";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> runningStats.deleteRunById(nonExistentRunID));
    }
    @Test
    void createRun_ShouldAddRunToCollection() {
        // Arrange
        DatabaseAPI databaseMock = mock(DatabaseAPI.class);
        TrailRunner trailRunner = new TrailRunner(databaseMock);

        // Act
        String runID = trailRunner.createRun();

        // Assert
        assertTrue(trailRunner.containsRun(runID));
    }

    @Test
    void deleteRunById_ShouldRemoveRunFromCollection() {
        // Arrange
        DatabaseAPI databaseMock = mock(DatabaseAPI.class);
        TrailRunner trailRunner = new TrailRunner(databaseMock);
        String runID = trailRunner.createRun();

        // Act
        trailRunner.deleteRunById(runID);

        // Assert
        assertFalse(trailRunner.containsRun(runID));
    }

    @Test
    void containsRun_ShouldReturnTrueForExistingRun() {
        // Arrange
        DatabaseAPI databaseMock = mock(DatabaseAPI.class);
        TrailRunner trailRunner = new TrailRunner(databaseMock);
        String runID = trailRunner.createRun();

        // Act
        boolean containsRun = trailRunner.containsRun(runID);

        // Assert
        assertTrue(containsRun);
    }

    @Test
    void containsRun_ShouldReturnFalseForNonexistentRun() {
        // Arrange
        DatabaseAPI databaseMock = mock(DatabaseAPI.class);
        TrailRunner trailRunner = new TrailRunner(databaseMock);
        String runID = "nonexistentRun";

        // Act
        boolean containsRun = trailRunner.containsRun(runID);

        // Assert
        assertFalse(containsRun);
    }
    
}
    


