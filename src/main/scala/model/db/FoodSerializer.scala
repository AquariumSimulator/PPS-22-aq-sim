package model.db

import model.food.Food

object FoodSerializer extends Serializer[Food]:
  /** Create the theory string to be stored in prolog database for a [[Food]].
    *
    * @param food
    *   The [[Food]] to be stored.
    * @return
    *   The prolog theory of the [[Food]].
    */
  override def serialize(food: Food): String = ???

  /** Create a [[Food]] from the theory string of prolog database.
    *
    * @param food
    *   The [[Food]] theory string.
    * @return
    *   The [[Food]] from the prolog theory.
    */
  override def deserialize(food: String): Food = ???
