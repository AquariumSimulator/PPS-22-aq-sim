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

/** Companion object of the case class */
object UpdateChronicle:

  /** Create a new [[Chronicle]]
    * @param chronicleList
    *   the string list that represent the initial chronicle
    * @return
    *   an instance of the chronicle
    */
  def apply(chronicle: Chronicle): UpdateChronicle =
    UpdateChronicleImpl(chronicle)

  private class UpdateChronicleImpl(chronicle: Chronicle) extends UpdateChronicle:

    override def addEvent(event: String): Chronicle =
      chronicle.copy(events = chronicle.events :+ event)
