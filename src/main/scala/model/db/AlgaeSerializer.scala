package model.db

import model.Algae

object AlgaeSerializer extends Serializer[Algae]:
  /** Serializes a given [[Algae]] into a String understandable by Prolog. */
  def serialize(algae: Algae): String =
    "algae('" + algae.base + "','" + algae.height + "')."

  /** Deserializes the given String (must be Prolog-like) into a [[Algae]] object. */
  def deserialize(info: String): Algae =
    val content = """.*\((.*)\).*""".r
    val content(list) = info
    val fields = list.split(",").iterator
    val base: Double = fields.next.replace("'", "").toDouble
    val height: Int = fields.next.replace("'", "").toInt
    Algae(base = base, height = height)
