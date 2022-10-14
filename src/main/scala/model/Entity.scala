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
    val checkY =
      other match
        case _: Algae =>
          position._2 <= other.position._2 &&
          position._2 > other.position._2 - other.size._2
        case _ =>
          position._2 < other.position._2 + other.size._2 &&
          position._2 + size._2 > other.position._2

    position._1 < other.position._1 + other.size._1 &&
    position._1 + size._1 > other.position._1 && checkY
