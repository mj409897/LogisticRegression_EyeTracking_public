package com.hsfresenius.M159_JM_Final.Statistics;

import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DescriptiveStatisticalAnalysis {

    // Eigenschaften:
    public SummaryStatistics summaryStatisticsA = new SummaryStatistics();
    public SummaryStatistics summaryStatisticsB = new SummaryStatistics();
    public SummaryStatistics summaryStatisticsC = new SummaryStatistics();
    public SummaryStatistics summaryStatisticsD = new SummaryStatistics();
    public SummaryStatistics summaryStatisticsE = new SummaryStatistics();
    public SummaryStatistics summaryStatisticsF = new SummaryStatistics();



    // Erzeuge Konstruktor:
    public DescriptiveStatisticalAnalysis() {
    }

    // Erzeuge Methoden:
    public String summaryStatsAfdFr(Map<Proband,Map<String,Double>> metricObjects2){

        // Zugriff auf Daten aus Methodeneingabe:
        Collection<Map<String, Double>> valueList = metricObjects2.values();

        // Eigenschaften der Methode:
        ArrayList<Double> valuesRegionA = new ArrayList<>();
        ArrayList<Double> valuesRegionB = new ArrayList<>();
        ArrayList<Double> valuesRegionC = new ArrayList<>();
        ArrayList<Double> valuesRegionD = new ArrayList<>();
        ArrayList<Double> valuesRegionE = new ArrayList<>();
        ArrayList<Double> valuesRegionF = new ArrayList<>();

        for (Map<String,Double> valueListObject : valueList) {
            for (String aoiRegion : valueListObject.keySet()) {
                switch (aoiRegion) {
                    case "A" ->{if(!valueListObject.get("A").isNaN()){ valuesRegionA.add(valueListObject.get("A"));}}
                    case "B" ->{if(!valueListObject.get("B").isNaN()){ valuesRegionB.add(valueListObject.get("B"));}}
                    case "C" ->{if(!valueListObject.get("C").isNaN()){ valuesRegionC.add(valueListObject.get("C"));}}
                    case "D" ->{if(!valueListObject.get("D").isNaN()){ valuesRegionD.add(valueListObject.get("D"));}}
                    case "E" ->{if(!valueListObject.get("E").isNaN()){ valuesRegionE.add(valueListObject.get("E"));}}
                    case "F" ->{if(!valueListObject.get("F").isNaN()){ valuesRegionF.add(valueListObject.get("F"));}}
                }
            }
        }

        for (Double value : valuesRegionA) {
            this.summaryStatisticsA.addValue(value);
        }
        for (Double value : valuesRegionB) {
            this.summaryStatisticsB.addValue(value);
        }
        for (Double value : valuesRegionC) {
            this.summaryStatisticsC.addValue(value);
        }
        for (Double value : valuesRegionD) {
            this.summaryStatisticsD.addValue(value);
        }
        for (Double value : valuesRegionE) {
            this.summaryStatisticsE.addValue(value);
        }
        for (Double value : valuesRegionF) {
            this.summaryStatisticsF.addValue(value);
        }

        return "AOI-Region A: " + "\n"
                + summaryStatisticsA.toString() + "\n"
                + "AOI-Region B: " + "\n"
                + summaryStatisticsB.toString() + "\n"
                + "AOI-Region C: " + "\n"
                + summaryStatisticsC.toString() + "\n"
                + "AOI-Region D: " + "\n"
                + summaryStatisticsD.toString() + "\n"
                + "AOI-Region E: " + "\n"
                + summaryStatisticsE.toString() + "\n"
                + "AOI-Region F: " + "\n"
                + summaryStatisticsF.toString();
    }



    public SummaryStatistics summaryStatsFsd(Map<Proband,Double> metricObjects1) {

        // Methodeneigene Eigenschaften:
        SummaryStatistics summaryStatistics = new SummaryStatistics(); // Objekt zur Berechnung der deskriptiven Statistik
        ArrayList<Double> values = new ArrayList<>();
        for (double i : metricObjects1.values()) {
            if (!Double.isNaN(i)){
                values.add(i);
            }
        }

        for (Double value : values) {
            summaryStatistics.addValue(value);
        }
        return summaryStatistics;
    }


}
