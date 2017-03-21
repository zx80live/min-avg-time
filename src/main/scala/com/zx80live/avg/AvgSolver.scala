package com.zx80live.avg

/**
  * Solver for the task "Minimum Average Waiting Time"
  *
  * @author zx80live@gmail.com
  * @since 21.03.2017
  */
trait AvgSolver {
  type Time = Int
  type OrderTime = Time
  type CookTime = Time
  type PrevTime = Time
  type Sum = Int
  type Client = (OrderTime, CookTime)

  /**
    * Calculates a time of waiting until the cooking will be started
    *
    * @param prev time
    * @param order time
    * @return time or 0 if the result is negative
    */
  def waitToStart(prev: PrevTime, order: OrderTime): Time = prev - order match {
    case w if w > 0 => w
    case _ => 0
  }

  /**
    * Calculates avg time for arbitrary list of clients.
    * The result can be non-optimal, because the calculation is
    *
    * @param xs list of clients
    * @return avg time
    */
  def avgTime(xs: Seq[Client]): Time = {
    val init = (0: PrevTime, 0: Sum)

    val (_, sum) = xs.foldLeft(init) {
      case ((prev, accSum), (orderTime, cookTime)) =>

        val t1 = orderTime + waitToStart(prev, orderTime) + cookTime
        val total = t1 - orderTime // total time of waiting of pizza for current client

        (t1, accSum + total)
    }

    sum / xs.length
  }

  /**
    * Sorts list of client in optimal order.
    *
    * @param xs list of client
    * @return sorted list
    */
  def sortOptimally(xs: Seq[Client]): Seq[Client] = xs.sortBy(c => c._1 + c._2)

  /**
    * Find minimal avg time for list of clients
    *
    * @param xs list of clients
    * @return min avg time
    */
  def minAvgTime(xs: Seq[Client]): Time = avgTime(sortOptimally(xs))
}

object AvgSolver extends AvgSolver
