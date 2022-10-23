package model.db

trait Serializer[T]:
  /** Create the theory string to be stored in prolog database for an object of type [[T]].
    *
    * @param obj
    *   The object of type [[T]] to be stored.
    * @param iteration
    *   The iteration the [[T]] object refers to.
    * @return
    *   The prolog theory of the object.
    */
  def serialize(obj: T, iteration: Int): String

  /** Create an object of type [[T]] from the theory string of prolog database.
    *
    * @param obj
    *   The theory string of the object.
    * @return
    *   The object of type [[T]] from the prolog theory.
    */
  def deserialize(obj: String): T
