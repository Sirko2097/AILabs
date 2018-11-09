package ThirdLabMNIST

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator
import org.deeplearning4j.eval.Evaluation
import org.deeplearning4j.nn.api.OptimizationAlgorithm
import org.deeplearning4j.nn.conf.NeuralNetConfiguration
import org.deeplearning4j.nn.conf.layers.{DenseLayer, OutputLayer}
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork
import org.deeplearning4j.nn.weights.WeightInit
import org.deeplearning4j.optimize.listeners.ScoreIterationListener
import org.nd4j.linalg.activations.Activation
import org.nd4j.linalg.dataset.api.DataSet
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator
import org.nd4j.linalg.learning.config.Sgd
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction
import org.slf4j.LoggerFactory

object MNISTLab {

  private val log = LoggerFactory.getLogger(MNISTLab.getClass)

  def main(args: Array[String]) = {

    val seed = 1
    val numInputs = 28 * 28
    val numHidden = 512
    val numOutputs = 10
    val learningRate = 0.01
    val batchSize = 128
    val numEpochs = 10

    /*Download MNIST images as tensors*/
    val mnistTrain = new MnistDataSetIterator(batchSize, true, seed)
    val mnistTest = new MnistDataSetIterator(batchSize, false, seed)

    val conf = new NeuralNetConfiguration.Builder()
      .seed(seed)
      .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
      .updater(new Sgd(learningRate))
      .weightInit(WeightInit.XAVIER)
      .list
      .layer(0, new DenseLayer.Builder()
        .nIn(numInputs)
        .nOut(numHidden)
        .activation(Activation.RELU)
        .build)
      .layer(1, new OutputLayer.Builder(LossFunction.MCXENT)
        .nIn(numHidden)
        .nOut(numOutputs)
        .activation(Activation.SOFTMAX)
        .build)
      .build()


    val model = new MultiLayerNetwork(conf)
    model.init()
    model.setListeners(new ScoreIterationListener(100))

    for (_ <- 0 until numEpochs) {
      model.fit(mnistTrain)
    }

    def accuracy(dataSetIterator: DataSetIterator) = {
      val evaluator = new Evaluation(numOutputs)
      dataSetIterator.reset()

      while (dataSetIterator.hasNext) {
        val next: DataSet = dataSetIterator.next()
        val output = model.output(next.getFeatures)
        evaluator.eval(next.getLabels, output)
      }

      evaluator.accuracy()
    }

    log.info(s"Train accuracy = ${accuracy(mnistTrain)}")
    log.info(s"Test accuracy = ${accuracy(mnistTest)}")
  }
}
