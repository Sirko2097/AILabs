package FirstLab

import scala.math._

class NeuronL1(val x: Double, val yExpected: Double, val weight: Double, val deltaMax: Double) {

  var w: Double = weight


  /**
    * In this method neuron trains.
    * @return velue of weight
    * */
  def training() = {
    var yi = 0.0
    var xi = 0.0
    do {
      xi = x * w
      yi = sigmoid(xi)
      val deltaI = sigmoidPrime(yi)
      val deltaW = deltaI * x
      w = w + deltaW
    } while (abs((yExpected - yi) / yExpected) > deltaMax)
    println("Res y: " + yi)
    w
  }

  /**
    * Calculate sigmoid
    *
    * @return value of function in x
    * */
  private def sigmoid(x: Double) = {
    1 / (1 + exp(-x))
  }

  /**
    * Calculate another version of sigmoid for changing weight
    *
    * @return value of function
    * */
  private def sigmoidPrime(y: Double) = {
    y * (1 - y) * (yExpected - y)
  }

}

