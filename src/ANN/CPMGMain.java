/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.io.FileNotFoundException;
import java.util.HashMap;
import org.ojalgo.ann.ArtificialNeuralNetwork;
import static ANN.NeuralNetworkUtils.saveNeuralNetwork;

/**
 *
 * @author teddycolon
 */

/* TODO:
 1. read test file
 2. grab input and target output
 3. convert to ojAlgo data structure Access1D
 4. initialize the neural network
 5. train
 6. evaluate the errors to make sure we have lowest possible error (if possible)*/
public class CPMGMain {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        // TODO:
        // 1. Take it files to measure performance
        // 2. Train the neural network
        // 3. Save the neural network
        // 4. Call the guess(...) method in Jython code
        // 5. Display the MSE/RMSE differences by plotting bar graph (?)

        // XXX: Calling this function with 2 absolute file paths to train/test ANN
        String trainingFile = args[0];
        MyResult result = null;

        if (trainingFile.contains("FAST")) {
            CPMGTrain f1 = new CPMGTrain(trainingFile);
            if (trainingFile.contains("1")) {
                result = f1.cpmgANNRun("CPMGFast1", 11, 3);
            } else {
                result = f1.cpmgANNRun("CPMGFast2", 22, 3);
            }
        } else if (trainingFile.contains("SLOW")) {
            CPMGTrain f2 = new CPMGTrain(trainingFile);
            if (trainingFile.contains("1")) {
                result = f2.cpmgANNRun("CPMGSlow1", 11, 4);
            } else {
                result = f2.cpmgANNRun("CPMGSlow2", 22, 4);
            }
        } else {
            throw new Exception("The name of Training files should contain the type of CPMG experiment (e.g FAST, SLOW).");
        }
        if (result != null) {
            ArtificialNeuralNetwork trainedNetwork = (ArtificialNeuralNetwork) result.getFirst();
            HashMap networkInfo = (HashMap) result.getSecond();
            String saveDir = "/Users/teddycolon/Desktop/ASRC/ojAlgo-ANN/cpmg-ann/saved-networks";
            saveNeuralNetwork(saveDir,trainedNetwork, networkInfo);
        } else {
            throw new Exception("Result is null!");
        }

    }

}
