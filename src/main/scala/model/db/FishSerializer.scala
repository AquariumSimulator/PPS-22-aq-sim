package model.db

import model.fish.Fish
import model.fish.FeedingType

object FishSerializer extends Serializer[Fish]:
  /** Serializes a given [[Fish]] into a String understandable by Prolog. */
  def serialize(fish: Fish): String =
    "fish('" + fish.name + "','" + fish.feedingType.toString.head + "')."

  /** Deserializes the given String (must be Prolog-like) into a [[Fish]] object. */
  def deserialize(info: String): Fish =
    val content = """.*\((.*)\).*""".r
    val content(list) = info
    val fields = list.split(",").iterator
    val name: String = fields.next.replace("'", "")
    val feedingType: FeedingType = fields.next.replace("'", "") match
      case "C" => FeedingType.CARNIVOROUS
      case "H" => FeedingType.HERBIVOROUS
    Fish(name = name, feedingType = feedingType)
