/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.io.FileNotFoundException;

/**
 *
 * @author teddycolon
 */

public class CESTTrain extends TrainANN {

    public CESTTrain(String trainFileName) throws FileNotFoundException {
        super(trainFileName);
    }

    public CESTTrain(String trainFileName, String validationFileName) throws FileNotFoundException {
        super(trainFileName, validationFileName);
    }

    public void setScales(String values) {
        super.setScaleValues(values);
    }

    public MyResult cestR1RhoPANNRun(String label, int nInputNeurons, int nOutputNeurons) throws FileNotFoundException, Exception {
        
        MyResult result = trainingRun(label, nInputNeurons, nOutputNeurons);
        return result;
    }
}
