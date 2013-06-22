/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author tyamgin
 */
public class MultiLayerPerceptron implements IPerceptron
{
    private double alpha = 0.3; // Коэффициент сглаживания ошибки
    private double eta = 0.5;   // Скорость градиентного спуска
    
    private double[][][] deltaW; // вспомогательный массив для обучения
    private Neuron[][] neurons;  // слои нейронов
    private int[] size;          // размеры слоев
    private int m;               // число входов
    private double[][] delta;            // градиент ошибки
    private double[][] o;                // выходы нейронов
    private double error;                // ошибка при последнем вызове teach
    
    /**
     * Конструктор
     * @param size_out - число нейронов в выходном слое
     * @param size_inner - число нейронов в среднем слое
     * @param m - число входов каждого нейрона
     */
    public MultiLayerPerceptron(int m, int[] sizes)
    {
        size = Arrays.copyOf(sizes, sizes.length);
        this.m = m;
        neurons = new Neuron[size.length][];
        deltaW = new double[size.length][][];
        o = new double[size.length + 1][];
        
        for(int i = 0; i < size.length; i++)
        {
            o[i + 1] = new double[size[i]];
            
            deltaW[i] = new double[i == 0 ? m : size[i - 1]][size[i]];
            neurons[i] = new Neuron[size[i]];
            for(int j = 0; j < size[i]; j++)
                neurons[i][j] = new Neuron(i == 0 ? m : size[i - 1]);
            for(int j = 0; j < deltaW[i].length; j++)
                for(int k = 0; k < deltaW[i][j].length; k++)
                    deltaW[i][j][k] = 0;
        }
        
        delta = new double[size.length][];
        for(int i = 0; i < size.length; i++)
            delta[i] = new double[size[i]];
        
        initWeights();
    }
    
    /**
     * Распознавание образа
     * @param x - входной вектор
     * @return - выходной образ
     */
    @Override
    public double[] recognize(double[] x) throws IllegalArgumentException
    {
        recognizeInO(x);
        return Arrays.copyOf(o[size.length], o[size.length].length);
    }
    
    private void recognizeInO(double[] x) throws IllegalArgumentException
    {
        if (x.length != m)
            throw new IllegalArgumentException("x.length != m");
        o[0] = x;
        for(int layer = 0; layer < size.length; layer++)
        {
            for(int i = 0; i < size[layer]; i++)
            {
                o[layer + 1][i] = neurons[layer][i].transfer(o[layer]);
            }
        }
    }
    

    /**
     * Инициализация начальных весов
     * малым случайным значением
     */
    @Override
    public void initWeights() 
    {
        initWeights(2);
    }
    
    @Override
    public void initWeights(int mod)
    {
        for(int i = 0; i < neurons.length; i++)
            for (int j = 0; j < neurons[i].length; j++)
                neurons[i][j].initWeights(mod);
    }
    
    /**
     * Обучение перцептрона
     * @param x - входной вектор
     * @param t - правильный выходной вектор
     */
    @Override
    public void teach(double[] x, double[] t) throws IllegalArgumentException
    {
        if (t.length != size[size.length - 1])
            throw new IllegalArgumentException("t.length != size[last]");
       
        recognizeInO(x);
        
        int lastLayer = size.length - 1;
        for(int k = 0; k < size[lastLayer]; k++) // для каждого узла k из Outputs
            delta[lastLayer][k] = -o[lastLayer + 1][k] * (1 - o[lastLayer + 1][k]) * (t[k] - o[lastLayer + 1][k]);
        
        for(int layer = lastLayer - 1; layer >= 0; layer--)  // для каждого узла j всех уровней, начиная с предпоследнего
        {
            for(int j = 0; j < size[layer]; j++)
            {
                double sum = 0;
                for(int k = 0; k < size[layer + 1]; k++)
                    sum += delta[layer + 1][k] * neurons[layer + 1][k].getWeigth(j);
                delta[layer][j] = o[layer + 1][j] * (1 - o[layer + 1][j]) * sum;
            }
        }
        
        for(int s = 0; s < size.length; s++)
        {
            for(int i = 0; i < (s == 0 ? m : size[s - 1]); i++)
            {
                for(int j = 0; j < size[s]; j++)
                {
                    deltaW[s][i][j] = alpha * deltaW[s][i][j] + 
                            (1 - alpha) * -eta * delta[s][j] * o[s][i];  
                    neurons[s][j].addWeigth(i, deltaW[s][i][j]);
                }
            }
        }
        
        error = 0.0;
        for(int j = 0; j < size[lastLayer]; j++)
            error += Math.pow(t[j] - o[lastLayer + 1][j], 2);
        error /= 2;
    }
    
    public int getSize(int dim) 
    {
        return dim == 0 ? m : size[dim - 1];
    }

    @Override
    public int getN()
    {
        return size[size.length - 1];
    }
    
    @Override
    public int getM()
    {
        return m;
    }
    
    public double getLastError()
    {
        return error;
    }
    
    /**
     * 
     * @param Коэффициент сглаживания ошибки, 0 < alpha < 1
     */
    public void setAlpha(double val) throws IllegalArgumentException
    {
        if (!(val > 0 && val < 1))
            throw new IllegalArgumentException("alpha must be 0 < alpha < 1");
        this.alpha = val;
    }
    
    /**
     * 
     * @param Скорость градиентного спуска, желательно 0 < eta < 1
     */
    public void setEta(double val)
    {
        this.eta = eta;
    }
}