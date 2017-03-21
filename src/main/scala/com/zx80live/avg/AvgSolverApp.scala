package com.zx80live.avg

import scala.collection.immutable.Seq

object AvgSolverApp extends AvgSolver {

  def main(args: Array[String]): Unit = {
    val N = io.StdIn.readInt()
    val xs: Seq[Client] = (0 until N) map { _ =>
      val Array(order, time) = io.StdIn.readLine().split("\\s+").map(_.toInt)
      (order, time)
    }
    println(minAvgTime(xs))
  }
}
