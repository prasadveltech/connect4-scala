package connect4

import java.io.{InputStream, OutputStream}

class Board() {
  private val board : Array[Array[String]] = Array.fill(6, 7)(" ")

  def draw(): String = {
    val columnNumbers = " 1 2 3 4 5 6 7"
    val horizontalSeparator = "---------------"

    s"""|
        |$columnNumbers
        |$horizontalSeparator
        |${board.map(_.mkString("|", "|", "|")).mkString("\n|")}
        |$horizontalSeparator""".stripMargin
  }

}

class Connect4(outputStream: OutputStream, inputStream: InputStream) extends CommandLineApp(outputStream, inputStream, "Connect 4") {
  val b: Board = new Board
  override def processCommand(line: String): String = b.draw()
}

object Connect4 extends App {
  new Connect4(System.out, System.in).run()
}
