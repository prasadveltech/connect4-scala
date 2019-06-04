package connect4

sealed trait GameState
case class Winner(player: Player) extends GameState
case object Draw extends GameState
case object Playing extends GameState

class Board() {
  private val board: Array[Array[String]] = Array.fill(6, 7)(" ")

  def draw(): String = {
    val columnNumbers = " 1 2 3 4 5 6 7"
    val horizontalSeparator = "---------------"

    s"""|$columnNumbers
        |$horizontalSeparator
        |${board.map(_.mkString("|", "|", "|")).mkString("\n|")}
        |$horizontalSeparator""".stripMargin
  }

  def play(p: Player, column: Int): Either[String, Board] = {

    Right(this)
  }

  def gameState: GameState = if (board.exists(_.exists(_ == " "))) Playing else Draw
}

case class Player(sign: Char, name: String)