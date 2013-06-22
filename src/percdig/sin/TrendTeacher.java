/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package percdig.sin;

import ai.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utills.Utills;

/**
 *
 * @author tyamgin
 */
public class TrendTeacher 
{
    private Perceptron2 perceptron;
    public boolean debugOutput = false;
    
    public TrendTeacher(Perceptron2 perceptron)
    {
        this.perceptron = perceptron;
    }
    
    public void teach(double[] vector, int iters, boolean randomize)
    {
        if (randomize)
            teachInRandomOrder(vector, iters);
        else
            teach(vector, iters);
    }
    
    public void teach(double[] vector, int iters)
    {
        try 
        {
            perceptron.setAlpha(0.1);
            perceptron.setEta(0.5);
            perceptron.initWeights(5);
            
            int rangeInput = perceptron.getM();
            int rangeOutput = perceptron.getN();
            for(int it = 0; it < iters; it++)
            {   
                for(int i = rangeInput - 1; i < vector.length; i++)
                {
                    if (i + 1 + rangeOutput > vector.length)
                        break;
                    double[] inVector = Arrays.copyOfRange(vector, i - rangeInput + 1, i + 1);
                    double[] outVector = Arrays.copyOfRange(vector, i + 1, i + 1 + rangeOutput);
                    for(int j = 0; j < outVector.length; j++)
                        outVector[j] = (outVector[j] + 1.0) / 2;

                    perceptron.teach(inVector, outVector);
                    
                    if (debugOutput)
                        System.out.printf("%.5f\n", perceptron.getLastError());
                }
            }
        
        } 
        catch (Exception ex) 
        {
            System.err.println(ex);
        }
        
    }
    
    /**
     * 
     * @param vector
     * @param iters количество итераций (сумма всех итераций)
     */
    private void teachInRandomOrder(double[] vector, int iters)
    {
        try 
        {
            perceptron.setAlpha(0.1);
            perceptron.setEta(0.5);
            perceptron.initWeights(5);
           
            
            int rangeInput = perceptron.getM();
            int rangeOutput = perceptron.getN();

            for(int it = 0; it < iters; it++)
            {   
                int i = Utills.rand(rangeInput - 1, vector.length - rangeOutput + 1);
                
                double[] inVector = Arrays.copyOfRange(vector, i - rangeInput + 1, i + 1);
                double[] outVector = Arrays.copyOfRange(vector, i + 1, i + 1 + rangeOutput);

                perceptron.teach(inVector, outVector);

                if (debugOutput)
                    System.out.printf("%.5f\n", ((Perceptron2)perceptron).getLastError());
                
            }
        } 
        catch (Exception ex) 
        {
            System.err.println(ex);
        }        
    }
}
