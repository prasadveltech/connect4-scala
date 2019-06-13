package connect4

import connect4.Board.Columns

sealed trait GameState
case class Winner(player: Char) extends GameState
case object Draw extends GameState
case object Playing extends GameState

object Board {
  type Columns = List[List[String]]
}

class Board(board: Columns = List.fill(7, 6)(" ")) {

  def draw(): String = {

    val columnNumbers = " 1 2 3 4 5 6 7"
    val horizontalSeparator = "---------------"

    s"""|$columnNumbers
        |$horizontalSeparator
        |${board.transpose.map(_.mkString("|", "|", "|")).mkString("\n|")}
        |$horizontalSeparator""".stripMargin
  }

  def play(p: Player, column: Int): Either[String, Board] = {

    val colIndex = column - 1

    val firstAvailableSpace: Option[Int] = board(colIndex).zipWithIndex.reverse.collectFirst {
      case (char, idx) if char == " " => idx
    }

    firstAvailableSpace match {
      case None => Left(s"Column $column is Full!")
      case Some(num) =>
        Right(new Board(board.updated(colIndex, board(colIndex).updated(num, p.sign.toString))))
    }
  }


  private def maybeWinner: Option[Char] = {
    def winner(c: Char) : List[String] => Boolean = _.mkString contains c.toString * 4

    def diags(): Columns = {
      /*
            1 2 3
            4 5 6
            7 8 9

            (1), (4, 2), (7, 5, 3), (6, 8), 9
       */
      def diag(b: Columns) : Columns = ???

      diag(board) ++ diag(board.transpose)
    }

    if      (board.exists(winner('X')))           Some('X') //X col
    else if (board.exists(winner('O')))           Some('O') //O col
    else if (board.transpose.exists(winner('X'))) Some('X') //X row
    else if (board.transpose.exists(winner('O'))) Some('O') //O row
//    else if (diags().exists(winner('X')))         Some('X') //diag x
//    else if (diags().exists(winner('O')))         Some('O') //diag x
    else                                          None
  }

  def gameState: GameState = maybeWinner match {
    case Some(s)                                      => Winner(s)
    case None if !board.exists(_.exists(_ == " "))    => Draw
    case _                                            => Playing
  }
}

case class Player(sign: Char, name: String)