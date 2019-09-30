package string_calculator

final class String_calculator {
  val SEPARATORS = Array('\n', ',')

  def add(input: String): String = {
    if (input.isEmpty) return "0"
    try {
      val numbers = this.parse(input)
      val sum = numbers.sum
      println("sum", sum)
      this.format(sum)
    } catch {
      case ex: RuntimeException => return ex.getMessage
    }
  }

  private def parse(input: String): Array[Double] = {
    var numbers = Array.empty[Double]
    var position = 0
    do {
      val (currentPosition, token) = this.nextToken(position, input)
      if (token.isEmpty) {
        position = currentPosition - 1
        val c = input.charAt(position)
        throw new RuntimeException(s"Number expected but '$c' found at position $position.")
      }
      println("next token", currentPosition, token.toDouble)

      numbers = numbers :+ token.toDouble
      position = currentPosition
    }
    while (position < input.length)
    numbers
  }

  private def nextToken(initialPosition: Int, input: String): (Int, String) = {
    var currentPosition = initialPosition
    var number = ""
    do {
      val c = input.charAt(currentPosition)
      if (this.isSeparator(c) && currentPosition == input.length - 1) {
        throw new RuntimeException("Number expected but EOF found")
      }
      if (this.isSeparator(c)) return (currentPosition + 1, number)
      number += c
      currentPosition += 1
    }
    while (currentPosition < input.length)
    return (currentPosition, number)
  }

  private def isSeparator(c: Char): Boolean = SEPARATORS contains c

  private def format(n: Double): String = this.roundAt(2)(n).toString

  private def roundAt(p: Int)(n: Double): Double = {
    val s = math pow (10, p); (math round n * s) / s
  }
}
