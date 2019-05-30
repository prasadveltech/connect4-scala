package connect4

import java.io.{InputStream, OutputStream}

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

  def play(column: Int, p: Player): Unit = () //TODO
}

class Connect4(outputStream: OutputStream, inputStream: InputStream) extends CommandLineApp(outputStream, inputStream, "Connect 4") {
  private val b: Board = new Board
  private val p1 = Player('X')
  private val p2 = Player('0')

  private var player = p1

  override def processCommand(line: String): String = {
    b.play(line.toInt, player)
    val boardState = b.draw()
    if (player == p1) player = p2 else player = p1
    boardState
  }
}

object Connect4 extends App {
  new Connect4(System.out, System.in).run()
}

case class Player(sign: Char)
