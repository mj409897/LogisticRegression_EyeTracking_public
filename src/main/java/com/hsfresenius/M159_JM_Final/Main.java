package com.hsfresenius.M159_JM_Final;


import com.hsfresenius.M159_JM_Final.DataPreparation.LoadingTSV;
import com.hsfresenius.M159_JM_Final.ET_metrics.AverageFixationDuration;
import com.hsfresenius.M159_JM_Final.ET_metrics.FixationRate;
import com.hsfresenius.M159_JM_Final.ET_metrics.FixationSpatialDensity;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Probands;
import com.hsfresenius.M159_JM_Final.SavingData.DescriptiveStatisticsToCSV;
import com.hsfresenius.M159_JM_Final.StatisticalEvaluation.BinomialLogisticRegression;
import com.hsfresenius.M159_JM_Final.StatisticalEvaluation.HistogrammPlot;
import com.hsfresenius.M159_JM_Final.Statistics.DescriptiveStatisticalAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main (String[] args) throws InterruptedException {

        // Teil 1:
        // Erzeuge Objekt für das Einladen einer Datei:
        LoadingTSV file1;
        LoadingTSV file2;
        // Angabe des Dateipfads:
        String path_to_data1 = "data/Fixations/Fixations.tsv";
        String path_to_data2 = "data/Fixations/Rectangle-OK-1920-1080/54_RO.tsv";

        // Einladen einer Datei
        file1 = new LoadingTSV(path_to_data1);
        file2 = new LoadingTSV(path_to_data2);

        // Einlesen der Daten
        // Name:
        String name1 = file1.read_tsv_name();
        String name2 = file2.read_tsv_name();
        // Fixation Durations:
        ArrayList<Integer> fixationDurations_Liste1 = file1.read_tsv_fixationDurations();
        ArrayList<Integer> fixationDurations_Liste2 = file2.read_tsv_fixationDurations();
        // Fixations Pos X:
        ArrayList<Integer> fixationsPosX_Liste1 = file1.read_tsv_fixationsPosX();
        ArrayList<Integer> fixationsPosX_Liste2 = file2.read_tsv_fixationsPosX();
        // Fixations Pos Y:
        ArrayList<Integer> fixationsPosY_Liste1 = file1.read_tsv_fixationsPosY();
        ArrayList<Integer> fixationsPosY_Liste2 = file2.read_tsv_fixationsPosY();

        // Erzeuge Objekt eines Probanden mit den Angaben aus der eingelesenen Datei:
        Proband p1 = new Proband(name1,
                      fixationDurations_Liste1,
                      fixationsPosX_Liste1,
                      fixationsPosY_Liste1);
        Proband p2 = new Proband(name2,
                        fixationDurations_Liste2,
                        fixationsPosX_Liste2,
                        fixationsPosY_Liste2);

        // Erzeuge Objekt der Metrik AverageFixationDuration mit dem Probanden p1 und p2:
        Map<String,Double> testAfdP1 = new AverageFixationDuration().calcAfdForOneProband(p1,"RO");
        Map<String,Double> testAfdP2 = new AverageFixationDuration().calcAfdForOneProband(p2,"RO");

        // Erzeuge Objekt der Metrik FixationRate mit dem Probanden p1 und p2:
        Map<String, Double> testFrP1 = new FixationRate().calcFrForOneProband(p1,"RO");
        Map<String, Double> testFrP2 = new FixationRate().calcFrForOneProband(p2,"RO");


        // Erzeuge Objekt der Metrik FixationSpatialDensity mit dem Probanden p1 und p2:
        double testFsdP1 = new FixationSpatialDensity().calcFsdForOneProband(p1);
        double testFsdP2 = new FixationSpatialDensity().calcFsdForOneProband(p2);

        // Darstellung der Berechnungen:
        //System.out.println(testAfdP1);
        //System.out.println(testAfdP2);




        // Teil 2:
        // Berechne Metriken für jeden Probanden:

            // Erzeuge Listen der Probanden:
        ArrayList<Proband> probandsRO = new Probands().accessProbands("RO");
        ArrayList<Proband> probandsRS = new Probands().accessProbands("RS");
        ArrayList<Proband> probandsVO = new Probands().accessProbands("VO");
        ArrayList<Proband> probandsVS = new Probands().accessProbands("VS");

            // Berechnung Average Fixation Duration (AFD):
            // Erzeuge ein Objekt zur Berechnung und Ablage der Average Fixation Duration Metrik:
                    // Für Probanden in ArrayList probandsRO:
        AverageFixationDuration afdRO = new AverageFixationDuration();
        afdRO.setAfdList(probandsRO,"RO"); // Berechnet die afd-Werte und fügt sie der Liste mit den Werten der Probanden der Gruppe RO hinzu.
                    // Für Probanden in ArrayList probandsRS:
        AverageFixationDuration afdRS = new AverageFixationDuration();
        afdRS.setAfdList(probandsRS,"RS");
                    // Für Probanden in ArrayList probandsVO:
        AverageFixationDuration afdVO = new AverageFixationDuration();
        afdVO.setAfdList(probandsVO,"VO");
                    // Für Probanden in ArrayList probandsVS:
        AverageFixationDuration afdVS = new AverageFixationDuration();
        afdVS.setAfdList(probandsVS,"VS");

            // Berechnung Fixation Rate (FR):
            // Erzeuge ein Objekt zur Berechnung und Ablage der Fixation Rate Metrik:
                    //Für Probanden in ArrayList probandsRO:
        FixationRate frRO = new FixationRate();
        frRO.setFrList(probandsRO,"RO"); // Berechnet die fr-Werte und fügt sie der Liste mit den Werten der Probanden der Gruppe RO hinzu.
                    // Für Probanden in ArrayList probandsRS:
        FixationRate frRS = new FixationRate();
        frRS.setFrList(probandsRS,"RS");
                    // Für Probanden in ArrayList probandsVO:
        FixationRate frVO = new FixationRate();
        frVO.setFrList(probandsVO,"VO");
                    // Für Probanden in ArrayList probandsVS:
        FixationRate frVS = new FixationRate();
        frVS.setFrList(probandsVS,"VS");


            // Berechnung Fixation Spatial Density (FSD):
            // Erzeuge ein Objekt zur Berechnung und Ablage der Fixation Spatial Density Metrik:
                //Für Probanden in ArrayList probandsRO:
        FixationSpatialDensity fsdRO = new FixationSpatialDensity();
        fsdRO.setFsdList(probandsRO); // Berechnet die fsd-Werte und fügt sie der Liste mit den Werten der Probanden der Gruppe RO hinzu.
                // Für Probanden in ArrayList probandsRS:
        FixationSpatialDensity fsdRS = new FixationSpatialDensity();
        fsdRS.setFsdList(probandsRS);
                // Für Probanden in ArrayList probandsVO:
        FixationSpatialDensity fsdVO = new FixationSpatialDensity();
        fsdVO.setFsdList(probandsVO);
                // Für Probanden in ArrayList probandsVS:
        FixationSpatialDensity fsdVS = new FixationSpatialDensity();
        fsdVS.setFsdList(probandsVS);



        // Teil 3:
        // Führe statistische Berechnungen aus:

            // Für die Gruppe der Teilnehmer an RO-VS:
                // Deskriptive Statistik der Fixation Spatial Density (FSD):
        Map<Proband, Double> fsdROList = fsdRO.getFsdList();
        Map<Proband, Double> fsdVSList = fsdVS.getFsdList();
        Map<Proband, Double> fsdRSList = fsdRS.getFsdList();
        Map<Proband, Double> fsdVOList = fsdVO.getFsdList();

        DescriptiveStatisticalAnalysis statsFsd = new DescriptiveStatisticalAnalysis();
        System.out.println("Fixation Spatial Density für Code Rectangle OK (RO): " + statsFsd.summaryStatsFsd(fsdROList));
        System.out.println("Fixation Spatial Density für Code Vehicle Smell (VS): " + statsFsd.summaryStatsFsd(fsdVSList));
        System.out.println("Fixation Spatial Density für Code Rectangle Smell (RS): " + statsFsd.summaryStatsFsd(fsdRSList));
        System.out.println("Fixation Spatial Density für Code Vehicle OK (VO): " + statsFsd.summaryStatsFsd(fsdVOList));

                //Deskriptive Statistik der Average Fixation Duration (AFD):
        Map<Proband, Map<String, Double>> afdROList = afdRO.getAfdList();
        Map<Proband, Map<String, Double>> afdVSList = afdVS.getAfdList();
        Map<Proband, Map<String, Double>> afdRSList = afdRS.getAfdList();
        Map<Proband, Map<String, Double>> afdVOList = afdVO.getAfdList();

        DescriptiveStatisticalAnalysis statsAfd = new DescriptiveStatisticalAnalysis();
        System.out.println("Average Fixation Duration für Code Rectangle OK (RO): " + statsAfd.summaryStatsAfdFr(afdROList));
        System.out.println("Average Fixation Duration für Code Vehicle Smell (VS): " + statsAfd.summaryStatsAfdFr(afdVSList));
        System.out.println("Average Fixation Duration für Code Rectangle Smell (RS): " + statsAfd.summaryStatsAfdFr(afdRSList));
        System.out.println("Average Fixation Duration für Code Vehicle OK (VO): " + statsAfd.summaryStatsAfdFr(afdVOList));

            //Deskriptive Statistik der Fixation Rate (FR):
        Map<Proband, Map<String, Double>> frROList = frRO.getFrList();
        Map<Proband, Map<String, Double>> frVSList = frVS.getFrList();
        Map<Proband, Map<String, Double>> frRSList = frRS.getFrList();
        Map<Proband, Map<String, Double>> frVOList = frVO.getFrList();

        DescriptiveStatisticalAnalysis statsFr = new DescriptiveStatisticalAnalysis();
        System.out.println("Fixation Rate für Code Rectangle OK (RO): " + statsFr.summaryStatsAfdFr(frROList));
        System.out.println("Fixation Rate für Code Vehicle Smell (VS): " + statsFr.summaryStatsAfdFr(frVSList));
        System.out.println("Fixation Rate für Code Rectangle Smell (RS): " + statsFr.summaryStatsAfdFr(frRSList));
        System.out.println("Fixation Rate für Code Vehicle OK (VO): " + statsFr.summaryStatsAfdFr(frVOList));

        // Speichere die Statistik in einem csv-File:

        DescriptiveStatisticsToCSV allFsdStats = new DescriptiveStatisticsToCSV();
        try {
            allFsdStats.createCSVfromFSD(statsFsd.summaryStatsFsd(fsdROList),
                                         statsFsd.summaryStatsFsd(fsdVSList),
                                         statsFsd.summaryStatsFsd(fsdRSList),
                                         statsFsd.summaryStatsFsd(fsdVOList),
                                "data/SavedDataFiles/FsdDescriptiveStats.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Statistische Auswertung mit unabhängiger und abhängigen Variablen:
                // Trainingdatensätze: RO und VS ,Testdatensätze: RS und VO
                // unabhängige Variablen: Code Smell --> 1 / Code OK -> 0
                // abhängige Variablen: FR,AFD,FSD

        // Erzeuge CsvTabelle mit den relevanten Parametern für die logistic Regression Analyse:
        BinomialLogisticRegression train = new BinomialLogisticRegression();
        BinomialLogisticRegression test = new BinomialLogisticRegression();
        try {
            train.createCSVforBLRegression("RO","RS","data/SavedDataFiles/TrainData.csv");
            System.out.println("TrainCSV-File erstellt!");
            test.createCSVforBLRegression("VO","VS","data/SavedDataFiles/TestData.csv");
            System.out.println("TestCSV-File erstellt!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Führe BLR durch:
            // Hierbei ist es gleich, welches BLR-Objekt genutzt wird.
            // Die Pfade zu den zuvor erstellten Dateien müssen angegeben werden.
            // Die Methode "calcBinomialLogisticRegression" ist unabhängig von der Methode
            // "createCSVforBLRegression" .

        System.out.println(test.calcBinomialLogisticRegression(5,
                                                                2,
                                                           5,
                              "data/SavedDataFiles/TestData.csv",
                            "data/SavedDataFiles/TrainData.csv"));


        // Erstelle Histogramm:
            // Für FixationSpatialDensity in der Gruppe RO:
        HistogrammPlot histRO = new HistogrammPlot(); // Initiiere Objekt der Klasse HistogrammPlot.
        String histPlotFsdRO = histRO.showHistogram(fsdROList,5); // Nutzt Methode showHistogramm der Klasse HistogrammPlot
        System.out.println("\n" + "FSD RO" + "\n" +  histPlotFsdRO);

        // Für FixationSpatialDensity in der Gruppe VS:
        HistogrammPlot histVS = new HistogrammPlot(); // Initiiere Objekt der Klasse HistogrammPlot.
        String histPlotFsdVS = histVS.showHistogram(fsdVSList,5); // Nutzt Methode showHistogramm der Klasse HistogrammPlot
        System.out.println("\n" + "FSD VS"  + "\n" +  histPlotFsdVS);

        // Für FixationSpatialDensity in der Gruppe RS:
        HistogrammPlot histRS = new HistogrammPlot(); // Initiiere Objekt der Klasse HistogrammPlot.
        String histPlotFsdRS = histRS.showHistogram(fsdRSList,5); // Nutzt Methode showHistogramm der Klasse HistogrammPlot
        System.out.println("\n" + "FSD RS"  + "\n" + histPlotFsdRS);

        // Für FixationSpatialDensity in der Gruppe VO:
        HistogrammPlot histVO = new HistogrammPlot(); // Initiiere Objekt der Klasse HistogrammPlot.
        String histPlotFsdVO = histVO.showHistogram(fsdVOList,5); // Nutzt Methode showHistogramm der Klasse HistogrammPlot
        System.out.println("\n" + "FSD VO" + "\n" + histPlotFsdVO);

    }
}
