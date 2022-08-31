case class Algae(height: Int = 1):
    val oxygenProduction: Int = Algae.OXYGEN_MULTIPLIER * height

object Algae:
    val MAX_HEIGHT: Int = 100
    val OXYGEN_MULTIPLIER: Int = 2