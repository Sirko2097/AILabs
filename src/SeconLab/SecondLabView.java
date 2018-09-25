package SeconLab;

public class SecondLabView {
    public void secondLab(){
        double[] x = {3, 2, 4, 2.5};
        double eps = 0.01;
        double[] w = {0.01, 0.02, 0.03, 0.015};
        NeuronL2 neuronL2 = new NeuronL2(x, 0.47, w, eps);
        System.out.println("W = " + neuronL2.training() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
