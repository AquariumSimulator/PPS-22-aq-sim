package view.widgets.download

import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import com.github.tototoshi.csv.CSVWriter
import com.github.tototoshi.csv.defaultCSVFormat
import mvc.MVC.given_ViewRequirements as context

object DownloadCSV:

  def apply(path: String): Unit =
    println("Saving fishes...")
    saveFish(path)
    println("Saving algaes...")
    saveAlgae(path)
    println("Done: " + path)

  private def saveFish(path: String) =
    saveToCSV(path, "fish.csv")(context.controller.getAllFish)(fish =>
      List(
        fish.feedingType.toString,
        fish.name
      )
    )

  private def saveAlgae(path: String) =
    saveToCSV(path, "algae.csv")(context.controller.getAllAlgae)(algae =>
      List(
        algae.base.toString,
        algae.height.toString
      )
    )

  private def saveToCSV[U](path: String, name: String)(getList: Int => List[U])(mapper: U => List[String]) =
    val outputFile = BufferedWriter(FileWriter(path + name))
    val csvWriter = CSVWriter.open(outputFile)
    (0 to context.controller.currentIteration).foreach(iteration =>
      csvWriter.writeAll(
        getList(iteration).map(mapper).map(l => iteration.toString :: l)
      )
    )
    csvWriter.close()
