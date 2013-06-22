/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

/**
 *
 * @author tyamgin
 */
public interface IPerceptron 
{
     /**
     * Распознавание образа
     * @param x - входной вектор
     * @return - выходной образ
     */
    public double[] recognize(double[] x);
    
    
    /**
     * Инициализация начальных весов
     * малым случайным значением
     */
    public void initWeights();
    public void initWeights(int mod);
            
     /**
     * Обучение перцептрона
     * @param x - входной вектор
     * @param t - правильный выходной вектор
     */
    public void teach(double[] x, double[] y) throws Exception;
    
     /**
     * 
     * @return число нейронов на последнем слое
     */
    public int getN();
    
     /**
     * 
     * @return число входов
     */
    public int getM();
}
