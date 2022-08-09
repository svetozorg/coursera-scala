package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
    assert(!contains(i => i > 100, 99))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(1000)
    val s5 = singletonSet(-1000)

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {
    

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s2, 5))
      assert(s3(3), "Singleton3")
      assertEquals(s3(5), false)
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("forall") {
    new TestSets:
      val s = union(s1, s2)
      assertEquals(forall(s, x => x < 0), false)
      assert(forall(s, x => x > 0))
  }

  test("exists") {
    new TestSets:
      val s = union(s3, union(s1, s2))
      assert(exists(s, x => x > 2))
      assert(!exists(s, _ > 3))
  }

  test("map") {
    new TestSets:
      val s = union(union(s3, union(s1, s2)), s4)
      val ss = union(s, s5)
      assert(forall(map(s, i => i + 1), _ > 1))
      assert(exists(ss, _ == -1000 ))
      assert(exists(ss, _ == 1000 ))
  }

  test("diff") {
    new TestSets:
      val set1 = union(union(s3, union(s1, s2)), s4)
      val set2 = union(set1, s5)
      printSet(set1)
      printSet(set2)
      assertEquals(FunSets.toString(diff(set2, set1)), "{-1000}")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
