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

}
