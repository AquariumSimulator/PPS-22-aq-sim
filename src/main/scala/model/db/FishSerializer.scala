package model.db

import model.fish.Fish
import model.fish.FeedingType
import alice.tuprolog.SolveInfo

trait FishSerializer:
  /** Create the theory string to be stored in prolog database for a [[Fish]].
    *
    * @param fish
    *   The [[Fish]] to be stored.
    * @return
    *   The prolog theory of the [[Fish]].
    */
  def serialize(fish: Fish): String

  /** Create a [[Fish]] from the theory string of prolog database.
    *
    * @param fish
    *   The [[Fish]] theory string.
    * @return
    *   The [[Fish]] from the prolog theory.
    */
  def deserialize(fish: String): Fish

object FishSerializer:
  def serialize(fish: Fish): String =
    "fish('" + fish.name + "','" + fish.feedingType.toString.head + "')."

  def deserialize(info: SolveInfo): Fish =
    val name: String = info.getTerm("N").toString.replace("'", "")
    val feedingType: FeedingType = info.getTerm("F").toString.replace("'", "") match
      case "C" => FeedingType.CARNIVOROUS
      case "H" => FeedingType.HERBIVOROUS
    Fish(name = name, feedingType = feedingType)
