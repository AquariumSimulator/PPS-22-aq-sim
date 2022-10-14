package view.widgets.download

import com.google.gson.Gson
import com.google.gson.stream.JsonWriter
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context

import java.io.{BufferedWriter, FileWriter}
import scala.language.postfixOps

object DownloadJSON:

  def apply(path: String): Unit =
    val gson = new Gson
    val writer = gson.newJsonWriter(FileWriter(path + "data.json"))
    writer.beginArray()
    (0 to context.controller.currentIteration).foreach(iteration =>
      val fishes = context.controller.getAllFish(iteration)
      val algaes = context.controller.getAllAlgae(iteration)
      if fishes.nonEmpty then
        writer.beginArray()
        fishes.foreach(fish =>
          val map = Map("feedingType" -> fish.feedingType.toString, "name" -> fish.name)
          writeObject(writer, iteration, map)
        )
        writer.endArray()
      if algaes.nonEmpty then
        writer.beginArray()
        algaes.foreach(algae =>
          val map = Map("base" -> algae.base.toString(), "height" -> algae.height.toString())
          writeObject(writer, iteration, map)
        )
        writer.endArray()
    )
    writer.endArray()
    writer.close()

  private def writeObject(writer: JsonWriter, iteration: Int, map: Map[String, String]) =
    writer.beginObject()
    writer.name("iteration")
    writer.value(iteration.toString)
    map.keySet.foreach(key =>
      writer.name(key)
      writer.value(map(key))
    )
    writer.endObject()
