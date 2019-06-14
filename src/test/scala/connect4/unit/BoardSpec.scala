package connect4.unit

import connect4.Board.Columns
import connect4.{Board, Draw, Player, Winner}
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class BoardSpec extends FunSpec {

  it("draws an empty board") {
    new Board().draw() shouldBe
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        |---------------""".stripMargin
  }

  it("player 1 chooses a column") {
    val board = new Board()
    board.play(Player('X', "sam"), 1).map(_.draw()).right.get shouldBe
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        ||X| | | | | | |
        |---------------""".stripMargin
  }

  it("player 2 chooses a column") {
    val board = new Board(parse(""" 1 2 3 4 5 6 7
                                  |---------------
                                  || | | | | | | |
                                  || | | | | | | |
                                  || | | | | | | |
                                  || | | | | | | |
                                  || | | | | | | |
                                  ||X| | | | | | |
                                  |---------------""".stripMargin))

    board.play(Player('O', "Phil"), 1).map(_.draw()).right.get shouldBe
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        ||O| | | | | | |
        ||X| | | | | | |
        |---------------""".stripMargin
  }

  it("Error if column full") {
    val board = new Board(parse(""" 1 2 3 4 5 6 7
                                  |---------------
                                  ||O| | | | | | |
                                  ||X| | | | | | |
                                  ||O| | | | | | |
                                  ||X| | | | | | |
                                  ||O| | | | | | |
                                  ||X| | | | | | |
                                  |---------------""".stripMargin))

    board.play(Player('O', "Phil"), 1) shouldBe Left("Column 1 is Full!")
  }

  it("player wins across") {
    new Board(parse(
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        ||X|X|X|X| | | |
        |---------------""".stripMargin)

    ).gameState shouldBe Winner('X')
  }

  it("player wins down") {
    new Board(parse(
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        ||O| | | | | | |
        ||O| | | | | | |
        ||O| | | | | | |
        ||O| | | | | | |
        |---------------""".stripMargin)
    ).gameState shouldBe Winner('O')
  }

  it("can show drwan game") {
    new Board(parse(
      """ 1 2 3 4 5 6 7
        |---------------
        ||X|O|O|O|X|X|X|
        ||O|X|X|X|O|O|O|
        ||X|O|O|O|X|X|X|
        ||O|X|X|X|O|O|O|
        ||X|O|O|O|X|X|X|
        ||O|X|X|X|O|O|O|
        |---------------""".stripMargin)
    ).gameState shouldBe Draw

  }

  it("player can win diagonally") {
    new Board(parse(
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | |O| | |
        || | | |O| | | |
        || | |O| | | | |
        || |O| | | | | |
        || | | | | | | |
        || | | | | | | |
        |---------------""".stripMargin)
    ).gameState shouldBe Winner('O')
  }

  it("player can win diagonally in a different plane") {
    new Board(parse(
      """ 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        ||X| | | | | | |
        || |X| | | | | |
        || | |X| | | | |
        || | | |X| | | |
        || | | | | | | |
        |---------------""".stripMargin)
    ).gameState shouldBe Winner('X')

  }

  private def parse(board: String): Columns = board.split("\n").toList.slice(2, 8).map(_.split("\\|").drop(1).toList).transpose

}
