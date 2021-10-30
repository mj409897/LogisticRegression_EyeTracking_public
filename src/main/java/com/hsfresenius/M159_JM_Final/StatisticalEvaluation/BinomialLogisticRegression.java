package com.hsfresenius.M159_JM_Final.StatisticalEvaluation;

import com.hsfresenius.M159_JM_Final.ET_metrics.AverageFixationDuration;
import com.hsfresenius.M159_JM_Final.ET_metrics.FixationRate;
import com.hsfresenius.M159_JM_Final.ET_metrics.FixationSpatialDensity;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Proband;
import com.hsfresenius.M159_JM_Final.ProbandsObjects.Probands;
import com.hsfresenius.M159_JM_Final.SavingData.BLRVariablesToCSV;
import com.hsfresenius.M159_JM_Final.Statistics.NeuralNetworkConfigurationSetUp;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BinomialLogisticRegression {

    // Eigenschaften der Klasse:
        // Initiiere Metrik-Objekte:
    private final FixationSpatialDensity fixationSpatialDensity = new FixationSpatialDensity();
    private final AverageFixationDuration averageFixationDurationOK = new AverageFixationDuration();
    private final AverageFixationDuration averageFixationDurationSmell = new AverageFixationDuration();
    private final FixationRate fixationRateOK = new FixationRate();
    private final FixationRate fixationRateSmell = new FixationRate();

        // Führe die relevanten Variablen für die Regressionsanalyse aus den Probanden-Listen Code-OK und Code-VS in Listen
        // zusammen, damit damit weiter gerechnet werden kann:
    private final ArrayList<Proband> probandsAll = new ArrayList<>(); // Liste mit Probanden
    private final ArrayList<Integer> targetVariable = new ArrayList<>(); // Liste mit Zielvariablen
    private final ArrayList<Double> fsdVariable = new ArrayList<>();
    private final ArrayList<Double> afdAvgVariable = new ArrayList<>();
    private final ArrayList<Double> afdMaxVariable = new ArrayList<>();
    private final ArrayList<Double> afdDistrVariable = new ArrayList<>();
    private final ArrayList<Double> frMaxVariable = new ArrayList<>();
    private final ArrayList<Double> frDistrVariable = new ArrayList<>();

        // Initiiere das Ergebnis Objekt zur Visualisierung:
    private MultiLayerNetwork model;




    // Konstruktor:
    public BinomialLogisticRegression() {
    }

    // Methoden:
    public void createCSVforBLRegression(String pathnameOK, String pathnameSmell, String pathnameNewFile) throws IOException {

        // Initiiere die notwendigen Probanden:
        ArrayList<Proband> probandsOK = new Probands().accessProbands(pathnameOK);
        ArrayList<Proband> probandsSmell = new Probands().accessProbands(pathnameSmell);




        for (Proband proband:probandsOK){
            probandsAll.add(proband);
            targetVariable.add(0); // Für Zielvariable Code-OK, setze den Wert 0
        }
        for (Proband proband:probandsSmell){
            probandsAll.add(proband);
            targetVariable.add(1); // Für Zielvariable Code-Smell, setze den Wert 1
        }



        // Berechne die FSD für die erzeugte Probandenliste:
        fixationSpatialDensity.setFsdList(probandsAll);
        Map<Proband,Double> tempFsdMap = fixationSpatialDensity.getFsdList();
        fsdVariable.addAll(tempFsdMap.values()); // Füge die Werte der FSD-Variablen Liste hinzu.

        // Berechne die mittlere AFD für die erzeugte Probandenliste:
            // Erstell Afd-Werte für Probanden Objekte:
        averageFixationDurationOK.setAfdList(probandsOK,pathnameOK);
        averageFixationDurationSmell.setAfdList(probandsSmell,pathnameSmell);
            // MapObjekt mit den Afd-Werten:
        Map<Proband,Map<String,Double>>afdMapOK = averageFixationDurationOK.getAfdList();
        Map<Proband,Map<String,Double>>afdMapSmell = averageFixationDurationSmell.getAfdList();
            // Errechne Mittelwert aus AFD-Werten eines Probanden:
                // Für Gruppe Code-OK:
        for (Proband proband : afdMapOK.keySet()) {
                // Initiiere temporäre Liste mit allen afd_werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> afdValueList = new ArrayList<>(afdMapOK.get(proband).values());
                // Initiiere temporäre Vriable zur Verarbeitung der Summe aus allen AFD Werten eines Probanden:
            double sumAFD = 0;
                // Initiiere temporäre Variable zur Aufnahme der Afd -Werte ohne NaN Werte um das Maximum zu finden.
            ArrayList<Double> tempAfdMaxSearchList = new ArrayList<>();

            for (Double aDouble : afdValueList) {
                if (!aDouble.isNaN()) {
                    sumAFD += aDouble;
                    tempAfdMaxSearchList.add(aDouble);
                }
            }
                // Berechne durchschnittlichen AFD-Wert eines Probanden und füge ihn der AFD-Variable Liste hinzu.
            double afdAvg = sumAFD/afdValueList.size();
                // Berechne den maximalen Afd-Wert in der Liste tempAfdMaxSearchList.
            double afdMax = Collections.max(tempAfdMaxSearchList);

            // Berechne die mittlere Differenz zum Maximalen AfdWert eines Probanden:
            ArrayList<Double> tempAfdDiffToMaxSearch = new ArrayList<>(); // Temporäre Liste soll alle Distanzen zum Maximalen AFD-Wert aufnehmen
            double sumAfdDiffToMaxListValues = 0; // Berechnet die Summe der Differenzen zum Maximalen AFD-Wert.
            for (Double aDouble : afdValueList) {
                if (!aDouble.isNaN() && aDouble != afdMax){
                    tempAfdDiffToMaxSearch.add(aDouble);
                }
            }
            for (Double DiffToMax : tempAfdDiffToMaxSearch) {
                sumAfdDiffToMaxListValues += DiffToMax;
            }
            double afdDiffToMaxAvg = sumAfdDiffToMaxListValues/tempAfdDiffToMaxSearch.size();


            afdDistrVariable.add(afdDiffToMaxAvg);
            afdMaxVariable.add(afdMax);
            afdAvgVariable.add(afdAvg);
        }
                // Für Gruppe VS:
        for (Proband proband : afdMapSmell.keySet()) {
                // Initiiere temporäre Liste mit allen afd_werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> afdValueList = new ArrayList<>(afdMapSmell.get(proband).values());
                // Initiiere temporäre Vriable zur Verarbeitung der Summe aus allen AFD Werten eines Probanden:
            double sumAFD = 0;
                // Initiiere temporäre Variable zur Aufnahme der Afd -Werte ohne NaN Werte um das Maximum zu finden.
            ArrayList<Double> tempAfdMaxSearchList = new ArrayList<>();

            for (Double aDouble : afdValueList) {
                if (!aDouble.isNaN()) {
                    sumAFD += aDouble;
                    tempAfdMaxSearchList.add(aDouble);
                }
            }
                // Berechne durchschnittlichen AFD-Wert eines Probanden und füge ihn der AFD-Variable Liste hinzu.
            double afdAvg = sumAFD/afdValueList.size();
                // Berechne den maximalen Afd-Wert in der Liste tempAfdMaxSearchList.
            double afdMax = Collections.max(tempAfdMaxSearchList);

                // Berechne die mittlere Differenz zum Maximalen AfdWert eines Probanden:
            ArrayList<Double> tempAfdDiffToMaxSearch = new ArrayList<>(); // Temporäre Liste soll alle Distanzen zum Maximalen AFD-Wert aufnehmen
            double sumAfdDiffToMaxListValues = 0; // Berechnet die Summe der Differenzen zum Maximalen AFD-Wert.
            for (Double aDouble : afdValueList) {
                if (!aDouble.isNaN() && aDouble != afdMax){
                    tempAfdDiffToMaxSearch.add(aDouble);
                }
            }
            for (Double DiffToMax : tempAfdDiffToMaxSearch) {
                sumAfdDiffToMaxListValues += DiffToMax;
            }
            double afdDiffToMaxAvg = sumAfdDiffToMaxListValues/tempAfdDiffToMaxSearch.size();


            afdDistrVariable.add(afdDiffToMaxAvg);
            afdMaxVariable.add(afdMax);
            afdAvgVariable.add(afdAvg);
        }

        // Aus der FixationRate werden für die Regression zwei Variablen gebildet. Da die FixationRate
        // mit höherer Rate einen höheren Untersuchungsaufwand für eine AOI darstellt wird die
        // maximale FixationRate als Anhaltspunkt für einen Code mit einem erkennbar relevanten AOI oder
        // einem besonders schwierig zu verstehendem AOI verwendet (frMaxVariable).
        // Um das Verhältnis zu den anderen AOI miteinzubeziehen, wird die mittlere Abweichung der FixationRate
        // Werte zu der maximalen FixationRate berechnet. Je höher dieser Wert ist desto gleichmäßiger wurden die
        // AOI Regionen betrachtet. Diese Variable (frDistrVariable) liefert eine Aussage wie wichtig einzelne
        // AOI-Regionen im Auge des Betrachters waren, weshalb sie gleichmäßig häufig betrachtet wurden oder
        // eher weniger im Vergleich zu anderen AOI Regionen im Code.

        // Berechne die maximale FR für die erzeugte Probandenliste (frMax):
            // Erstelle FR-Werte für Probanden Objekte:
        fixationRateOK.setFrList(probandsOK,pathnameOK);
        fixationRateSmell.setFrList(probandsSmell,pathnameSmell);
            // MapObjekt mit den FR-Werten:
        Map<Proband,Map<String,Double>> frMapOK = fixationRateOK.getFrList();
        Map<Proband,Map<String,Double>> frMapSmell = fixationRateSmell.getFrList();
            // Errechne Maximum aus FR-Werten eines Probanden:
                // Für Gruppe Code-OK:
        for (Proband proband : frMapOK.keySet()) {
                    // Initiiere temporäre Liste mit allen fr-Werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> frValueList = new ArrayList<>(frMapOK.get(proband).values());

                    // Berechne maximalen FR-Wert eines Probanden und füge ihn der FR-Variable Liste hinzu.
            double frMax = Collections.max(frValueList);
            frMaxVariable.add(frMax);
        }
                // Für Gruppe Code-Smell:
        for (Proband proband : frMapSmell.keySet()) {
                    // Initiiere temporäre Liste mit allen FR-werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> frValueList = new ArrayList<>(frMapSmell.get(proband).values());

                    // Berechne durchschnittlichen AFD-Wert eines Probanden und füge ihn der FR - Variable Liste hinzu.
            double frMax = Collections.max(frValueList);
            frMaxVariable.add(frMax);
        }

        // Berechne die mittlere Abweichung zur maximalen FixationRate für die erzeugte Probandenliste (frDistr):

            // Errechne mittler Abweichung zu frMax aus FR-Werten eines Probanden:
                // Für Gruppe Code-OK:
        for (Proband proband : frMapOK.keySet()) {
                    // Initiiere temporäre Liste mit allen fr-Werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> frValueList = new ArrayList<>(frMapOK.get(proband).values());

                    // Berechne maximalen FR-Wert eines Probanden und bilde die Differenzen der
                    // anderen Werte zu diesem Wert:
            double frMax = Collections.max(frValueList);
            ArrayList<Double> tempFrDiffToMaxList = new ArrayList<>();
            double sumFrDiffToMaxListValues = 0;
            for (Double aDouble : frValueList) {
                if (aDouble != 0 && aDouble != frMax) {
                    double frDistr = frMax - aDouble;
                    tempFrDiffToMaxList.add(frDistr);
                }
            }
            for (Double value : tempFrDiffToMaxList) {
                sumFrDiffToMaxListValues += value;
            }

                    // Berechne Mittelwert der Differenzen und füge ihn der frDistrVariable Liste hinzu:
            double frDiffToMaxAvg = sumFrDiffToMaxListValues/tempFrDiffToMaxList.size();
            frDistrVariable.add(frDiffToMaxAvg);

        }
                // Für Gruppe Code-Smell:
        for (Proband proband : frMapSmell.keySet()) {
                    // Initiiere temporäre Liste mit allen FR-werten eines Probanden zur Berechnung der Summe:
            ArrayList<Double> frValueList = new ArrayList<>(frMapSmell.get(proband).values());

                    // Berechne maximalen FR-Wert eines Probanden und bilde die Differenzen der
                    // anderen Werte zu diesem Wert:
            double frMax = Collections.max(frValueList); // Initiiert die Variable die den Maximalen FR-Wert der FR-Werte eines Probanden aufnimmt.
            ArrayList<Double> tempFrDiffToMaxList = new ArrayList<>(); // Initiiert die Liste die alle Differenzwerte zwischen den übrigen FR Werten und dem Maximalen FR Wert eines Probanden aufnimmt.
            double sumFrDiffToMaxListValues = 0; // Initiiert die Variable in der die Summe dieser Differenzwerte berechnet wird.
            for (Double aDouble : frValueList) {
                if (aDouble != 0 && aDouble != frMax) {
                    double frDistr = frMax - aDouble;
                    tempFrDiffToMaxList.add(frDistr);
                }
            }
            for (Double value : tempFrDiffToMaxList) {
                sumFrDiffToMaxListValues += value;
            }

                    // Berechne Mittelwert der Differenzen und füge ihn der frDistrVariable Liste hinzu:
            double frDiffToMaxAvg = sumFrDiffToMaxListValues/tempFrDiffToMaxList.size();
            frDistrVariable.add(frDiffToMaxAvg);
        }

        // Starte Binomial Logistic Regression:

        BLRVariablesToCSV saveAsCSV = new BLRVariablesToCSV();

        saveAsCSV.saveData(fsdVariable,afdMaxVariable,afdDistrVariable,frMaxVariable,frDistrVariable,targetVariable,pathnameNewFile);






    }

    public String calcBinomialLogisticRegression(int featuresCount,
                                                 int classesCount,
                                                 int positionClassColumn,
                                                 String pathToTestData,
                                                 String pathToTrainData){

        // Initialisiere die notwendigen Variablen:
        int BATCH_SIZE = 53; // Wählbare Größe für die die Gradientenmethode den Theta-Wert anpasst. Da der Datensatz sehr klein ist, kann der ganze Datensatz als Batch benutzt werden.
        String results = null;


        // Stelle die zuvor abgespeicherten Daten für die Berechnung als Datensatz zur Verfügung:
            // Einmal für den Training Datensatz:
        try (RecordReader recordReaderTrain = new CSVRecordReader()) {
            recordReaderTrain.initialize(new FileSplit(new File(pathToTrainData)));
            DataSetIterator iteratorTrain = new RecordReaderDataSetIterator(recordReaderTrain, BATCH_SIZE, positionClassColumn, classesCount);
            DataSet trainData = iteratorTrain.next();
            trainData.shuffle(42);
            // Und für den Test Datensatz:
            RecordReader recordReaderTest = new CSVRecordReader();
            recordReaderTest.initialize(new FileSplit(new File(pathToTestData)));
            DataSetIterator iteratorTest = new RecordReaderDataSetIterator(recordReaderTest, BATCH_SIZE, positionClassColumn, classesCount);
            DataSet testData = iteratorTest.next();
            testData.shuffle(42);

            // Normalisierung der Daten:
                // Initialisieren das Objekt das die Normalisierung durchführt:
            NormalizerStandardize normalizer = new NormalizerStandardize();
                // Anpassung des Normalizers an die Training Daten:
            normalizer.fit(trainData);
                    // Normalisierung der training data:
            normalizer.transform(trainData);
                    // Normalisierung der testing data:
            normalizer.transform(testData);


            // Erstelle das Model mit den Einstellungen der Klasse NeuralNetworkConfigurationSetUp. Hierin
            // Das MultiLayerNetwork model aus den Eigenschaften der Klasse wird hier initiiert
            // unter Anwendung der Klasse NeuralNetworkConfigurationSetUp.
            model = new MultiLayerNetwork(new NeuralNetworkConfigurationSetUp().
                                                setUpLogisticRegression(featuresCount, classesCount));


            model.init();

            model.fit(trainData); // Anpassen des Modells an den Trainingsdatensatz

            // Anwedung des Modells auf die abhängigen Variablen des Testdatensatzes:
            INDArray output = model.output(testData.getFeatures());

            // Auswertungsmethode:
            Evaluation eval = new Evaluation(classesCount);
            eval.eval(testData.getLabels(), output);

            results = eval.stats();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("File wurde nicht gefunden, nochmal nach Fehler suchen");
        }


        return results;
        
    }

    public MultiLayerNetwork getModel(){
        return model;
    }


}
