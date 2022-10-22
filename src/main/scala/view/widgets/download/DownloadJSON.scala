package view.widgets.download

import com.google.gson.Gson
import com.google.gson.stream.JsonWriter
import model.Algae
import model.fish.Fish
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context

import java.io.{BufferedWriter, FileWriter}
import scala.language.postfixOps

object DownloadJSON:
  def apply(path: String): Unit =
    given writer: JsonWriter = (new Gson).newJsonWriter(FileWriter(path + "data.json"))
    val controller = context.controller
    writer.beginArray()
    (0 to controller.currentIteration).foreach(it =>
      writer.beginArray()
      writeObject(Map("iteration" -> it.toString))
      writeArray(
        controller.getAllFish(it).map(f => Map("feedingType" -> f.feedingType.toString, "name" -> f.name))
      )
      writeArray(
        controller.getAllAlgae(it).map(a => Map("base" -> a.base.toString, "height" -> a.height.toString))
      )
      writer.endArray()
    )
    writer.endArray()
    writer.close()
  private def writeArray(using writer: JsonWriter)(mapList: List[Map[String, String]]) =
    if mapList.nonEmpty then
      writer.beginArray()
      mapList.foreach(map => writeObject(map))
      writer.endArray()
  private def writeObject(using writer: JsonWriter)(map: Map[String, String]) =
    writer.beginObject()
    map.keySet.foreach(key => writer.name(key).value(map(key)))
    writer.endObject()
