package ai;

import java.io.File;
import java.io.FilenameFilter;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Container;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Toolkit;

/**
 * Учитель
 * учит перцептрон распознаванию цифр
 */
public class Teacher< PerceptronClass extends IPerceptron >
{
    private PerceptronClass perceptron;    

    /**
     * Конструктор
     * @param perceptron
     */
    public Teacher(PerceptronClass perceptron)
    {
        this.perceptron = perceptron;        
    }

    /**
     * Обучение перцептрона
     * @param path
     * @param n - количество циклов обучения
     */
    public void teach(String path, int n)  throws Exception
    {
        
        class JPGFilter implements FilenameFilter {
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg"));
            }
        }       

        // загрузка всех тестовых изображений в массив img[]
        String[] list = new File(path + "/").list(new JPGFilter());
        Image[] img = new Image[list.length];        
        MediaTracker mediaTracker = new MediaTracker(new Container());        
        int i = 0;
        for (String s: list) 
        {
            img[i] = Toolkit.getDefaultToolkit().createImage(path + "/" + s);

            mediaTracker.addImage(img[i], 0);
            try {
                mediaTracker.waitForAll();
            } catch (InterruptedException ex) {
                Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
            }

            i++;
        }

        // инициализация начальных весов
        perceptron.initWeights();

        // получение пиксельных массивов каждого изображения
        // и обучение n раз каждой выборке
        PixelGrabber pg;
        int[] pixels;
        double[] x, y;
        int w, h, k = 0;
        for(int iter = 0; iter < n; iter++)
        {
            for (int j = 0; j < img.length; j++) 
            {
                w = img[j].getWidth(null);
                h = img[j].getHeight(null);

                if (w*h > perceptron.getM()) continue;

                pixels = new int[w*h];
                pg = new PixelGrabber(img[j], 0, 0, w, h, pixels, 0, w);
                try 
                {
                    pg.grabPixels();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
                }

                // получение векторов и обучение перцептрона
                x = getInVector(pixels);
                y = getOutVector(Integer.parseInt(String.valueOf(list[j].charAt(0))));
                perceptron.teach(x, y);
                
                if (iter == n - 1 && perceptron.getClass() == Perceptron2.class )
                    System.out.printf("%.5f\n", ((Perceptron2)perceptron).getLastError());
            }
            System.out.println((iter + 1) + "/" + n);
        }
    }

    /**
     * Трансформация пикселей изображения в вектор из 0 и 1
     * 1 на том месте, где есть цвет, 0 - там где белый
     * @param p - пиксели изображения
     * @return - вектор для входа перцептрона
     */
    private double[] getInVector(int[] p)
    {
        double[] x = new double[p.length];
        for (int i = 0; i < p.length; i++) {
            if (p[i] == -1) x[i] = 0; 
            else x[i] = 1;
        }
        return x;
    }

    /**
     * Генерация правильного выходного вектора
     * @param n - цифра, в соответствии с которой
     * нужно построить вектор, другими словами:
     * на каком месте должна быть 1, остальные 0
     * @return - выходной вектор для перцептрона
     */
    private double[] getOutVector(int n)
    {
        double[] y = new double[perceptron.getN()];
        for (int i = 0; i < y.length; i++) {
            if (i == n) y[i] = 1; 
            else y[i] = 0;
        }
        return y;
    }
}
