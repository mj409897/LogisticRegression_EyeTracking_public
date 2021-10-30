package com.hsfresenius.M159_JM_Final.SavingData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BLRVariablesToCSV {

    //Konstrukor:
    public BLRVariablesToCSV() {
    }

    //Methoden:
    public void saveData(ArrayList<Double> fsd,
                         ArrayList<Double> afdMax,
                         ArrayList<Double> afdDistr,
                         ArrayList<Double> frMax,
                         ArrayList<Double> frDistr,
                         ArrayList<Integer> targetVariable, String pathname) throws IOException {
        if (fsd.size() == afdMax.size() &&
                fsd.size() == afdDistr.size() &&
                fsd.size() == frMax.size() &&
                fsd.size() == frDistr.size() &&
                fsd.size() == targetVariable.size()){

            // Initiiere Objekt zur Erstellung einer Textdatei:
            File file = new File(pathname);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


            // Befülle Textdatei

            for (int i = 0; i < fsd.size(); i++){
                bufferedWriter.write(String.valueOf(fsd.get(i))+",");
                bufferedWriter.write(String.valueOf(afdMax.get(i))+",");
                bufferedWriter.write(String.valueOf(afdDistr.get(i))+",");
                bufferedWriter.write(String.valueOf(frMax.get(i))+",");
                bufferedWriter.write(String.valueOf(frDistr.get(i))+",");
                bufferedWriter.write(String.valueOf(targetVariable.get(i)));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        }
        else {
            System.out.println("Die Variablenlisten der unabhängigen Variablen " +
                    "und der abhängigen Variable haben unterschiedliche Längen." +
                    "Überprüfe die Berechnung in der Methode 'createCSVforBLRegression' " +
                    "der Klasse 'BinomialLogisticRegression' im Paket 'StatisticalEvaluation'!");
        }

    }

}
