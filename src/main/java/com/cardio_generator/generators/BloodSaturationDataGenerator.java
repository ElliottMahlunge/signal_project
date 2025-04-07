package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Generates simulated blood saturation values for patients.
 * The values fluctuate within a realistic range and simulate small variations
 * typically seen in real patients.
 */
public class BloodSaturationDataGenerator implements PatientDataGenerator {
    /**
     * A random number generator used for simulating fluctuations in saturation values.
     */
    private static final Random random = new Random();
    /**
     * Stores the last known saturation value for each patient.
     */
    private int[] lastSaturationValues;
    /**
     * Initializes the saturation values with a base value per patient.
     *
     * @param patientCount The number of patients to track
     */
    public BloodSaturationDataGenerator(int patientCount) {
        lastSaturationValues = new int[patientCount + 1];

        // Initialize with baseline saturation values for each patient
        for (int i = 1; i <= patientCount; i++) {
            lastSaturationValues[i] = 95 + random.nextInt(6); // Initializes with a value between 95 and 100
        }
    }
    /**
     * Generates and outputs new saturation data for a given patient.
     * The saturation value is allowed to fluctuate slightly while staying within safe bounds.
     *
     * @param patientId The patient identifier
     * @param outputStrategy The output method used for the data
     */
    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            // Simulate blood saturation values
            int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
            int newSaturationValue = lastSaturationValues[patientId] + variation;

            // Ensure the saturation stays within a realistic and healthy range
            newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
            lastSaturationValues[patientId] = newSaturationValue;
            outputStrategy.output(patientId, System.currentTimeMillis(), "Saturation",
                    Double.toString(newSaturationValue) + "%");
        } catch (Exception e) {
            System.err.println("An error occurred while generating blood saturation data for patient " + patientId);
            e.printStackTrace(); // This will print the stack trace to help identify where the error occurred.
        }
    }
}
