package model.chronicle

/** This class represent the chronicle of the aquarium events
  *
  * @param events
  *   represent the list of the events
  */
case class Chronicle(events: List[String] = List()) extends UpdateChronicle:
    override def addEvent(event: String): Chronicle =
        this.copy(events = this.events :+ event)
