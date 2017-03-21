package com.zx80live.avg

import com.zx80live.avg.AvgSolver._
import org.scalatest.{FunSuite, Matchers}

class AvgSolverTest extends FunSuite with Matchers {

  test("waitToStart") {
    waitToStart(10, 1) shouldEqual 9
    waitToStart(10, 0) shouldEqual 10
    waitToStart(0, 10) shouldEqual 0
    waitToStart(1, 2) shouldEqual 0
  }

  test("minAvgTime") {
    minAvgTime(List((0, 3), (1, 9), (2, 6))) shouldEqual 9
    minAvgTime(List((0, 3), (1, 9), (2, 5))) shouldEqual 8
    minAvgTime(List((10, 3), (0, 3), (5, 3))) shouldEqual 3
  }

  test("avgTime") {
    avgTime(List((0, 3), (1, 9), (2, 6))) shouldEqual 10
    avgTime(List((0, 3), (2, 6), (1, 9))) shouldEqual 9
  }

  test("sortOptimally") {
    sortOptimally(List((0, 3), (1, 9), (2, 6))) shouldEqual List((0, 3), (2, 6), (1, 9))
  }
}
