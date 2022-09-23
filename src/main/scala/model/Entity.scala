package model

/** Common trait for every entity inside the aquarium. */
trait Entity:
  /** Position with base (0, 0) in the bottom-left corner. */
  val position: (Double, Double)

  /** Dimension of the entity. */
  val size: (Double, Double)

  /** Oxygen shift, if positive oxygen is produced else it's absorbed. */
  val oxygenShift: Double

  /** Impurity shift, if positive impurity is augmented else it's decreased. */
  val impurityShift: Double

  /** Ph shift, if positive ph is augmented else it's decreased. */
  val phShift: Double

  /** Checks if this Entity is colliding with the other given Entity. Colliding means that the rectangles, representing
    * the two entities, are overlapping.
    *
    * @param other
    *   The Entity to check for collision.
    * @return
    *   True whether the two entities are colliding, false otherwise.
    */
  def collidesWith(other: Entity): Boolean =
    position match
      case (x, y)
          if x > other.position._1 && x < (other.position._1 + other.size._1) && y > other.position._2 && y < (other.position._2 + other.size._2) =>
        true
      case _ => false
