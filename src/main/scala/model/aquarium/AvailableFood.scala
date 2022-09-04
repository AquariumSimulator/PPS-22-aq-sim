package model.aquarium

import model.{CarnivorousFood, Food, HerbivorousFood}

/** This case class represent the food available inside the aquarium
  *
  * @param herbivorousFood
  *   herbivorous food available
  * @param carnivorousFood
  *   carnivorous food available
  */
case class AvailableFood(herbivorousFood: Seq[HerbivorousFood], carnivorousFood: Seq[CarnivorousFood])

/** Trait that models methods for modifying he food available inside the aquarium */
trait UpdateAvailableFood:

  /** Add a new food
    * @param addElem
    * @return
    *   a new seq of [[Food]]
    */
  def addFood(addElem: Food): Seq[Food]

  /** Remove a particular instance of food
    * @param removeElem
    *   that have to be removed
    * @return
    *   a new seq of [[Food]]
    */
  def deleteFood(removeElem: Food): Seq[Food]

/** Companion object of [[UpdateAvailableFood]] */
object UpdateAvailableFood:

  /** Create a new [[UpdateAvailableFood]] given a seq of [[Food]]
    * @param seq
    *   seq of [[Food]]
    * @return
    *   a new [[UpdateAvailableFood]]
    */
  def apply(seq: Seq[Food]): UpdateAvailableFood = UpdateAvailableFoodImpl(seq)

  /** Hidden implementation of [[UpdateAvailableFood]]
    * @param seq
    *   seq of [[Food]]
    */
  private class UpdateAvailableFoodImpl(seq: Seq[Food]) extends UpdateAvailableFood:

    override def addFood(addElem: Food): Seq[Food] =
      seq :+ addElem

    override def deleteFood(removeElem: Food): Seq[Food] = seq.filterNot(elem => elem == removeElem)
