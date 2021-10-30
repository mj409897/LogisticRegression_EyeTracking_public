package com.hsfresenius.M159_JM_Final.ET_metrics;

import com.hsfresenius.M159_JM_Final.DataPreparation.AoiCoordinates;
import com.hsfresenius.M159_JM_Final.DataPreparation.AreaOfInterestCSV;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixationRate {
    // Die Ergebnisse der Metrik Fixation Rate (FR) werden mit steigender
    // Rate in Verbindung mit einer höheren Effektivität bzw. einem geringeren
    // Aufwand beim Verständnis des betrachteten Objekts gebracht.
    // Die FR ergibt sich aus dem Verhältnis der Anzahl aller Fixierungen in einem Interessenblickfeld
    // (Area of Interest [AOI]) zu der Anzahl der Fixierungen im gesamten Blickbereich (Area of Glance [AOG]).

    // Erstelle Eigenschaften:
    // Deklariere die Eigenschaften der Klasse:
    private Map<String, List<AoiCoordinates>> aoiRegions;
    private final Map<String,Double> valuesFrForOneProband = new HashMap<>(); // Enthält für jede AOI-Region die Fr-Werte eines Probanden
    private final Map<Proband,Map<String,Double>> valuesFrForAllProbands = new HashMap<>(); // Enthält für alle Probanden einer Gruppe (RO,VS,RS,VO) die Fr-Werte für jede AOI-Region.


    // Erstelle Konstruktor:
        // Konstruktor:
    public FixationRate() {
    }

    // Erstelle Methoden
        // Berechnung der Metrik FixationRate (FR):

    public Map<String, Double> calcFrForOneProband(Proband proband, String aoiPathDeclaration) {

        // Initiiere die für die Berechnung notwendigen Variablen:
        AreaOfInterestCSV aoi = new AreaOfInterestCSV();

        // Verändere den Pfad in Abhängikeit von der Eingabe des Pfadnamens in der Eingabe der Methode calcFrForOneProband
        switch (aoiPathDeclaration) {
            case "RO" -> this.aoiRegions = aoi.readData("RO");
            case "RS" -> this.aoiRegions = aoi.readData("RS");
            case "VO" -> this.aoiRegions = aoi.readData("VO");
            case "VS" -> this.aoiRegions = aoi.readData("VS");
        }

        // Initiiere Variablen um die Anzahl der Fixierungen in einer AOI-Region abzuspeichern.
        int regionA_CountOfFixations = 0;
        int regionB_CountOfFixations = 0;
        int regionC_CountOfFixations = 0;
        int regionD_CountOfFixations = 0;
        int regionE_CountOfFixations = 0;
        int regionF_CountOfFixations = 0;



        // Initiiere Listen zur Aufnahme der Koordinaten.

        List<AoiCoordinates> aoiRegionCoordinatesA = aoiRegions.get("A");
        List<AoiCoordinates> aoiRegionCoordinatesB = aoiRegions.get("B");
        List<AoiCoordinates> aoiRegionCoordinatesC = aoiRegions.get("C");
        List<AoiCoordinates> aoiRegionCoordinatesD = aoiRegions.get("D");
        List<AoiCoordinates> aoiRegionCoordinatesE = aoiRegions.get("E");
        List<AoiCoordinates> aoiRegionCoordinatesF = aoiRegions.get("F");

        // Loop durch alle Fixierungen des Probanden um sie einer AOI-Region zuzuordnen und mit der Variable
        // CountOfFixations die Anzahl der Fixierungen in einer AOI zu zählen:
        for (int i = 0; i < proband.fixationsPosX.size(); i++) {
            // Loop durch alle AOI-Koordinaten der Region A um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesA) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionA_CountOfFixations += 1;
                }
            }
            // Loop durch alle AOI-Koordinaten der Region B um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesB) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionB_CountOfFixations += 1;
                }

            }
            // Loop durch alle AOI-Koordinaten der Region C um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesC) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionC_CountOfFixations += 1;
                }

            }
            // Loop durch alle AOI-Koordinaten der Region D um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesD) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionD_CountOfFixations += 1;
                }

            }
            // Loop durch alle AOI-Koordinaten der Region E um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesE) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionE_CountOfFixations += 1;
                }

            }
            // Loop durch alle AOI-Koordinaten der Region F um abzugleichen, ob der Blick hier lag.
            for (AoiCoordinates aoiCoordinates : aoiRegionCoordinatesF) {
                if (aoiCoordinates.getX1() < proband.fixationsPosX.get(i) && proband.fixationsPosX.get(i) < aoiCoordinates.getX2()
                        && aoiCoordinates.getY1() < proband.fixationsPosY.get(i) && proband.fixationsPosY.get(i) < aoiCoordinates.getY2()) {
                    regionF_CountOfFixations += 1;
                }

            }

        }

            // Berechnung der Fixation Rate für jede AOI-Region:
        double TotalSumOfFixationsInAOG = regionA_CountOfFixations +
                                          regionB_CountOfFixations +
                                          regionC_CountOfFixations +
                                          regionD_CountOfFixations +
                                          regionE_CountOfFixations +
                                          regionF_CountOfFixations;

                // Region A:
        double valueFrA = regionA_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("A",valueFrA);

                // Region B:
        double valueFrB = regionB_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("B",valueFrB);

                // Region C:
        double valueFrC = regionC_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("C",valueFrC);

                // Region D:
        double valueFrD = regionD_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("D",valueFrD);

                // Region E:
        double valueFrE = regionE_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("E",valueFrE);

                // Region F:
        double valueFrF = regionF_CountOfFixations/TotalSumOfFixationsInAOG; // Berechnung der FixationRate
        valuesFrForOneProband.put("F",valueFrF);



        return valuesFrForOneProband;
    }

    // Erstelle Setter-Methode:

        // Nimmt probands-Objekt sowie die Gruppe zu der die AOI Regionen gehören ("RO","VS","RS","VO").
        // Für jeden Probanden und jede AOI-Region (A-F) werden die FR-Werte berechnet und in einem gemeinsamen Map Objekt abgelegt.
        // So wird ein HashMap Objekt erstellt, welche für jeden Probanden alle Ergebnisse enthält.

    public void setFrList (ArrayList<Proband> probands, String aoiPathDeclaration){
            // Loop durch die angegebene Probandenliste
        for (Proband proband : probands) {
            FixationRate tempFrObject = new FixationRate(); // Erzeugt ein temporäres fr Objekt für die Berechnung in der Schleife
            Map<String, Double> tempFr = tempFrObject.calcFrForOneProband(proband, aoiPathDeclaration);
            valuesFrForAllProbands.put(proband, tempFr);
        }
    }


        // Erstelle Getter-Methode:

            // Gibt die berechneten fr-Werte der Probandenliste aus:
    public Map<Proband, Map<String, Double>> getFrList () {
        return this.valuesFrForAllProbands;
    }


}
