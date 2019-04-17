package play.echo

import github.westernsam.repltest.ReplRunner
import org.scalatest.FunSuite
import play.echo.Echo

class EchoSpec extends FunSuite {

  val runner: ReplRunner = new ReplRunner(">", true, (out, in) => new Echo(out, in)).start()

  test("Echos") {
    runner
      .enter("hello", "hello")
      .enter("boo", "boo")
  }

}
