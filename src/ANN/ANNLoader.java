/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ANN;

import java.io.File;
import java.io.FileNotFoundException;
import org.ojalgo.ann.ArtificialNeuralNetwork;
import org.ojalgo.ann.NetworkBuilder;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author teddycolon
 */
public class ANNLoader {

    private NetworkBuilder builder;
    private final String savedNetworkFileName;
    private final HashMap<String, double[]> scaleValues = new HashMap(20);
    

    public ANNLoader(String fileName) {
        this.savedNetworkFileName = fileName;
    }

    private NetworkBuilder reBuildNeuralNetworkFromFile() throws FileNotFoundException, Exception {
        System.out.println("savedNetwork file name : " + savedNetworkFileName);
        File f = new File(savedNetworkFileName);
        Scanner scanFile = new Scanner(f);
        
        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();

            if (line.startsWith("Neu")) {
                String[] nNeuronsString = line.substring((line.indexOf("[") + 1), line.indexOf("]")).replaceAll("\\s+", "").split(",");
                int[] nNeuronsInt = Arrays.stream(nNeuronsString).mapToInt(Integer::parseInt).toArray();
                setBuilder(nNeuronsInt);
            } else if (line.startsWith("Wei")) {
                
                String weightLine = scanFile.nextLine();
                while (!weightLine.equals("")) {
                    String[] weightStrArr = weightLine.split("\\s+");
                    updateWeights(weightStrArr);
                    weightLine = scanFile.nextLine();
                }
            } else if (line.startsWith("Bi")) {
                String biasLine = scanFile.nextLine();
                while (!biasLine.equals("")) {
                    String[] biasStrArr = biasLine.split("\\s+");
                    updateBiases(biasStrArr);
                    biasLine = scanFile.nextLine();
                }
            } else if (line.startsWith("Act")) {
                String[] activatorsString = line.substring((line.indexOf("[") + 1), line.indexOf("]")).split(",");
                ArtificialNeuralNetwork.Activator[] listOfActivators = new ArtificialNeuralNetwork.Activator[activatorsString.length];
                int a = 0;
                for (String activator : activatorsString) {
                    listOfActivators[a] = ArtificialNeuralNetwork.Activator.valueOf(activator.trim());
                    a++;
                }
                updateActivators(listOfActivators);
            } else if (line.startsWith("Scale")) {
                String[] scaleValsString = line.substring((line.indexOf("=") + 1), (line.length() - 1)).replaceAll("\\s+", "").split(":");
                for (String scaleVals : scaleValsString) {
                    String[] tempKeyVal = scaleVals.split(";");
//                    System.out.println("[ANNLoader.reBuildNeuralNetwork] >>> KeyVal: " + Arrays.toString(tempKeyVal));
                    try {
                        String[] tempValArr = tempKeyVal[tempKeyVal.length - 1].split(",");
                        double[] tempDoubleArr = Arrays.stream(tempValArr).mapToDouble(Double::parseDouble).toArray();
//                        System.out.println("[ANNLoader.reBuildNeuralNetwork] >>> dArr: " + Arrays.toString(tempDoubleArr));
                        scaleValues.put(tempKeyVal[0], tempDoubleArr);
                    } catch (Exception e) {
                        throw new Exception("Problem retrieving scale values string. (" + e + ")");
                    }
                }
            }
        }
        return builder;
    }

    private void setBuilder(int[] layers) {
        builder = ArtificialNeuralNetwork.builder(layers[0], Arrays.copyOfRange(layers, 1, layers.length));
    }

    private void updateWeights(String[] weightLine) {
        int layer = Integer.parseInt(weightLine[0]);
        int input = Integer.parseInt(weightLine[1]);
        int output = Integer.parseInt(weightLine[2]);
        double value = Double.parseDouble(weightLine[3]);
        builder.weight(layer, input, output, value);
    }

    private void updateBiases(String[] biasLine) {
        int layer = Integer.parseInt(biasLine[0]);
        int output = Integer.parseInt(biasLine[1]);
        double value = Double.parseDouble(biasLine[2]);
        builder.bias(layer, output, value);
    }

    private void updateActivators(ArtificialNeuralNetwork.Activator[] activators) {
        builder.activators(activators);
    }

    public ArtificialNeuralNetwork getTrainedNetwork() throws FileNotFoundException, Exception {
        return reBuildNeuralNetworkFromFile().get();
    }

}
