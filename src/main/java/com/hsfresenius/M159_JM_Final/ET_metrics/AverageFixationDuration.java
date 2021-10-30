package com.hsfresenius.M159_JM_Final.ET_metrics;

import com.hsfresenius.M159_JM_Final.DataPreparation.AoiCoordinates;
import com.hsfresenius.M159_JM_Final.DataPreparation.AreaOfInterestCSV;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageFixationDuration {
    // Die Average Fixation Duration (AFD) weist
    // mit steigendem Wert auf eine Zunahme des Aufwands bzw. einen steigenden Schwierigkeitsgrad
    // im Verständnis des betrachteten Objekts hin. Sie lässt sich aus dem Quotienten der Summe der
    // Dauer aller Fixierungen in einer AOI und der Anzahl der Fixierungen (n) berechnen.

    // Deklariere die Eigenschaften der Klasse:
    private Map<String, List<AoiCoordinates>> aoiRegions;
    private final Map<String,Double> valuesAfdForOneProband = new HashMap<>(); // Enthält für jede AOI-Region die AFD-Werte eines Probanden
    private final Map<Proband,Map<String,Double>> valuesAfdForAllProbands = new HashMap<>(); // Enthält für alle Probanden einer Gruppe (RO,VS,RS,VO) die AFD-Werte für jede AOI-Region.


    // Erstelle Konstruktor
    public AverageFixationDuration() {

    }


    // Erstelle Methoden:
        // Berechnung der Metrik AverageFixationDuration (afd):
    public Map<String, Double> calcAfdForOneProband(Proband proband, String aoiPathDeclaration) {

        // Initiiere die für die Berechnung notwendigen Variablen:
        AreaOfInterestCSV aoi = new AreaOfInterestCSV();

        // Verändere den Pfad in Abhängikeit von der Eingabe des Pfadnamens in der Eingabe der Methode calcAfdForOneProband
        switch (aoiPathDeclaration) {
            case "RO" -> this.aoiRegions = aoi.readData("RO");
            case "RS" -> this.aoiRegions = aoi.readData("RS");
            case "VO" -> this.aoiRegions = aoi.readData("VO");
            case "VS" -> this.aoiRegions = aoi.readData("VS");
        }

        // Initiiere Listen um die Länge jeder Fixierungen in einer AOI-Region abzuspeichern.
        ArrayList<Integer> regionA_FixationsDurations = new ArrayList<>();
        ArrayList<Integer> regionB_FixationsDurations = new ArrayList<>();
        ArrayList<Integer> regionC_FixationsDurations = new ArrayList<>();
        ArrayList<Integer> regionD_FixationsDurations = new ArrayList<>();
        ArrayList<Integer> regionE_FixationsDurations = new ArrayList<>();
        ArrayList<Integer> regionF_FixationsDurations = new ArrayList<>();



            // Initiiere Listen zur Aufnahme der Koordinaten.

        List<AoiCoordinates> aoiRegionCoordinatesA = aoiRegions.get("A");
        List<AoiCoordinates> aoiRegionCoordinatesB = aoiRegions.get("B");
        List<AoiCoordinates> aoiRegionCoordinatesC = aoiRegions.get("C");
        List<AoiCoordinates> aoiRegionCoordinatesD = aoiRegions.get("D");
        List<AoiCoordinates> aoiRegionCoordinatesE = aoiRegions.get("E");
        List<AoiCoordinates> aoiRegionCoordinatesF = aoiRegions.get("F");

            // Loop durch alle Fixierungen des Probanden um sie einer AOI-Region zuzuordnen:
        for (int i = 0; i < proband.fixationsPosX.size(); i++) {
                // Loop durch alle AOI-Koordinaten der Region A um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesA) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionA_FixationsDurations.add(proband.fixationDurations.get(i));
                }
            }
                // Loop durch alle AOI-Koordinaten der Region B um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesB) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionB_FixationsDurations.add(proband.fixationDurations.get(i));
                }

            }
                // Loop durch alle AOI-Koordinaten der Region C um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesC) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionC_FixationsDurations.add(proband.fixationDurations.get(i));
                }

            }
                // Loop durch alle AOI-Koordinaten der Region D um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesD) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionD_FixationsDurations.add(proband.fixationDurations.get(i));
                }

            }
                // Loop durch alle AOI-Koordinaten der Region E um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesE) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionE_FixationsDurations.add(proband.fixationDurations.get(i));
                }

            }
                // Loop durch alle AOI-Koordinaten der Region F um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesF) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionF_FixationsDurations.add(proband.fixationDurations.get(i));
                }

            }

        }

            // Berechnung der Average Fixation Duration für jede AOI-Region:
            // Region A:
        int countOfFixationsInAOIRegionA = regionA_FixationsDurations.size();
        double sumOfDurationsInAOIRegionA = regionA_FixationsDurations.stream().mapToDouble(regionA_fixationsDuration -> regionA_fixationsDuration).sum();
        double valueAfdA = sumOfDurationsInAOIRegionA/countOfFixationsInAOIRegionA; // Berechnug der AverageFixationDuration
        valuesAfdForOneProband.put("A",valueAfdA);

            // Region B:
        int countOfFixationsInAOIRegionB = regionB_FixationsDurations.size();
        double sumOfDurationsInAOIRegionB = regionB_FixationsDurations.stream().mapToDouble(regionB_fixationsDuration -> regionB_fixationsDuration).sum();
        double valueAfdB = sumOfDurationsInAOIRegionB/countOfFixationsInAOIRegionB; // Berechnung der AverageFixationDuration
        valuesAfdForOneProband.put("B",valueAfdB);

            // Region C:
        int countOfFixationsInAOIRegionC = regionC_FixationsDurations.size();
        double sumOfDurationsInAOIRegionC = regionC_FixationsDurations.stream().mapToDouble(regionC_fixationsDuration -> regionC_fixationsDuration).sum();
        double valueAfdC = sumOfDurationsInAOIRegionC/countOfFixationsInAOIRegionC; // Berechnung der AverageFixationDuration
        valuesAfdForOneProband.put("C",valueAfdC);

            // Region D:
        int countOfFixationsInAOIRegionD = regionD_FixationsDurations.size();
        double sumOfDurationsInAOIRegionD = regionD_FixationsDurations.stream().mapToDouble(regionD_fixationsDuration -> regionD_fixationsDuration).sum();
        double valueAfdD = sumOfDurationsInAOIRegionD/countOfFixationsInAOIRegionD; // Berechnung der AverageFixationDuration
        valuesAfdForOneProband.put("D",valueAfdD);

            // Region E:

        int countOfFixationsInAOIRegionE = regionE_FixationsDurations.size();
        double sumOfDurationsInAOIRegionE = regionE_FixationsDurations.stream().mapToDouble(regionE_fixationsDuration -> regionE_fixationsDuration).sum();
        double valueAfdE = sumOfDurationsInAOIRegionE/countOfFixationsInAOIRegionE; // Berechnung der AverageFixationDuration
        valuesAfdForOneProband.put("E",valueAfdE);

            // Region F:
        int countOfFixationsInAOIRegionF = regionF_FixationsDurations.size();
        double sumOfDurationsInAOIRegionF = regionF_FixationsDurations.stream().mapToDouble(regionF_fixationsDuration -> regionF_fixationsDuration).sum();
        double valueAfdF = sumOfDurationsInAOIRegionF/countOfFixationsInAOIRegionF; // Berechnug der AverageFixationDuration
        valuesAfdForOneProband.put("F",valueAfdF);



        return valuesAfdForOneProband;
    }


    // Erstelle Setter-Methode:

        // Nimmt probands-Objekt sowie die Gruppe zu der die AOI Regionen gehören ("RO","VS","RS","VO").
        // Für jeden Probanden und jede AOI-Region (A-F) werden die AFD-Werte berechnet und in einem gemeinsamen Map Objekt abgelegt.
        // So wird ein HashMap Objekt erstellt, welche für jeden Probanden alle Ergebnisse enthält.

    public void setAfdList (ArrayList<Proband> probands, String aoiPathDeclaration){
        // Loop durch die angegebene Probandenliste
        for (Proband proband : probands) {
            AverageFixationDuration tempAfdObject = new AverageFixationDuration();// Erzeugt ein temporäres afd Objekt für die Berechnung in der Schleife
            Map<String, Double> tempAfd = tempAfdObject.calcAfdForOneProband(proband, aoiPathDeclaration);
            valuesAfdForAllProbands.put(proband, tempAfd);
        }
    }


    // Erstelle Getter-Methode:

        // Gibt die berechneten afd-Werte der Probandenliste aus:
    public Map<Proband, Map<String, Double>> getAfdList () {
        return this.valuesAfdForAllProbands;
    }


}

