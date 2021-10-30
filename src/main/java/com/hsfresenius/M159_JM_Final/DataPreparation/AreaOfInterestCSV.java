package com.hsfresenius.M159_JM_Final.DataPreparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AreaOfInterestCSV {
    // Es gibt 6 AOI Regionen (A,B,C,D,E,F) im Code Rectangle-OK (RO), Rectangle-Smell (RS),
    // Vehicle-OK (VO) und Vehicle-Smell (VS)
    // Diese Klasse soll die definierte Area of Interest aus
    // den Dateien im Ordner AOI_region in ein Objekt übernehmen können.

    // Deklariere Eigenschaften (AOIs)
        // spezifische Eigenschaften des Objekts der Klasse:


        // Initiiere die Variablen für den Zugriff auf die Dateien mit AOI Informationen:
    public static final String PATH_TO_DATA = "data/AOI_region/";
    public static final String DATASET_RO = "Rectangle-OK-1920-1080.csv";
    public static final String DATASET_RS = "Rectangle-Smell-1920-1080.csv";
    public static final String DATASET_VO = "Vehicle-OK-1920-1080.csv";
    public static final String DATASET_VS = "Vehicle-Smell-1920-1080.csv";

        // Deklariere Scanner Objekt um die Daten einlesen zu können:
    private Scanner scan;
    private Scanner scanNumOfLines;

    // Erzeuge Konstruktor:
    public AreaOfInterestCSV() {
    }


    // Erzeuge Methode:
        // Die folgende Methode "readData" benötigt als Input die Angabe der Datei (RO,RS,VO oder VS)
    public Map<String, List<AoiCoordinates>> readData(String pathname) {

        Map<String,List<AoiCoordinates>> aoiRegions = new HashMap<>();

        // Initialisiere Methoden-Variablen zur Verarbeitung der Ergebnisse:
        List<AoiCoordinates> aoiCoordinatesRegionA = new ArrayList<>();
        List<AoiCoordinates> aoiCoordinatesRegionB = new ArrayList<>();
        List<AoiCoordinates> aoiCoordinatesRegionC = new ArrayList<>();
        List<AoiCoordinates> aoiCoordinatesRegionD = new ArrayList<>();
        List<AoiCoordinates> aoiCoordinatesRegionE = new ArrayList<>();
        List<AoiCoordinates> aoiCoordinatesRegionF = new ArrayList<>();

        // Initialisiere File-Datei um die Dateneinzulesen:
        File file = new File("");
        // Wechsle den Pfad anhand der Eingabe des Strings "pathname" in der Methode "readData":
        switch (pathname){
            case "RO" -> file = new File(AreaOfInterestCSV.PATH_TO_DATA + AreaOfInterestCSV.DATASET_RO);
            case "RS" -> file = new File(AreaOfInterestCSV.PATH_TO_DATA + AreaOfInterestCSV.DATASET_RS);
            case "VO" -> file = new File(AreaOfInterestCSV.PATH_TO_DATA + AreaOfInterestCSV.DATASET_VO);
            case "VS" -> file = new File(AreaOfInterestCSV.PATH_TO_DATA + AreaOfInterestCSV.DATASET_VS);

        }

        // Initialisiere Scanner Objekte und prüfe ob die Datei existiert.
        try {
            scan = new Scanner(file).useDelimiter(",");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanNumOfLines = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Überspringe Kopfzeile der Datei beim Einlesen:
        scan.nextLine();
        scanNumOfLines.nextLine();

        // Zähle die Anzahl der Zeilen innerhalb der Datei:
        int count_of_lines = 0;
        while (scanNumOfLines.hasNextLine()) {
            count_of_lines++;
            scanNumOfLines.nextLine();
        }

        // Loopt durch die Datei um jeden Eintrag in einer Zeile zu erreichen:
        for (int counter = 0; counter<=count_of_lines; counter++) {

            if (scan.hasNext()) {

                // Scanne die Zeile in der Datei nach den erforderlichen Informationen:
                for(int i = 1; i < 2; i++) { // Überspringe den ersten Eintrag der Zeile
                    scan.next();              // um zur AOI-Bezeichung zu gelangen.
                }

                // Füge den AOI-Namen (A,B,C,D,E oder F) der Objekteigenen ArrayList mit AOI-Namen hinzu.
                String aoi_name = scan.next();

                // Füge die AOI-Koordinaten in die Objekteigene ArrayList aoi:
                Integer aoi_X1_Coordinate = scan.nextInt();
                Integer aoi_Y1_Coordinate = scan.nextInt();
                Integer aoi_X2_Coordinate = scan.nextInt();
                Integer aoi_Y2_Coordinate = scan.nextInt();

                // Objekt füllen statt aoi_temp
                AoiCoordinates aoiCoordinates = new AoiCoordinates();
                aoiCoordinates.setX1(aoi_X1_Coordinate);
                aoiCoordinates.setY1(aoi_Y1_Coordinate);
                aoiCoordinates.setX2(aoi_X2_Coordinate);
                aoiCoordinates.setY2(aoi_Y2_Coordinate);

                // Füge die eben erstelle Liste mit den Koordinaten einer AOI-Objekt hinzu

                switch (aoi_name.replace("\"", "")){
                    case "A" -> aoiCoordinatesRegionA.add(aoiCoordinates);
                    case "B" -> aoiCoordinatesRegionB.add(aoiCoordinates);
                    case "C" -> aoiCoordinatesRegionC.add(aoiCoordinates);
                    case "D" -> aoiCoordinatesRegionD.add(aoiCoordinates);
                    case "E" -> aoiCoordinatesRegionE.add(aoiCoordinates);
                    case "F" -> aoiCoordinatesRegionF.add(aoiCoordinates);
                }
            }
            // Springe in nächste Zeile um mit dieser fortzufahren:
            if (scan.hasNextLine()){
                scan.nextLine();
            }

        }
        aoiRegions.put("A",aoiCoordinatesRegionA);
        aoiRegions.put("B",aoiCoordinatesRegionB);
        aoiRegions.put("C",aoiCoordinatesRegionC);
        aoiRegions.put("D",aoiCoordinatesRegionD);
        aoiRegions.put("E",aoiCoordinatesRegionE);
        aoiRegions.put("F",aoiCoordinatesRegionF);

        return aoiRegions;


    }

}
