package view.widgets.download

import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import com.github.tototoshi.csv.CSVWriter
import com.github.tototoshi.csv.defaultCSVFormat
import mvc.MVC.given_ViewRequirements as context
import model.fish.Fish
import model.Algae

/** Used to save to file data of simulation in CSV format. */
object DownloadCSV:

  def apply(path: String): Unit =
    given String = path
    val contr = context.controller
    println("Saving fishes...")
    saveToCSV("fish.csv")(it =>
      contr
        .getAllFish(it)
        .map(fish =>
          List(
            fish.feedingType.toString,
            fish.name
          )
        )
    )
    println("Saving algaes...")
    saveToCSV("algae.csv")(it =>
      contr
        .getAllAlgae(it)
        .map(algae =>
          List(
            algae.base.toString,
            algae.height.toString
          )
        )
    )
    println("Done: " + path)

  private def saveToCSV(using path: String)(name: String)(getList: Int => List[List[String]]) =
    val outputFile = BufferedWriter(FileWriter(path + name))
    val csvWriter = CSVWriter.open(outputFile)
    (0 to context.controller.currentIteration).foreach(iteration =>
      csvWriter.writeAll(
        getList(iteration).map(l => iteration.toString :: l)
      )
    )
    csvWriter.close()
