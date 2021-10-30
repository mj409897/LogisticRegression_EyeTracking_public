package com.hsfresenius.M159_JM_Final.SavingData;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DescriptiveStatisticsToCSV {


    // Konstruktor:
    public DescriptiveStatisticsToCSV() {
    }

    // Methoden:
    public void createCSVfromFSD(SummaryStatistics fsdROList,
                                 SummaryStatistics fsdVSList,
                                 SummaryStatistics fsdRSList,
                                 SummaryStatistics fsdVOList,
                                 String pathname) throws IOException {

        // Variablen für FixationSpatialDensity

        // Zur Zwischenspeicherung der statistischen Kennwerte:
        ArrayList<Double> statsRO = new ArrayList<>();
        ArrayList<Double> statsVS = new ArrayList<>();
        ArrayList<Double> statsRS = new ArrayList<>();
        ArrayList<Double> statsVO = new ArrayList<>();

        // Zusammenfügen in eine List zur Erleichterung der .csv - Dateierzeugung:
        ArrayList<ArrayList<Double>> allStats = new ArrayList<>();
        allStats.add(statsRO);
        allStats.add(statsVS);
        allStats.add(statsRS);
        allStats.add(statsVO);

        // Hinzufügen der stat. Kennwerte zu den zuvor erzeugten  Listen:
        // Für RO:
        statsRO.add((double) fsdROList.getN());
        statsRO.add(fsdROList.getMax());
        statsRO.add(fsdROList.getMin());
        statsRO.add(fsdROList.getMean());
        statsRO.add(fsdROList.getGeometricMean());
        statsRO.add(fsdROList.getVariance());
        statsRO.add(fsdROList.getStandardDeviation());

        // Für VS:
        statsVS.add((double) fsdVSList.getN());
        statsVS.add(fsdVSList.getMax());
        statsVS.add(fsdVSList.getMin());
        statsVS.add(fsdVSList.getMean());
        statsVS.add(fsdVSList.getGeometricMean());
        statsVS.add(fsdVSList.getVariance());
        statsVS.add(fsdVSList.getStandardDeviation());


        // Für RS:
        statsRS.add((double) fsdRSList.getN());
        statsRS.add(fsdRSList.getMax());
        statsRS.add(fsdRSList.getMin());
        statsRS.add(fsdRSList.getMean());
        statsRS.add(fsdRSList.getGeometricMean());
        statsRS.add(fsdRSList.getVariance());
        statsRS.add(fsdRSList.getStandardDeviation());

        // Für VO:
        statsVO.add((double) fsdVOList.getN());
        statsVO.add(fsdVOList.getMax());
        statsVO.add(fsdVOList.getMin());
        statsVO.add(fsdVOList.getMean());
        statsVO.add(fsdVOList.getGeometricMean());
        statsVO.add(fsdVOList.getVariance());
        statsVO.add(fsdVOList.getStandardDeviation());

        // Initiiere Objekt zur Erstellung einer Textdatei:
        File file = new File(pathname);
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Befülle Textdatei
        bufferedWriter.write("Parameter" + ","
                                +"RO" + ","
                                +"VS" + ","
                                +"RS" + ","
                                +"VO");
        bufferedWriter.newLine();
        bufferedWriter.write("N" + ","
                                + allStats.get(0).get(0) + ","
                                + allStats.get(1).get(0) + ","
                                + allStats.get(2).get(0) + ","
                                + allStats.get(3).get(0));
        bufferedWriter.newLine();
        bufferedWriter.write("Max" + ","
                                + allStats.get(0).get(1) + ","
                                + allStats.get(1).get(1) + ","
                                + allStats.get(2).get(1) + ","
                                + allStats.get(3).get(1));
        bufferedWriter.newLine();
        bufferedWriter.write("Min" + ","
                                + allStats.get(0).get(2) + ","
                                + allStats.get(1).get(2) + ","
                                + allStats.get(2).get(2) + ","
                                + allStats.get(3).get(2));
        bufferedWriter.newLine();
        bufferedWriter.write("Mean" + ","
                                + allStats.get(0).get(3) + ","
                                + allStats.get(1).get(3) + ","
                                + allStats.get(2).get(3) + ","
                                + allStats.get(3).get(3));
        bufferedWriter.newLine();
        bufferedWriter.write("Median" + ","
                                + allStats.get(0).get(4) + ","
                                + allStats.get(1).get(4) + ","
                                + allStats.get(2).get(4) + ","
                                + allStats.get(3).get(4));
        bufferedWriter.newLine();
        bufferedWriter.write("Variance" + ","
                                + allStats.get(0).get(5) + ","
                                + allStats.get(1).get(5) + ","
                                + allStats.get(2).get(5) + ","
                                + allStats.get(3).get(5));
        bufferedWriter.newLine();
        bufferedWriter.write("StdDev" + ","
                                + allStats.get(0).get(6) + ","
                                + allStats.get(1).get(6) + ","
                                + allStats.get(2).get(6) + ","
                                + allStats.get(3).get(6));
        bufferedWriter.newLine();

        bufferedWriter.close();
        fileWriter.close();

    }

}
