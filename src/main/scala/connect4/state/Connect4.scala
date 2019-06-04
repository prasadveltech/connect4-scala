package connect4.state

import cats.data.StateT
import cats.effect.ExitCase.Canceled
import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.all._
import connect4.{Board, GameState, Player, Playing}

import scala.io.StdIn
import scala.util.matching.Regex

object Connect4 extends IOApp {

  case class Turn(p: Player, col: Int)

  type Game = StateT[IO, (Player, Board), Unit]

  val p1 = Player('X', "Player1")
  val p2 = Player('O', "Player2")

  def swapPlayer(player: Player): Player = if (player == p1) p2 else p1

  def printBoard: Game = for {
    st          <- StateT.get[IO, (Player, Board)]
    (_, board)  = st
    _           <- StateT.liftF(IO(println(board.draw())))
  } yield ()

  def applyMove(column: Int): Game = for {
    st          <- StateT.get[IO, (Player, Board)]
    (pl, board)  = st
    updated     = board.play(pl, column)
    _           <- updated match {
      case Left(err) => StateT.liftF[IO, (Player, Board), Unit] {IO(println(s"Sorry $err"))}.flatMap(_ => applyMove(column))
      case Right(b)  => StateT.modify[IO, (Player, Board)] {case (player, _) =>
        swapPlayer(player) -> b
      }
    }
  } yield ()

  val selectColumn: Regex = "([1-7])".r
  def getColumnChoice(player: Player): IO[Either[ExitCode, Int]] = IO.suspend(for {
    _ <- IO(println(s"Enter command ${player.name}: "))
    c <- IO(StdIn.readLine()).flatMap {
        case selectColumn(c) => IO.pure(Right(c.toInt))
        case null            => IO.pure(Left(ExitCode.Success))
        case column          => IO(println(s"Select a column from 1-7: not '$column'")) *> getColumnChoice(player)
      }
    } yield c)

  def gameState : StateT[IO, (Player, Board), GameState] = for {
    st          <- StateT.get[IO, (Player, Board)]
    (_, board)  = st
  } yield board.gameState

  def mainLoop(): StateT[IO, (Player, Board), ExitCode] = for {
      st            <- StateT.get[IO, (Player, Board)]
      (player, _)   = st
      columnOrExit  <- StateT.liftF(getColumnChoice(player))
      exit          <- columnOrExit match {
        case Left(ec)       => StateT.liftF[IO, (Player, Board), ExitCode](IO.pure(ec))
        case Right(column)  => for {
          _             <- applyMove(column)
          _             <- printBoard
          gs            <- gameState
          exit          <- if (gs == Playing) mainLoop() else StateT.liftF[IO, (Player, Board), ExitCode] {
            IO(println(s"Game resulted in a $gameState")) *> IO.pure(ExitCode.Success)
          }
        } yield exit
      }
    } yield exit


  def run(args: List[String]): IO[ExitCode] = mainLoop().runA(p1 -> new Board()).guaranteeCase {
    case Canceled =>
      IO(println("Interrupted: releasing and exiting!"))
    case _ =>
      IO(println("Normal exit!"))
  }

}
