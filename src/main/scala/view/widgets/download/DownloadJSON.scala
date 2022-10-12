package view.widgets.download

import com.google.gson.Gson
import mvc.MVC
import mvc.MVC.given_ViewRequirements as context

import java.io.{BufferedWriter, FileWriter}
import scala.language.postfixOps

object DownloadJSON:

  def apply(path: String) =
    //val outputFile = BufferedWriter(FileWriter(path + name))
    val gson = new Gson
    val writer = gson.newJsonWriter(FileWriter(path + "data.json"))
    writer.beginArray()
    (0 to context.controller.currentIteration).foreach(iteration =>
      if context.controller.getAllFish(iteration).nonEmpty ||
        context.controller.getAllAlgae(iteration).nonEmpty then
        writer.beginArray()
        context.controller.getAllFish(iteration).foreach(fish =>
          writer.beginObject()
          writer.name("iteration")
          writer.value(iteration.toString)
          writer.name("feedingType")
          writer.value(fish.feedingType.toString)
          writer.name("fishName")
          writer.value(fish.name)
          writer.endObject()
        )
        writer.endArray()
        writer.beginArray()
        context.controller.getAllAlgae(iteration).foreach(algae =>
          writer.beginObject()
          writer.name("iteration")
          writer.value(iteration.toString)
          writer.name("base")
          writer.value(algae.base.toString)
          writer.name("height")
          writer.value(algae.height.toString)
          writer.endObject()
        )
        writer.endArray()
      )
    writer.endArray()

  /*
  private def saveToCSV[U](path: String, name: String)(getList: Int => List[U])(mapper: U => List[String]) =
    val outputFile = BufferedWriter(FileWriter(path + name))
    val csvWriter = CSVWriter.open(outputFile)
    (0 to context.controller.currentIteration).foreach(iteration =>
      csvWriter.writeAll(
        getList(iteration).map(mapper).map(l => iteration.toString :: l)
      )
    )
    csvWriter.close()
*/