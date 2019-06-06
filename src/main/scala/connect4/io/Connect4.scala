package connect4.io

import cats.effect.ExitCase.Canceled
import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all._
import connect4.{Board, Player, Playing}

import scala.io.StdIn
import scala.util.matching.Regex

object Connect4 extends IOApp {

  val p1 = Player('X', "Player1")
  val p2 = Player('O', "Player2")
  val selectColumn: Regex = "([1-7])".r

  def dropCoin(b: Board, currentPlayer: Player, column: String): Either[String, Board] = column match {
    case selectColumn(c) => b.play(currentPlayer, c.toInt)
    case _ => Left(s"Select a column from 1-7: not '$column'")
  }

  def swapPlayer(p: Player): Player = if (p == p1) p2 else p1

  def eval(command: String, board: Board, p: Player): IO[ExitCode] = IO.suspend {
    if (command == null) IO.pure(ExitCode.Success) else dropCoin(board, p, command) match {
      case Left(err)                          => IO(println(s"Err: $err"))                              *> loop(board, p)
      case Right(b) if b.gameState == Playing => IO(println(b.draw()))                                  *> loop(b, swapPlayer(p))
      case Right(b)                           => IO(println(s"Game finished as ${b.gameState} : \n$b")) *> IO.pure(ExitCode.Success)
    }
  }

  def loop(board: Board, p: Player): IO[ExitCode] = IO.suspend {
    for {
      _           <- IO(print(s"> Choose a column ${p.name}: "))
      line        <- IO(StdIn.readLine)
      exitCode    <- eval(line, board, p)
    } yield exitCode
  }

  def run(args: List[String]): IO[ExitCode] = loop(new Board(), p1).guaranteeCase {
    case Canceled =>
      IO(println("Interrupted: releasing and exiting!"))
    case _ =>
      IO(println("Normal exit!"))
  }
}