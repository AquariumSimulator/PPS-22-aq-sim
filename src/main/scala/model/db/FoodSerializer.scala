package model.db

import model.food.Food
import model.FeedingType

object FoodSerializer extends Serializer[Food]:
  /** Create the theory string to be stored in prolog database for a [[Food]].
    *
    * @param food
    *   The [[Food]] to be stored.
    * @param iteration
    *   The iteration the [[Food]] object refers to.
    * @return
    *   The prolog theory of the [[Food]].
    */
  override def serialize(food: Food, iteration: Int): String =
    // iteration is ignored
    "food('" + food.feedingType.toString.head + "'," + food.nutritionAmount + "," + food.position._1 + "," + food.position._2 + ")."

  /** Create a [[Food]] from the theory string of prolog database.
    *
    * @param food
    *   The [[Food]] theory string.
    * @return
    *   The [[Food]] from the prolog theory.
    */
  override def deserialize(food: String): Food =
    val content = """.*\((.*)\).*""".r
    val content(list) = food
    val fields = list.split(",").iterator
    val feedingType: FeedingType = fields.next.replace("'", "") match
      case "H" => FeedingType.HERBIVOROUS
      case "C" => FeedingType.CARNIVOROUS

    val nutritionAmount: Int = fields.next.toInt
    val position: (Double, Double) = (fields.next.toDouble, fields.next.toDouble)
    Food(feedingType = feedingType, nutritionAmount = nutritionAmount, position = position)
