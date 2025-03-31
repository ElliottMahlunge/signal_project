package com.cardio_generator.generators;

import java.util.Random;

import com.cardio_generator.outputs.OutputStrategy;

public class AlertGenerator implements PatientDataGenerator {

    public static final Random randomGenerator = new Random();
    // Renamed AlertStates to alertStates
    private boolean[] alertStates; // false = resolved, true = pressed

    public AlertGenerator(int patientCount) {
        alertStates = new boolean[patientCount + 1];
    }

    @Override
    public void generate(int patientId, OutputStrategy outputStrategy) {
        try {
            if (alertStates[patientId]) {
                if (randomGenerator.nextDouble() < 0.9) { // Modified comment to meet standards // There is a 90% chance that the alert will resolve in this cycle.
                    alertStates[patientId] = false;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "resolved");
                }
            } else {
                // Renamed Lambda to lambda to follow camelCase convention.
                double lambda = 0.1; // Average rate (alerts per period), adjust based on desired frequency
                double p = -Math.expm1(-lambda); //Expanded on a lacking comment // This calculates the probability of at least one alert being triggered in this cycle.
                boolean alertTriggered = randomGenerator.nextDouble() < p;

                if (alertTriggered) {
                    alertStates[patientId] = true;
                    // Output the alert
                    outputStrategy.output(patientId, System.currentTimeMillis(), "Alert", "triggered");
                }
            }
        } catch (Exception e) {
            // Logs an error message if alert generation fails for a patient.// Added an explanatory comment
            System.err.println("An error occurred while generating alert data for patient " + patientId);
            e.printStackTrace();
        }
    }
}
