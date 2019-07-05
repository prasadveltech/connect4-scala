package connect4

case class Player(sign: Char, name: String)

sealed trait GameState
case object Playing extends GameState
case object Draw extends GameState
case class Winner(sign: Char) extends GameState

class Board(val board: Array[Array[String]] = Array.fill(6, 7)(" ")) {

  def draw(): String = {
    val columnNumbers = " 1 2 3 4 5 6 7"
    val horizontalSeparator = "---------------"

    s"""|$columnNumbers
        |$horizontalSeparator
        |${board.map(_.mkString("|", "|", "|")).mkString("\n|")}
        |$horizontalSeparator""".stripMargin
  }

  def play(p: Player, column: Int): Either[String, Board] = {
    val rowNumber = board.map(_.apply(column - 1)).count(_ == " ")
    val newBoard = new Board(board.updated(rowNumber-1, board(rowNumber-1).updated(column-1, "" + p.sign)))
    Right(newBoard) //TODO
  }

  def gameState: GameState = Playing //TODO
}