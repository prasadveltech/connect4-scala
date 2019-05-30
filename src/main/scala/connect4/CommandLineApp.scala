package connect4

import java.io._

abstract class CommandLineApp(outputStream: OutputStream, inputStream: InputStream, appName: String) extends Runnable {
  val in = new BufferedReader(new InputStreamReader(inputStream))
  val out = new BufferedWriter(new OutputStreamWriter(outputStream))

  def processCommand(line: String): String

  override def run(): Unit = {
    out.write(s"Welcome to $appName!!!\n")
    out.flush()

    while (true) {
      out.write("> ")
      out.flush()

      val line: String = in.readLine()
      if (line == null || line == "Q") return

      if (line != "") {
        out.write(processCommand(line))
        out.write("\n")
        out.flush()
      }
    }
  }
}
