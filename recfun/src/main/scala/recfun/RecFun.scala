package recfun

import scala.collection.mutable.ListBuffer

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (r <= 1) 1
    else if (c == 0 || c == r) 1
    else pascal(c -1, r - 1) + pascal(c, r -1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
   if chars.isEmpty then throw IllegalArgumentException("empty is not allowed")
    else if chars.find(_ == '(').isDefined then
      if chars.find(_ == ')').isDefined then
      if chars.head == ')' then false
      else if chars.head == '(' then
        if chars.tail.find(_ == ')').isDefined then
          balance((ListBuffer.empty ++ chars.tail -= ')').toList)
        else false
      else balance(chars.tail)
      else false
    else !chars.find(_ == ')').isDefined

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if coins.isEmpty then 0
    else if money == 0 then 0
    else if money - coins.head == 0 then
      1 + countChange(money, coins.tail)
    else if money - coins.head < 0 then countChange(money, coins.tail)
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
