package model

import model.aquarium.{Aquarium, AquariumState, AvailableFood, Population}
import model.fish.Fish

/** Model trait implemented in [[ModelImpl]]. */
trait Model:

  def m(): Int

  def initializeAquarium(herbivorousFishNumber: Int, carnivorousFishNumber: Int, algaeNumber: Int): Aquarium
  
  def updateTemperatureByUser(temperature: Double, aquarium: Aquarium): Aquarium

  def updateBrightnessByUser(brightness: Double, aquarium: Aquarium): Aquarium

  def cleanByUser(aquarium: Aquarium): Aquarium

  def updateOxygenationByUser(oxygenation: Double, aquarium: Aquarium): Aquarium

  def addInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium

  def removeInhabitant[A](aquarium: Aquarium, inhabitant: A): Aquarium

  def addFood[A](aquarium: Aquarium, food: A): Aquarium

  def removeFood[A](aquarium: Aquarium, food: A): Aquarium

  def step(aquarium: Aquarium): Aquarium
