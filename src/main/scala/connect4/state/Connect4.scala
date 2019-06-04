package connect4.state

import cats.data.StateT
import cats.effect.IO
import connect4.{Board, Player}

import scala.io.StdIn

object Connect4 {

  case class Turn(p:Player, col:Int)

  type Game = StateT[IO, (Player, Board), Unit]

  val p1 = Player('X', "Player1")
  val p2 = Player('O', "Player1")

  def swapPlayer(player: Player) : Player = if (player == p1) p2 else p1

  def printBoard : Game = for  {
    _          <- StateT.liftF(IO(println("Enter command: ")))
    st         <- StateT.get[IO, (Player, Board)]
    (_, board) = st
    _          <- StateT.liftF(IO(board.draw()))
  } yield ()


  def applyMove(column: Int) : Game = StateT.modify[IO, (Player, Board)] {
    case (player, board) => (swapPlayer(player), board.play(player, column).right.get)
  }

  def getColumnChoice: IO[Int] = IO(StdIn.readLine().toInt)

  def initialBoard  = new Board()

  def main(args: Array[String]):Unit = for {
    _       <- printBoard
    column  <- StateT.liftF(getColumnChoice)
    _       <- applyMove(column)



  } yield ()

}

/*

playing :: IO ()
playing = evalStateT loop initialBoard
  where

    loop :: StateT Board IO ()
    loop = do
      printBoard
      move <- lift promptMove
      modify (play move)
      ended <- gets gameEnded
      if ended
        then lift $ putStrLn "You win!"
        else loop

    printBoard :: StateT Board IO ()
    printBoard = do
      lift $ putStrLn $ "The board looks like:"
      board <- get
      lift $ putStrLn $ showBoard board

    promptMove :: IO Move
    promptMove = do
      putStr "Indicate a move: "
      move <- getLine
      if validMove move
        then pure $ getColumnChoice move
        else do
          putStrLn "Invalid move."
          promptMove

 */
