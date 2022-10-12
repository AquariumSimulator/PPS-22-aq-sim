package view.widgets

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
    val outputFile = BufferedWriter(FileWriter(path + "fish.csv"))
    val csvWriter = CSVWriter.open(outputFile)
    (0 to context.controller.currentIteration).foreach(iteration =>
      context.controller
        .getAllFish(iteration)
        .foreach(fish =>
          csvWriter.writeRow(
            List(
              iteration,
              fish.feedingType,
              fish.name
            )
          )
        )
    )

  private def saveAlgae(path: String) =
    val outputFile = BufferedWriter(FileWriter(path + "algae.csv"))
    val csvWriter = CSVWriter.open(outputFile)
    (0 to context.controller.currentIteration).foreach(iteration =>
      context.controller
        .getAllAlgae(iteration)
        .foreach(algae =>
          csvWriter.writeRow(
            List(
              iteration,
              algae.base,
              algae.height
            )
          )
        )
    )
