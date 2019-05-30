package connect4.unit

import connect4.{Board, Connect4}
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class BoardSpec extends FunSpec {

  it("draws an empty board") {
    new Board().draw() shouldBe
      """
        | 1 2 3 4 5 6 7
        |---------------
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        || | | | | | | |
        |---------------""".stripMargin
  }

}
