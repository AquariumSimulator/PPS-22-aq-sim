package view.widgets.slider

import javafx.collections.*
import javafx.scene.control.ListView

trait ChronicleEvents:
  def update(newList: List[String]): Unit

object ChronicleEvents:
  def apply(): ChronicleEvents = ChronicleEventsImpl()

  private class ChronicleEventsImpl() extends ChronicleEvents:

    val list: ListView[String] = new ListView()
    
    def update(newList: List[String]): Unit =
      ???
      /*
      list.setItems(FXCollections.observableArrayList(newList))
      list.setEditable(true)
      list.setMaxHeight(150)
      */

    update(List())

