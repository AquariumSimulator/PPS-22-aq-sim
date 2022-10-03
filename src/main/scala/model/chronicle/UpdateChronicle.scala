package model.chronicle

trait UpdateChronicle:

  /** Add an event to the chronicle
    *
    * @param event
    *   the new event of the chronicle
    * @return
    *   a new [[Chronicle]]
    */
  def addEvent(event: String): Chronicle
