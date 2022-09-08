package view.utils

import scalafx.scene.text.{Font, FontPosture, FontWeight}

/** Utility object to keep the same fonts everywhere in the application. */
object AquariumFonts:

  val fontFamily: String = "Helvetica"

  def bold(size: Double): Font = Font.font(fontFamily, FontWeight.Bold, FontPosture.Regular, size)
  def normal(size: Double): Font = Font.font(fontFamily, FontWeight.Normal, FontPosture.Regular, size)
