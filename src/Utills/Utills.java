/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utills;

import java.util.Random;

/**
 *
 * @author tyamgin
 */
public class Utills 
{
    private static Random random = new Random();
    
    public static void randomShuffle(double[] a)
    {
        for(int i = 0; i < a.length; i++)
        {
            int idx = random.nextInt(a.length);
            double tmp = a[i];
            a[i] = a[idx];
            a[idx] = tmp;
        }
    }
    
    /**
     * 
     * @param L
     * @param R
     * @return random Integer in range [L, R] 
     */
    public static int rand(int L, int R)
    {
        return random.nextInt(R - L + 1) + L;
    }
    
    /**
     * [ a[0], a[1], ..., a[a.length - 1], b[0], b[1], ..., b[b.length - 1] ]
     * @param a
     * @param b
     * @return concatination of a and b
     */
    public static double[] concat(double[] a, double[] b)
    {
        double[] res = new double[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }
    
    /**
     * [a, a + 1, a + 2, ..., b - 1, b]
     * @param a
     * @param b
     * @return array of values from a to b
     */
    public static double[] range(int a, int b)
    {
        double[] res = new double[b - a + 1];
        for(int i = a; i <= b; i++)
            res[i - a] = i;
        return res;
    }
}
