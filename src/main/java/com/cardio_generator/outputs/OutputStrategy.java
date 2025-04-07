package com.cardio_generator.outputs;

/**
 * This is an Interface that defines how generated patient data should be returned.
 * Implementing classes can send data to different outputs like files or just the console.
 *
 * @author Elliott
 */

public interface OutputStrategy {

    /**
     * Outputs a line of data with patient info, a timestamp, a label, and the actual data.
     *
     * @param patientId The ID of the patient
     * @param timestamp The time when the data was generated
     * @param label A short name for the type of data (e.g., "HeartRate")
     * @param data The actual data value as a String
     */
    void output(int patientId, long timestamp, String label, String data);
}