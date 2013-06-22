/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

/**
 *
 * @author tyamgin
 */
public class Perceptron2 extends MultiLayerPerceptron
{
    public Perceptron2(int m, int inner_size, int out_size)
    {
        super(m, new int[] {inner_size, out_size});
    }
}
