package play.canvas

import java.io.{InputStream, OutputStream}

import play.util.CommandLineApp

class AsciCanvas(outputStream: OutputStream, inputStream: InputStream) extends CommandLineApp(outputStream, inputStream, "Ascii paint") {
  override def processCommand(line: String): String = line
}

object AsciCanvas extends App {
  new AsciCanvas(System.out, System.in).run()
}
