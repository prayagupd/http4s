package org.http4s
package client

import java.io.IOException

import org.http4s.Http4sSpec
import org.http4s.headers.Accept
import org.http4s.Status.InternalServerError

// import scalaz.-\/
// import scalaz.concurrent.Task
// import scalaz.stream.Process

import fs2._
import fs2.Stream._

import org.http4s.Status.{Ok, NotFound, Created, BadRequest}
import org.http4s.Method._
import org.http4s.Uri.uri

class ClientSpec extends Http4sSpec {
  val service = HttpService {
    case r =>
      Response(Ok).withBody(r.body)
  }
  val client = Client.fromHttpService(service)

  "mock client" should {
    "read body before dispose" in {
      client.expect[String](Request(POST).withBody("foo")).unsafeRun must_== "foo"
    }

    // "fail to read body after dispose" in {
    //   Request(POST).withBody("foo").flatMap { req =>
    //     // This is bad.  Don't do this.
    //     client.fetch(req)(Task.now).flatMap(_.as[String])
    //   }.unsafeRun must beLeft

      //.like {
      //   case e: IOException => e.getMessage == "response was disposed"
      //   case _ => false == true
      // }
    // }

    // "fail to read body after client shutdown" in {
    //   val client = Client.fromHttpService(service)
    //   client.shutdown.unsafeRun
    //   client.expect[String](Request(POST).withBody("foo")).unsafeRun() must beLeft{
    //     case e: IOException => e.getMessage == "client was shut down"
    //   }
    // }
  }
}
