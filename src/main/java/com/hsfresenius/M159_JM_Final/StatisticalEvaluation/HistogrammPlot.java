package com.hsfresenius.M159_JM_Final.StatisticalEvaluation;

import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;
import com.hsfresenius.M159_JM_Final.Statistics.DescriptiveStatisticalAnalysis;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.*;

public class HistogrammPlot {

    // Konstruktor:
    public HistogrammPlot() {
    }

    // Methode:

    public String showHistogram(Map<Proband, Double> fsdList, int numOfClasses) {
        // Nimmt die fsdValues des Probanden in einer Liste auf.
        ArrayList<Double> fsdValues = new ArrayList<>(fsdList.values());
        // Initiiert Objekt um auf Statistik Werte zugreifen zu können.
        DescriptiveStatisticalAnalysis stats = new DescriptiveStatisticalAnalysis();
        SummaryStatistics statistics = stats.summaryStatsFsd(fsdList);
        // Greift die relevanten Werte für die Klassenbildung eines Histogramms aus den Statistiken ab:
        double max = statistics.getMax();
        double min = statistics.getMin();
        double range = max - min;

        // Klasseneigenschaften:
        double widthOfClasses = range / (numOfClasses);

        // Initiiere Variablen für die Klassengrenzen und die Häufigkeiten:
        ArrayList<Double> classLimits = new ArrayList<>();
        Map<ArrayList<Double>,Integer> classFrequencies = new HashMap<>();

        // Loop zwischen min und max um die Klassengrenzen anhand der berechneten Klassenbreite festzulegen:
        for (double i = min; i <= max; i+= widthOfClasses) {
            classLimits.add((double) Math.round(i*10000)/(10000));
        }

        // Loop durch die Klassenlimits um Listen mit der unteren und oberen Grenze jeder Klasse zu erstellen:
        for (int i = 0; i < (classLimits.size()-1); i++) {
            double lowerLimit = classLimits.get(i);
            double upperLimit = classLimits.get(i+1);
            ArrayList<Double> classLimitsTemp = new ArrayList<>(); // Speichert ein Paar Klassengrenzen um sie dem Map objekt hinzuzufügen.

            int counter = 0; // Zähler für die Häufigkeit eines Wertes innerhalb einer Klasse;

            for (Double fsdValue : fsdValues) {
                if ((fsdValue < upperLimit && fsdValue >= lowerLimit)
                        || (fsdValue.equals(classLimits.get(classLimits.size() - 1)))) {
                    counter += 1;
                }
            }
            classLimitsTemp.add(lowerLimit);//(double) Math.round(lowerLimit*10000)/(10000));
            classLimitsTemp.add(upperLimit);//(double) Math.round(upperLimit*10000)/(10000));
            classFrequencies.put(classLimitsTemp,counter);
        }


        // Darstellung des Histogramms:
        String histogram = "--------------------Histogramm Plot-------------------";
        // For-Schleife zur Sortierung der Klassengrenzen in der Reihenfolge Niedrigste - Höchste:
        for (int i =0; i < (classLimits.size()-1); i++) {
            for (ArrayList<Double> key: classFrequencies.keySet()) {
                if (key.get(0).equals(classLimits.get(i))){
                    String histo = String.valueOf((key));
                    Integer freqCount = classFrequencies.get(key);
                    String frequency = "";
                    for (int j = 0; j < freqCount; j++) {
                        frequency += "*";
                    }
                    histogram += "\n" + histo + ":  " + frequency;
                }
            }
        }
        return histogram;
    }

}
