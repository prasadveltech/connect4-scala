package play.echo

import java.io.{InputStream, OutputStream}

import play.util.CommandLineApp

class Echo(out: OutputStream, in: InputStream) extends CommandLineApp(out, in, "Echo") {
  override def processCommand(line: String): String = line
}
