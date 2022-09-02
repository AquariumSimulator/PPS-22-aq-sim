package Model
/** Common trait for every entity inside the aquarium. */
trait Entity:
  /** Position with base (0, 0) in the bottom-left corner. */
  def position: (Double, Double)
  /** Oxygen shift, if positive oxygen is produced else it's absorbed. */
  def oxygenShift: Double
  /** Impurity shift, if positive impurity is augmented else it's decreased. */
  def impurityShift: Double
  /** Ph shift, if positive ph is augmented else it's decreased. */
  def phShift: Double
