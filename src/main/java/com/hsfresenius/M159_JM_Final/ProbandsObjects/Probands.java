package com.hsfresenius.M159_JM_Final.ProbandsObjects;

import com.hsfresenius.M159_JM_Final.DataPreparation.LoadingTSV;

import java.io.File;
import java.util.ArrayList;

public class Probands {

    // Eigenschaften:
    // Initiiere die ArrayList paths:
    private final ArrayList<String> pathsRO = new ArrayList<>();
    private final ArrayList<String> pathsRS = new ArrayList<>();
    private final ArrayList<String> pathsVO = new ArrayList<>();
    private final ArrayList<String> pathsVS = new ArrayList<>();


    // Konstruktor:
    public Probands() {
        // Suche alle korrekten Dateipfade und fasse sie in einer ArrayList zusammen.
            // Erzeuge Loop durch alle Dateien:
                // Initiiere Objekte zur Erstellung der Dateipfade:
        String path = "data/Fixations/"; // Erster Teil des Pfades in dem die Dateien liegen
                // Die unterschiedlichen Ordner, in denen die Dateien liegen, als Ergänzung des Pfades:
        String path1 = "Rectangle-OK-1920-1080/";
        String path2 = "Rectangle-Smell-1920-1080/";
        String path3 = "Vehicle-OK-1920-1080/";
        String path4 = "Vehicle-Smell-1920-1080/";
                // Die Zahl weist auf den Probanden hin und muss mit einem Loop ausgetauscht werden.(Probanden 1-55)
        String zahl;
                // Die unterschiedlichen Dateinamen in den Ordnern:
        String dateiendung1 = "_RO.tsv"; // Dateiendung der Probandendatei mit _RO.tsv
        String dateiendung2 = "_RS.tsv"; // /_RS.tsv
        String dateiendung3 = "_VO.tsv"; // /_VO.tsv
        String dateiendung4 = "_VS.tsv"; // /_VS.tsv

        // Loop über die Anzahl der Probanden (55) und hinzufügen zur ArrayList paths:
        for (int i = 1; i <= 55; i++) {
            // Erzeuge die Probandennummer:
            zahl = Integer.toString(i);
            // Erzeuge den Pfad zur Datei:
            String pathX1 = path + path1 + zahl + dateiendung1;
            String pathX2 = path + path2 + zahl + dateiendung2;
            String pathX3 = path + path3 + zahl + dateiendung3;
            String pathX4 = path + path4 + zahl + dateiendung4;

            // Prüfe ob hinter pathX auch eine Datei existiert:
            File fileX1 = new File(pathX1);
            File fileX2 = new File(pathX2);
            File fileX3 = new File(pathX3);
            File fileX4 = new File(pathX4);

            if (fileX1.isFile()) {
                // Im Falle dass die Datei existiert wird sie zu den korrekten Dateipfaden hinzugefügt.
                pathsRO.add(pathX1);
            }
            if (fileX2.isFile()) {
                pathsRS.add(pathX2);
            }
            if (fileX3.isFile()) {
                pathsVO.add(pathX3);
            }
            if (fileX4.isFile()) {
                pathsVS.add(pathX4);
            }

        }
    }



    // Methoden:
        // Erzeuge für alle Dateipfade Objekte der Klasse Proband:
    public ArrayList<Proband> accessProbands (String pathname) {

            // Eigenschaften der Methode
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<Proband> probands = new ArrayList<>();

            // Switch durch die Pfade um die richtigen Dateien in der ArrayList paths zuzuordnen:
        switch(pathname) {
            case "RO" -> paths = pathsRO;
            case "RS" -> paths = pathsRS;
            case "VO" -> paths = pathsVO;
            case "VS" -> paths = pathsVS;
        }

            // Beginne mit den Pfaden aus einem Ordner:
            // Loop durch die Pfade um Probanden-Objekte zu erstellen und der ArrayList probandsRO hinzuzufügen:
        for (String path : paths) {

            // Zugriff auf einen einzelnen Pfad aus dem Array
            // Lade die tsv_Dateien
            LoadingTSV file = new LoadingTSV(path);

            // Einlesen der Daten
            // Name:
            String name = file.read_tsv_name();
            // Fixation Durations:
            ArrayList<Integer> fixationDurationsListe = file.read_tsv_fixationDurations();
            // Fixations Pos X:
            ArrayList<Integer> fixationsPosX_Liste = file.read_tsv_fixationsPosX();
            // Fixations Pos Y:
            ArrayList<Integer> fixationsPosY_Liste = file.read_tsv_fixationsPosY();

            // Erzeuge Objekte für die relevanten Daten (name,fixationDurations,fixationsPosX,fixationsPosY)
            probands.add(new Proband(name, fixationDurationsListe, fixationsPosX_Liste, fixationsPosY_Liste));

        }

        return probands;

    }
}
