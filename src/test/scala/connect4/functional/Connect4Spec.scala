package connect4.functional

import connect4.Connect4
import github.westernsam.repltest.ReplRunner
import org.scalatest.FunSuite

class Connect4Spec extends FunSuite {

  val runner: ReplRunner = new ReplRunner(">", true, (out, in) => new Connect4(out, in)).start()

  test("player A starts") {
    runner
      .enter("1",
          "1 2 3 4 5 6 7",
          "---------------",
          "| | | | | | | |",
          "| | | | | | | |",
          "| | | | | | | |",
          "| | | | | | | |",
          "| | | | | | | |",
          "|X| | | | | | |",
          "---------------")
  }

}
