package patmat

class HuffmanSuite extends munit.FunSuite:
  import Huffman.*

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }


  test("weight of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(weight(t1), 5)
  }


  test("chars of a larger tree (10pts)") {
    new TestTrees:
      assertEquals(chars(t2), List('a','b','d'))
  }

  test("string2chars hello world") {
    assertEquals(string2Chars("hello, world"), List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }


  test("make ordered leaf list for Nil") {
    assertEquals(makeOrderedLeafList(Nil), Nil)
  }

  test("make ordered leaf list for some frequency table (15pts)") {
    assertEquals(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))), List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list (15pts)") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(combine(leaflist), List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("create code tree") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    println(createCodeTree("qqweriortyo".toList))
    assertEquals(1, 1)
  }

  test("decode codeTree") {
    new TestTrees:
      val a = decode(t2, List(1, 0, 0))
      println(t2.chars)
      println(a)

    val t3 = Fork(Fork(
      Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5),
      Leaf('d',4),
      List('a','b','d'),
      9),
      Fork(Leaf('l', 9), Leaf('q', 1), List('l', 'q'), 10),
      List('a','b','d', 'l', 'q'),
      19
    )
    val b = decode(t3, List(1, 0,1,1, 0, 0, 0))

    println(t3.chars)
    println(b)

    Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5),
      Leaf('d',4),
      List('a','b','d'),
      9)
  }

  test("decode secret") {
    val w = weight(frenchCode)
    println(w)
    println(decodedSecret)
  }


  test("decode and encode a very short text should be identity (10pts)") {
    new TestTrees:
      assertEquals(decode(t1, encode(t1)("ab".toList)), "ab".toList)
      assertEquals(decode(t1, quickEncode(t1)("ab".toList)), "ab".toList)
  }


  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
