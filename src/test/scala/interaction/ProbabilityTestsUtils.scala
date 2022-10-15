package interaction

/** Object that contains utils method for the tests on the probabilities */
object ProbabilityTestsUtils:

  /** Checks each value of a list of tuples
    * @param list
    *   list of tuples
    * @param checkFunc
    *   check function
    */
  def checkForEach(list: List[(Double, Double)])(checkFunc: (Double, Double) => Boolean): Unit =
    list.foreach((value: Double, prob: Double) => assert(checkFunc(value, prob)))

  /** Creates a list of values with the relative probability
    * @param valStart
    *   start value of the iteration
    * @param probStart
    *   start probability of the iteration
    * @param n
    *   number of values
    * @param returnFunc
    *   function that creates the list of tuples
    * @return
    *   a list of values with the relative probability
    */
  def probabilities(valStart: Double, probStart: Double, n: Int)(
      returnFunc: (Double, Double) => (Double, Double)
  ): List[(Double, Double)] =
    List.iterate((valStart, probStart), n)((value: Double, prob: Double) => returnFunc(value, prob))
