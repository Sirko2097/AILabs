package FirstLab

import scala.math._

class NeuronL1(val x: Double, val yExpected: Double, val weight: Double, val deltaMax: Double) {
  var w: Double = weight
  private var i = 1


  /**
    * In this method neuron trains.
    * @return value of weight
    * */
  def training():Double = {
    val xi = x * w
    val yi = sigmoid(xi)
    if (abs((yExpected - yi) / yExpected) > deltaMax) {
      val deltaI = sigmoidPrime(yi)
      val deltaW = deltaI * x
      w = w + deltaW
      i += 1
      training()
    } else {
      println("Res y: " + yi + "\nRes x: " + xi + "\ni = " + i)
      w
    }
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

