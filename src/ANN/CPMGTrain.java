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
public class CPMGTrain extends TrainANN {

    public CPMGTrain(String trainFileName) throws FileNotFoundException {
        super(trainFileName);
    }

    public CPMGTrain(String trainFileName, String validationFileName) throws FileNotFoundException {
        super(trainFileName, validationFileName);
    }

    public void setScales(String values) {
        super.setScaleValues(values);
    }

    public MyResult cpmgANNRun(String label, int nInputNeurons, int nOutputNeurons) throws FileNotFoundException, Exception {
        // cpmgANNRun label can be Fast for cpmg fast exchange or Slow for slow exc
        MyResult result = trainingRun(label, nInputNeurons, nOutputNeurons);
        return result;
    }

}
