/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particle_swarm_optimisation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author xuebing
 */
public class Swarm {

    private Problem _problem;
    private VelocityClamp _velocityClamp;
    private Topology _topology;
    private List<Particle> _swarm = new ArrayList<Particle>();
    private Random _random = new Random();

    public Swarm() {
    }

    public Problem getProblem() {
        return _problem;
    }

    public void setProblem(Problem problem) {
        this._problem = problem;
    }

    public Particle getParticle(int index) {
        return _swarm.get(index);
    }

    public void addParticle(Particle p) {
        _swarm.add(p);
    }

    public int numberOfParticles() {
        return _swarm.size();
    }

    public Random getRandom() {
        return _random;
    }

    public void initialize() {
        for (int i = 0; i < numberOfParticles(); ++i) {
            Particle p = getParticle(i);
            for (int j = 0; j < p.getSize(); ++j) {
                double position = NewMath.Scale(0, 1, getRandom().nextDouble(),
                        getProblem().getMinDomain(), getProblem().getMaxDomain());
                p.setPosition(j, position);

                double velocity = NewMath.Scale(0, 1, getRandom().nextDouble(),
                        20 / 100 * getProblem().getMinDomain(), 20 / 100 * getProblem().getMaxDomain());
                p.setVelocity(j, velocity);

                p.setPersonalFitness(getProblem().getWorstFitness());
                p.setNeighborhoodFitness(getProblem().getWorstFitness());

                p.setMaxPosition(getProblem().getMaxDomain());
                p.setMinPosition(getProblem().getMinDomain());
            }
        }
    }

    public void iterate() {

        for (int i = 0; i < numberOfParticles(); ++i) {
            Particle p_i = getParticle(i);
            double new_fitness = getProblem().fitness(p_i.getPosition());
            p_i.setFitness(new_fitness);


            //Check if new position is better than personal position...
            if (getProblem().isBetter(new_fitness, p_i.getPersonalFitness())) {
                p_i.setPersonalFitness(new_fitness);
                for (int j = 0; j < p_i.getSize(); ++j) {
                    p_i.setPersonalPosition(j, p_i.getPosition(j));
                }
            }
        }


        getTopology().share(this);

        for (int i = 0; i < numberOfParticles(); ++i) {
            getParticle(i).updateVelocity();
            //getVelocityClamp().clamp(getParticle(i),getProblem().getMaxVelocity());
            //do velocity clamp vClamp().clamp(particle);

            getParticle(i).updatePosition();
        }
    }

    public Topology getTopology() {
        return _topology;
    }

    public void setTopology(Topology topology) {
        this._topology = topology;
    }

    /**
     * @return the _VelocityClamp
     */
    public VelocityClamp getVelocityClamp() {
        return _velocityClamp;
    }

    /**
     * @param VelocityClamp the _VelocityClamp to set
     */
    public void setVelocityClamp(VelocityClamp velocityClamp) {
        this._velocityClamp = velocityClamp;
    }
}
