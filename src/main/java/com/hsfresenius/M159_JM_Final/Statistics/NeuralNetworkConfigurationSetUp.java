package com.hsfresenius.M159_JM_Final.Statistics;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Nesterovs;

public class NeuralNetworkConfigurationSetUp {

    // Konstruktor:
    public NeuralNetworkConfigurationSetUp(){}

    // Methode:
    public MultiLayerConfiguration setUpLogisticRegression(int FEATURES_COUNT, int CLASSES_COUNT) {
        // Erstellen des Output layer, die Parameter der Einstellungen wurden als Standardwerte für die logistic regression übernommen:
        // (Code aus: https://deeplearning4j.konduit.ai/getting-started/tutorials/logistic-regression)
        OutputLayer outputLayer = new OutputLayer.Builder()
                .nIn(FEATURES_COUNT) //The number of inputs feed from the input layer
                .nOut(CLASSES_COUNT) //The number of output values the output layer is supposed to take
                .weightInit(WeightInit.XAVIER) // XAVIER The algorithm to use for weights initialization
                .activation(Activation.SOFTMAX) //Softmax activate converts the output layer into a probability distribution
                .build(); //Building our output layer

        // Konfiguration des MultiLayerNetwork: Hier werden die Parameter für die Anpassung der Funktion
        // an die  abhängigen Variablen mittels Gradientenmethode eingestellt.
        // (Code von: https://deeplearning4j.konduit.ai/getting-started/tutorials/logistic-regression)
        MultiLayerConfiguration logisticRegressionConf = new NeuralNetConfiguration.Builder()
                //High Level Configuration
                .seed(42)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT) //STOCHASTIC_GRADIENT_DESCENT
                .updater(new Nesterovs(0.05, 0.9)) // Die learning rate wurde auf 0.05 eingestellt, weil hier die besten Ergebnisse zu verzeichnen sind.
                //For configuring MultiLayerNetwork we call the list method
                .list()
                .layer(0, outputLayer) //    <----- output layer fed here
                .build(); //Building Configuration

        return logisticRegressionConf;
    }

}
