package view.widgets

import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import com.github.tototoshi.csv.CSVWriter
import com.github.tototoshi.csv.defaultCSVFormat
import mvc.MVC.given_ViewRequirements as context

object CSVSimulation:
  def saveFish(path: String) =
    val outputFile = BufferedWriter(FileWriter(path))
    val csvWriter = CSVWriter.open(outputFile)

  def saveAlgae(path: String) =
    val outputFile = BufferedWriter(FileWriter(path))
    val csvWriter = CSVWriter.open(outputFile)
