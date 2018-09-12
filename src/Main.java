import FirstLab.NeuronL1;

public class Main {
    public static void main(String[] args) {
        NeuronL1 neuronL1 = new NeuronL1(2, 0.4, 0.0, 0.001);
        System.out.println("W = " + neuronL1.training());

    }
}
