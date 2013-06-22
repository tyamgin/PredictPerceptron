package ai;

/**
 * Однослойный n-нейронный перцептрон
 */
public class Perceptron implements IPerceptron
{
    Neuron[] neurons; // слой нейронов
    int n, m;

    /**
     * Конструктор
     * @param n - число нейронов
     * @param m - число входов каждого нейрона скрытого слоя
     */
    public Perceptron(int n, int m)
    {
        this.n = n;
        this.m = m;
        neurons = new Neuron[n];
        for (int j = 0; j < n; j++) {
            neurons[j] = new Neuron(m);
        }        
    }

    /**
     * Распознавание образа
     * @param x - входной вектор
     * @return - выходной образ
     */
    public double[] recognize(double[] x)
    {
        double[] y = new double[neurons.length];
        
        for (int j = 0; j < neurons.length; j++) {
            y[j] = neurons[j].transfer(x);
        }
        
        return y;
    }

    /**
     * Инициализация начальных весов
     * малым случайным значением
     */
    @Override
    public void initWeights() 
    {        
        initWeights(10);
    }
    
    @Override
    public void initWeights(int mod) 
    {        
        for (int j = 0; j < neurons.length; j++)
            neurons[j].initWeights(mod);
    }

    /**
     * Обучение перцептрона
     * @param x - входной вектор
     * @param y - правильный выходной вектор
     */
    public void teach(double[] x, double[] y)
    {
        double d;
        double v = 1; // скорость обучения

        double[] t = recognize(x);
        while (!equal(t, y)) 
        {

            // подстройка весов каждого нейрона
            for (int j = 0; j < neurons.length; j++) {
                d = y[j] - t[j];
                neurons[j].changeWeights(v, d, x);
            }
            t = recognize(x);
        }
    }

    /**
     * Сравнивание двух векторов
     * @param a - первый вектор
     * @param b - второй вектор
     * @return boolean
     */
    public static boolean equal(double[] a, double[] b) 
    {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++)
            if (Math.abs(a[i] - b[i]) > 1e-3)
                return false;
        return true;
    }


    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

}
