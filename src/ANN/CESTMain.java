/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import static ANN.NeuralNetworkUtils.saveNeuralNetwork;
import java.util.HashMap;
import org.ojalgo.ann.ArtificialNeuralNetwork;

/**
 *
 * @author teddycolon
 */
public class CESTMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
//        String trainingFile = args[0];
        String trainingFile = "/Users/teddycolon/Desktop/ASRC/ojAlgo-ANN/cest-ann/training/CEST_R1RhoPerturbation_training.txt";
        String validationFile = "/Users/teddycolon/Desktop/ASRC/ojAlgo-ANN/cest-ann/validation/CEST_R1RhoPerturbation_validation.txt";
        MyResult result = null;
        if ((! trainingFile.isEmpty()) && (! validationFile.isEmpty())) {
            CESTTrain trainF1 = new CESTTrain(trainingFile, validationFile);
            result = trainF1.cestR1RhoPANNRun("R1RHOPERTURBATION", 11, 4);
        } else {
            throw new Exception("The string provided as the training file name is empty!");
        }
        
        if (result != null) {
            ArtificialNeuralNetwork trainedNetwork = (ArtificialNeuralNetwork) result.getFirst();
            HashMap networkInfo = (HashMap) result.getSecond();
            String saveToDir = "/Users/teddycolon/Desktop/ASRC/ojAlgo-ANN/cest-ann/saved-networks";
            saveNeuralNetwork(saveToDir, trainedNetwork, networkInfo);
        } else {
            throw new Exception("result variable is null!");
        }
        
        
        
    }
    
}
