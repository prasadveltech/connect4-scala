package connect4

import connect4.Board.Columns

import scala.annotation.tailrec

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
    def winner(c: Char): List[String] => Boolean = _.mkString contains c.toString * 4

    def diags(): Columns = {
      @tailrec def spanAll[T](l: List[T], acc: List[List[T]] = Nil)(pred: T => Boolean): List[List[T]] = {
        val (left, right) = l.span(pred)
        if (left.isEmpty)
          if (right.isEmpty) acc else spanAll(right.tail, acc :+ right.take(1))(pred)
        else spanAll(right.drop(1), acc :+ left ++ right.take(1))(pred)
      }

      def diag(b: Columns): Columns = {

        val coordinates: Seq[(Int, Int)] = for {
          i <- 0 until b.size + b.head.size //width + dept
          j <- i to 0 by -1 //depth
        } yield (i - j, j)

        val diagCoordinates = spanAll(coordinates.toList) { case (_, y) => y != 0 }

        val inBounds = diagCoordinates.map(_.filter {case (x, y) => x < b.size && y < b.head.size})

        inBounds.map(_.map {case (x, y) => b(x)(y)})
      }

      diag(board) ++ diag(board.map(_.reverse))
    }

    if (board.exists(winner('X'))) Some('X') //X col
    else if (board.exists(winner('O'))) Some('O') //O col
    else if (board.transpose.exists(winner('X'))) Some('X') //X row
    else if (board.transpose.exists(winner('O'))) Some('O') //O row
    else if (diags().exists(winner('X'))) Some('X') //diag x
    else if (diags().exists(winner('O'))) Some('O') //diag x
    else None
  }

  def gameState: GameState = maybeWinner match {
    case Some(s) => Winner(s)
    case None if !board.exists(_.exists(_ == " ")) => Draw
    case _ => Playing
  }
}

case class Player(sign: Char, name: String)