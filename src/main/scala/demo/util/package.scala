package demo

import java.io.IOException
import java.net.{ DatagramSocket, ServerSocket }
import com.google.common.io.Closeables

package object util {

  def availablePort(port: Int): Boolean = {
    require(port >= 1 && port <= Integer.MAX_VALUE, "Invalid start port: " + port)

    var ss: ServerSocket = null
    var ds: DatagramSocket = null
    try {
      ss = new ServerSocket(port)
      ss.setReuseAddress(true)
      ds = new DatagramSocket(port)
      ds.setReuseAddress(true)

      true
    } catch {
      case e: IOException => false
    } finally {
      Closeables.close(ds, true)
      Closeables.close(ss, true)
    }
  }
}
