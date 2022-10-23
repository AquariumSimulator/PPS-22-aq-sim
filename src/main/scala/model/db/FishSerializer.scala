package model.db

import model.fish.Fish
import model.FeedingType

object FishSerializer extends Serializer[Fish]:
  /** Create the theory string to be stored in prolog database for a [[Fish]].
    *
    * @param fish
    *   The [[Fish]] to be stored.
    * @param iteration
    *   The iteration the [[Fish]] object refers to.
    * @return
    *   The prolog theory of the [[Fish]].
    */
  def serialize(fish: Fish, iteration: Int): String =
    "fish('" + fish.name + "','" + fish.feedingType.toString.head + "'," + iteration + ")."

  /** Create a [[Fish]] from the theory string of prolog database.
    *
    * @param info
    *   The [[Fish]] theory string.
    * @return
    *   The [[Fish]] from the prolog theory.
    */
  def deserialize(info: String): Fish =
    val content = """.*\((.*)\).*""".r
    val content(list) = info
    val fields = list.split(",").iterator
    val name: String = fields.next.replace("'", "")
    val feedingType: FeedingType = fields.next.replace("'", "") match
      case "C" => FeedingType.CARNIVOROUS
      case "H" => FeedingType.HERBIVOROUS
    Fish(name = name, feedingType = feedingType)
