/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particle_swarm_optimisation;


import java.lang.Math.*;

/**
 *
 * @author xuebing
 */
public class NewMath {

    public static double Scale(double src_min, double src_max, double value,
            double target_min, double target_max) {
        return (target_max - target_min) / (src_max - src_min) * (value - src_min) + target_min;
    }

    public static double Averageitera(Swarm s) {
        double result = 0;
        for (int i = 0; i < s.numberOfParticles(); ++i) {
            result += s.getParticle(i).getFitness();
        }
        return result / s.numberOfParticles();
    }

    public static double[] AverageRunIterations(double ARI[][]) {
        double results[] = new double[ARI[1].length];
        for (int i = 0; i < ARI[1].length; ++i) {
            for (int r = 0; r < ARI.length; ++r) {
                results[i] += ARI[r][i];
            }
            results[i] = results[i] / ARI.length;
        }
        return results;
    }

    // get mean and Standard Deviation
    public static double[] Best_Mean_STD(double BestFitness[]) {
        double Arrays[] =BestFitness;
        double M_STD[] = new double[2];
        double sum = 0;
        for (int i = 0; i < BestFitness.length; ++i) {
            sum += BestFitness[i];
        }

        M_STD[0] = sum / BestFitness.length;

        M_STD[1] = calculateSTD(Arrays,M_STD[0]);
        return M_STD;
    }

   public static double calculateSTD(double Arrays[], double Mean) {
        double allSquare = 0.0;
        for (Double Array : Arrays) {
            allSquare += (Array - Mean) * (Array - Mean);
        }
        double denominator = Arrays.length;
        return Math.sqrt(allSquare / denominator);
    }

    public static int ModEuclidean(int D, int d) {
        int r = D % d;
        if (r < 0) {
            if (d > 0) {
                r = r + d;
            } else {

                r = r - d;
            }
        }
        return r;
    }
}


