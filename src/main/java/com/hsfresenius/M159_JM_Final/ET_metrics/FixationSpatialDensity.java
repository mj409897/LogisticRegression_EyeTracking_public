package com.hsfresenius.M159_JM_Final.ET_metrics;

import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FixationSpatialDensity {
    // Die Ergebnisse der Fixation Spatial Density (SD)
    // weisen mit steigendem Wert auf eine direktere Suche,
    // beziehungsweise auf ein geringeres Zeitinvestment (Aufwand)
    // bei der Betrachtung des Objekts hin. Sie entspricht der Division
    // aus der Anzahl der Zellen die mindestens eine Fixierung enthalten
    // und der Gesamtanzahl der Zellen wenn der Stimulus als Gitter betrachtet wird (n).

    // Deklariere die Eigenschaften der Klasse:

    private final Map<Proband,Double> valuesFsdForAllProbands = new HashMap<>(); // Enthält für alle Probanden einer Gruppe (RO,VS,RS,VO) die Fsd-Werte.

    // Erstelle Konstruktor:
    public FixationSpatialDensity(){
    }

    // Erstelle Methoden:
        // Berechnung der Metrik FixationSpatialDensity (FSD);
    public double calcFsdForOneProband(Proband proband) {



        // Initiiere Eigenschaften der Methoden:
        ArrayList<String> listOfFixationCoordinates = new ArrayList<>();
        double valuesFsdForOneProband;
        double countOfRasterPoints = 1920 * 1080;


        // Loop durch alle Fixierungen des Probanden um doppelte Fixierungspunkte herauszufiltern:
            // Loop um die einzelnen XY Fixierungskoordinaten einer gemeinsamen Liste hinzuzufügen:
        for (int i = 0; i < proband.fixationsPosY.size(); i++) {
            String XYCoordinateString = Integer.toString(proband.fixationsPosX.get(i)) + Integer.toString(proband.fixationsPosY.get(i));
            if (!listOfFixationCoordinates.contains(XYCoordinateString)) { // Prüft ob Koordinatepaar schon vorhanden ist (also ob der Punkt schon einmal fixiert wurde).
                listOfFixationCoordinates.add(XYCoordinateString);
            }
        }

        valuesFsdForOneProband = (listOfFixationCoordinates.size()/countOfRasterPoints)*1000;

        return valuesFsdForOneProband;
    }
    // Erstelle Setter-Methode:

        // Nimmt probands-Objekt sowie die Gruppe zu der die AOI Regionen gehören ("RO","VS","RS","VO").
        // Für jeden Probanden werden die FSD-Werte berechnet und in einem gemeinsamen Map Objekt abgelegt.
        // So wird ein HashMap Objekt erstellt, welche für jeden Probanden alle Ergebnisse enthält.

    public void setFsdList (ArrayList<Proband> probands) {
        FixationSpatialDensity tempFsdObject = new FixationSpatialDensity(); // Erzeugt ein temporäres fsd Objekt für die Berechnung in der Schleife
                // Loop durch die angegebene Probandenliste
        for (Proband proband : probands) {
            Double tempFsd = tempFsdObject.calcFsdForOneProband(proband);
            valuesFsdForAllProbands.put(proband, tempFsd);
        }
    }


        // Erstelle Getter-Methode:

            // Gibt die berechneten fsd-Werte der Probandenliste aus:
    public Map<Proband,Double> getFsdList () {
        return this.valuesFsdForAllProbands;
    }


}
