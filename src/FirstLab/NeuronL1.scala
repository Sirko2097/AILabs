package FirstLab

import scala.math._

class NeuronL1(val x: Double, val yExpected: Double, val weight: Double, val deltaMax: Double) {

  var w: Double = weight

  def training() = {
    var yi = 0.0
    var xi = 0.0
    do {
      xi = x * w
      yi = sigmoid(xi)
      val di = yi * (1 - yi) * (yExpected - yi)
      val dw = di * x
      w = w + dw
    } while (abs((yExpected - yi) / yExpected) > deltaMax)
    println("Res y: " + yi)
    w
  }

  private def sigmoid(x: Double) = {
    1 / (1 + exp(-x))
  }

}

