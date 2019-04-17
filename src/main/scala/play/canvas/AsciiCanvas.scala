package play.canvas

import java.io.{InputStream, OutputStream}

import play.util.CommandLineApp

class AsciiCanvas(outputStream: OutputStream, inputStream: InputStream) extends CommandLineApp(outputStream, inputStream, "Ascii paint") {
  override def processCommand(line: String): String = "TODO"
}

object AsciiCanvas extends App {
  new AsciiCanvas(System.out, System.in).run()
}
