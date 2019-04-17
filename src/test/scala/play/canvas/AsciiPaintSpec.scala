package play.canvas

import github.westernsam.repltest.ReplRunner
import org.scalatest.FunSuite

class AsciiPaintSpec extends FunSuite {

  val runner: ReplRunner = new ReplRunner(">", true, (out, in) => new AsciCanvas(out, in)).start()

  test("Start a canvas") {
    runner
      .enter("C 4 4",
        " ----",
        "|    |",
        "|    |",
        "|    |",
        "|    |",
        " ----")
  }

}
