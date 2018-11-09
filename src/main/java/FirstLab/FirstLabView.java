package FirstLab;

public class FirstLabView {
    public void firstLab() {
        NeuronL1 neuronL12 = new NeuronL1(3, 0.02, 0.01, 0.01);
        System.out.println("W = " + neuronL12.training() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
