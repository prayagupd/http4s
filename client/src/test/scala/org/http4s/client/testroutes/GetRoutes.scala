package org.http4s.client.testroutes

import org.http4s.Status._
import org.http4s.{TransferCoding, Response}

// import scalaz.stream.Process
import fs2._
import fs2.Stream._

trait GetRoutes {

  /////////////// Test routes for clients ////////////////////////////////

  protected val getPaths: Map[String, Response] = {
    import org.http4s.headers._
    Map(
      "/simple" -> Response(Ok).withBody("simple path").unsafeRun,
      "/chunked" -> Response(Ok).withBody(Stream.emit("chunk1")).map(_.putHeaders(`Transfer-Encoding`(TransferCoding.chunked))).unsafeRun
    )
  }

}
