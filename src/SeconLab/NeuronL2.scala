package SeconLab

import java.util

import scala.math.{abs, exp}

class NeuronL2(val x: Array[Double], val yExpected: Double, val weight: Array[Double], val deltaMax: Double) {
  var w: Array[Double] = weight
  private var amountOfIterations: Int = 1
  private var xi: Double = 0.0

  private def sumOfInputX = {
    for (i <- x.indices) {
      xi += x(i) * w(i)
    }
    xi
  }


  /**
    * In this method neuron trains.
    * @return value of weight
    * */
  def training():Any = {
    val xi = sumOfInputX
    val yi = sigmoid(xi)

      if (abs((yExpected - yi) / yExpected) > deltaMax) {
        val deltaI = sigmoidPrime(yi)

        for (i <- x.indices) {
          val deltaW = deltaI * x(i)
          w(i) += deltaW
          amountOfIterations += 1
        }
        training()
      } else {
        println("Res y: " + yi + "\nRes x: " + xi + "\nAmount of Iterations: " + amountOfIterations)
        util.Arrays.toString(w)
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
