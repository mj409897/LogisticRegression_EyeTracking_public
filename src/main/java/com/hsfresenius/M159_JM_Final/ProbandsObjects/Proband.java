package com.hsfresenius.M159_JM_Final.ProbandsObjects;

import java.util.ArrayList;

public class Proband {

    // Erstelle Eigenschaften der Klasse Proband:

        // Definiere einzelne Eigenschaften:
    public String name;
        // Definiere die Listen der Eigenschaften (Eigenschaften beschreiben die Fixierungen des Probanden):
    public ArrayList<Integer> fixationDurations = new ArrayList<>();
    public ArrayList<Integer> fixationsPosX = new ArrayList<>();
    public ArrayList<Integer> fixationsPosY= new ArrayList<>();


    // Erstelle Konstruktoren der Klasse Proband:

        // leerer Konstruktor, falls die Eigenschaften eines Probanden noch nicht bekannt sind:
    public Proband(){
    }
        // Konstruktor der ein fertigen Probanden erstellt wenn die Eigenschaften schon bekannt sind:
    public  Proband(String name,
                           ArrayList<Integer> fixationDurations,
                           ArrayList<Integer> fixationsPosX,
                           ArrayList<Integer> fixationsPosY) {
        // Setzen des Methodeninputs als Eigenschaften der Klasse.
        this.name = name;
        this.fixationDurations = fixationDurations;
        this.fixationsPosX = fixationsPosX;
        this.fixationsPosY = fixationsPosY;
    }

    // Erstelle Methode der Klasse Proband:
        // Setter-Methode (Übernimmt die Fixierungen des Probanden aus den Ursprungsdateien aus der Klasse "Loading_tsv"):

    public void setName(String name) {
        this.name = name;
    }

    public void setFixationDurations(ArrayList<Integer> fixationDurations) { this.fixationDurations = fixationDurations;
    }

    public void setFixationsPosX(ArrayList<Integer> fixationsPosX) {
        this.fixationsPosX = fixationsPosX;
    }

    public void setFixationsPosY(ArrayList<Integer> fixationsPosY) {
        this.fixationsPosY = fixationsPosY;
    }

    // Getter-Methoden: (nur zusätzlich, werden nicht verwendet)
        // Zugriff auf den Namen
    public String getName(){ return name; }
        // Zugriff auf die Fixation Durations Liste:
    public ArrayList<Integer> getFixationDurations() {
        return fixationDurations;
    }
        // Zugriff auf die Fixations Pos X Liste:
    public ArrayList<Integer> getFixationsPosX() {
        return fixationsPosX;
    }
        // Zugriff auf die Fixations Pos Y Liste:
    public ArrayList<Integer> getFixationsPosY() {
        return fixationsPosY;
    }

}
