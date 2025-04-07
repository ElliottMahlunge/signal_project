package com.cardio_generator.outputs;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ConcurrentHashMap;
/**
 * FileOutputStrategy writes patient data to separate text files based on data labels.
 * Each label corresponds to a separate file, which is appended with new data entries.
 * Ensures thread-safe access using a ConcurrentHashMap for file path management.
 */

//Renamed class and its usages throughout the project to FileOutputStrategy to match file name "PS i did this in a previous push because i needed to fix
//these issues to run the code.
public class FileOutputStrategy implements OutputStrategy {
    // Renamed BaseDirectory to baseDirectory to follow camelCase naming convention.
    private String baseDirectory;

    /**
     * Constructs a FileOutputStrategy with the specified base directory.
     *
     * @param baseDirectory The directory where output files will be saved
     */
    public final ConcurrentHashMap<String, String> file_map = new ConcurrentHashMap<>();

    public FileOutputStrategy(String baseDirectory) {

        this.baseDirectory = baseDirectory;
    }
    /**
     * Outputs the formatted data to a file corresponding to the data label.
     *
     * @param patientId The ID of the patient
     * @param timestamp The time of the data entry
     * @param label The data type label (e.g., "ECG")
     * @param data The data value to write
     */
    @Override
    public void output(int patientId, long timestamp, String label, String data) {
        try {
            // Create the directory
            Files.createDirectories(Paths.get(baseDirectory));
        } catch (IOException e) {
            System.err.println("Error creating base directory: " + e.getMessage());
            return;
        }
        // Set the FilePath variable
        String FilePath = file_map.computeIfAbsent(label, k -> Paths.get(baseDirectory, label + ".txt").toString());

        // Write the data to the file
        try (PrintWriter out = new PrintWriter(
                Files.newBufferedWriter(Paths.get(FilePath), StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            out.printf("Patient ID: %d, Timestamp: %d, Label: %s, Data: %s%n", patientId, timestamp, label, data);
        } catch (Exception e) {
            // Reworded error message comment to follow proper sentence formatting.
            System.err.println("Failed to write data for patient " + patientId);
        }
    }
}