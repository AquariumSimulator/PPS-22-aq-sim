package model.db

import model.fish.Fish
import model.fish.FeedingType
import alice.tuprolog.SolveInfo

trait FishSerializer:
  // TODO: write doc
  def serialize(fish: Fish): String

  // TODO: write doc
  def deserialize(fish: String): Fish

object FishSerializer:
  def serialize(fish: Fish): String =
    "fish('" + fish.name + "','" + fish.feedingType.toString.head + "')."

  def deserialize(info: SolveInfo): Fish =
    println("info: " + info.toString)
    val name: String = info.getTerm("N").toString.replace("'", "")
    val feedingType: FeedingType = info.getTerm("F").toString.replace("'", "") match
      case "C" => FeedingType.CARNIVOROUS
      case "H" => FeedingType.HERBIVOROUS
    println("name: \"" + name + "\"")
    println("feed: \"" + feedingType + "\"")
    Fish(name = name, feedingType = feedingType)
