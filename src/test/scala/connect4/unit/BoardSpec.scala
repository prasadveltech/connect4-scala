package connect4.unit

import connect4.{Board, Player}
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
    board.play(Player('X', "P1"), 1).right.get.draw() shouldBe
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

  it("player 1 chooses a same column") {
    val board = new Board()
    board
      .play(Player('X', "P1"), 1).right.get
      .play(Player('O', "P2"), 1).right.get.draw() shouldBe
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

}
