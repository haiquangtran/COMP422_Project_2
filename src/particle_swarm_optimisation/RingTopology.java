/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particle_swarm_optimisation;

/**
 *
 * @author xuebing
 */
public class RingTopology extends Topology {

    private int _neighbors = 2;

    public RingTopology(){

    }

    public RingTopology(int n){
        setNeighbors(n);
    }

    public void share(Swarm s) {

        for (int i = 0; i < s.numberOfParticles(); ++i) {

            Particle p_i = s.getParticle(i);

            Particle best_neighbor = null;
            double best_fitness = s.getProblem().getWorstFitness();

            for (int j = -getNeighbors() / 2; j <= getNeighbors() / 2; ++j) {
                if (s.getProblem().isBetter(s.getParticle(NewMath.ModEuclidean(i + j, s.numberOfParticles())).getPersonalFitness(), best_fitness)) {
                    best_neighbor = s.getParticle(NewMath.ModEuclidean(i + j, s.numberOfParticles()));
                    best_fitness = best_neighbor.getPersonalFitness();
                }
            }

            p_i.setNeighborhoodFitness(best_fitness);

            for (int n = 0; n < p_i.getSize(); ++n) {
                p_i.setNeighborhoodPosition(n, best_neighbor.getPersonalPosition(n));

            }
        }
    }

    public int getNeighbors() {
        return _neighbors;
    }

    public void setNeighbors(int neighbors) {
        this._neighbors = neighbors;
    }
}

