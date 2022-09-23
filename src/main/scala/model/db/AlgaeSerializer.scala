package model.db

import model.Algae

object AlgaeSerializer extends Serializer[Algae]:
  /** Create the theory string to be stored in prolog database for a [[Algae]].
    *
    * @param algae
    *   The [[Algae]] to be stored.
    * @return
    *   The prolog theory of the [[Algae]].
    */
  def serialize(algae: Algae): String =
    "algae(" + algae.base + "," + algae.height + ")."

  /** Create a [[Algae]] from the theory string of prolog database.
    *
    * @param info
    *   The [[Algae]] theory string.
    * @return
    *   The [[Algae]] from the prolog theory.
    */
  def deserialize(info: String): Algae =
    val content = """.*\((.*)\).*""".r
    val content(list) = info
    val fields = list.split(",").iterator
    val base: Double = fields.next.toDouble
    val height: Int = fields.next.toInt
    Algae(base = base, height = height)
