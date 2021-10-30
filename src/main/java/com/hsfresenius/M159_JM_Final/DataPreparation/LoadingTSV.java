package com.hsfresenius.M159_JM_Final.DataPreparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadingTSV {

    // Erzeuge Eigenschaften der Klasse:

    // Erstelle Scanner zum Einladen von Dateien
    Scanner scan;
    // Erstelle Scanner zum Zählen der Zeilen:
    Scanner scanNumOfLines;
    // Erstelle tsv_file Objekt:
    File tsv_file;

    // Erzeuge Constructor der Klasse:
        // Für das Einlesen einer Datei:
    public LoadingTSV(String tsv_pfad) {

        this.tsv_file = new File(tsv_pfad);// Erzeuge Objekt aus der hinter dem Pfad angelegten Datei.
        // Prüfe ob die Datei hinter dem Pfad liegt:
        try {
            scan = new Scanner(tsv_file).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(" Der Pfad führt zu keiner Datei. Ordnerpfad überprüfen.");
        }



    }



    // Erzeuge METHODEN der Klasse Loading_tsv:

    // Methode zur Prüfung des Dateiinhalts auf den Namen:
    public String read_tsv_name() {

        // Initiiere erneut Scanner Objekte
        try {
            scan = new Scanner(tsv_file).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanNumOfLines = new Scanner(tsv_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialisiere String Variable;
        String name = "";

        // Überspringe Kopfzeile der Datei beim Einlesen:
        scan.nextLine();
        scanNumOfLines.nextLine();


        if (scan.hasNext()) {

            // Scanne die Zeile in der Datei nach den erforderlichen Informationen:
            for(int i = 1; i <= 5; i++) { // Überspringe die ersten 5 Einträge um zum Namen zu kommen;
                scan.next();
            }
            name = scan.next();
        }
        return (name);
        }






    // Methode zur Prüfung des Dateiinhalts auf die fixationDuration:
    public ArrayList<Integer> read_tsv_fixationDurations() {

        // Initiiere Scanner Objekte
        try {
            scan = new Scanner(tsv_file).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanNumOfLines = new Scanner(tsv_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialisiere ArrayList Variablen;
        ArrayList<Integer> fixationDurations = new ArrayList<>();

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
                for(int i = 1; i <= 45; i++) { // Überspringe die ersten 45 Einträge um zum fixationDuration zu kommen;
                    scan.next();
                }
                String fixationDuration_String = scan.next();
                int fixationDuration = Integer.parseInt(fixationDuration_String);

                // Fülle ArrayLists mit Informationen
                fixationDurations.add(fixationDuration);
            }
            // Springe in nächste Zeile um mit dieser fortzufahren:
            if (scan.hasNextLine()){
                scan.nextLine();
            }
        }
        return(fixationDurations);
    }





    // Methode zur Prüfung des Dateiinhalts auf die fixationPosX:
    public ArrayList<Integer> read_tsv_fixationsPosX() {

        // Initiiere Scanner Objekte
        try {
            scan = new Scanner(tsv_file).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanNumOfLines = new Scanner(tsv_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialisiere ArrayList Variablen;
        ArrayList<Integer> fixationsPosX = new ArrayList<>();

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
                for(int i = 1; i <= 46; i++) { // Überspringe die ersten 46 Einträge um zum fixationsPosX zu kommen;
                    scan.next();
                }
                String fixationPosX_String = scan.next();
                int fixationPosX = Integer.parseInt(fixationPosX_String);

                // Fülle ArrayLists mit Informationen
                fixationsPosX.add(fixationPosX);
            }
            // Springe in nächste Zeile um mit dieser fortzufahren:
            if (scan.hasNextLine()){
                scan.nextLine();
            }
        }
        return(fixationsPosX);
    }




    // Methode zur Prüfung des Dateiinhalts auf die fixationPosY:
    public ArrayList<Integer> read_tsv_fixationsPosY() {

        // Initiiere Scanner Objekte
        try {
            scan = new Scanner(tsv_file).useDelimiter("\t");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanNumOfLines = new Scanner(tsv_file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Initialisiere ArrayList Variablen;
        ArrayList<Integer> fixationsPosY = new ArrayList<>();

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
                for(int i = 1; i <= 47; i++) { // Überspringe die ersten 47 Einträge um zum fixationsPosY zu kommen;
                    scan.next();
                }
                String fixationPosY_String = scan.next();
                int fixationPosY = Integer.parseInt(fixationPosY_String);

                // Fülle ArrayLists mit Informationen
                fixationsPosY.add(fixationPosY);
            }
            // Springe in nächste Zeile um mit dieser fortzufahren:
            if (scan.hasNextLine()){
                scan.nextLine();
            }
        }
        return(fixationsPosY);
    }
}
