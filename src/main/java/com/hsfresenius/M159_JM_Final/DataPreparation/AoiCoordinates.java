package com.hsfresenius.M159_JM_Final.DataPreparation;

public class AoiCoordinates {
    // Diese Klasse soll die Koordinaten aus den eingelesenen Dateien der Klasse "AreaOfInterest"
    // aufnehmen und als Objekt speichern.
        // Deklariere die notwendigen Variablen:
    private Integer x1;
    private Integer y1;
    private Integer x2;
    private Integer y2;

        // Konstruktor 1: Ohne Angabe der Koordinaten
    public AoiCoordinates() {
    }
        // Konstruktor 2: Mit Angabe der Koordinaten, falls nachträglich einzelne AOI hinzugefügt werden sollten.
    public AoiCoordinates(Integer x1, Integer y1, Integer x2, Integer y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

        // Getter und Setter Methoden:
            // Zur Übergabe und zur Übernahme der Koordinaten:
    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }
}
