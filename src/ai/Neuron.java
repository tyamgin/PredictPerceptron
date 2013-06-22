package ai;

import java.util.Random;

/**
 * Нейрон
 */
public class Neuron
{
    private double[] w;        // веса синапсов    

    /**
     * Конструктор
     * @param m - число входов
     */
    Neuron(int m)
    {
        w = new double[m];
    }

    /**
     * Передаточная функция
     * @param x - входной вектор
     * @return - выходное значение нейрона
     */
    public double transfer(double[] x)
    {
        return activator(adder(x));
    }

    /**
     * Инициализация начальных весов
     * небольшими случайными значениями
     * @param n - от 0 до n
     */
    public void initWeights(int n)
    {
        Random rand = new Random();
        for (int i = 0; i < w.length; i++)
            w[i] = (double)rand.nextInt(n) * 0.001;
    }

    /**
     * Модификация весов для обучения
     * @param v - скорость обучения
     * @param d - разница между выходом нейрона и нужным выходом
     * @param x - входной вектор
     */
    public void changeWeights(double v, double d, double[] x)
    {
        for (int i = 0; i < w.length; i++)
            w[i] += v*d*x[i];
    }

    /**
     * Сумматор
     * @param x - входной вектор
     * @return - невзвешенная сумма nec (биас не используется)
     */
    private double adder(double[] x)
    {
        double nec = 0;
        for (int i = 0; i < x.length; i++)
            nec += x[i] * w[i];
        return nec;
    }

    /**
     * Сигмоидная функция  1 / (1 + e^-x)
     * @param x
     * @return 1 / (1 + e^-x)
     */
    private static double sigma(double x)
    {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    
    /**
     * 
     * 
     * 
     * @param nec - выход сумматора
     * @return
     */
    public static double activator(double nec)
    {
        return sigma(nec);
    }
    
    /**
     * 
     * @param pos - позиция
     * @return pos-ю координату весов
     */
    public double getWeigth(int pos)
    {
        return w[pos];
    }
    
    /**
     * Set the value in pos position
     * @param pos
     * @param value 
     */
    public void setWeigth(int pos, double value)
    {
        w[pos] = value;
    }
    
    /**
     * Add the value to the pos position
     * @param pos - position
     * @param value 
     */
    public void addWeigth(int pos, double value)
    {
        w[pos] += value;
    }
    
    @Override
    public String toString()
    {
        String res = "[";
        for(int i = 0; i < w.length; i++)
            res += w[i] + (i == w.length - 1 ? "" : ", ");
        res += "]";
        return res;
    }
}
