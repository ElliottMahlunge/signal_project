package com.cardio_generator.generators;

import com.cardio_generator.outputs.OutputStrategy;

/**
 * Interface for generating data for a specific patient.
 * Any class that implements this must define how the data is generated.
 * Used in the project for things like generating heart rate, saturation, alerts, etc.
 *
 * @author Elliott
 */
public interface PatientDataGenerator {

    /**
     * Generates and outputs data for a given patient using the selected output method.
     *
     * @param patientId The unique ID of the patient
     * @param outputStrategy The method used to send or display the data
     */
    void generate(int patientId, OutputStrategy outputStrategy);
}