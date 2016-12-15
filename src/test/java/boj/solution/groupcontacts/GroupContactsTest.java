package boj.solution.groupcontacts;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Ran Bi (ran.bi@addepar.com)
 */
public class GroupContactsTest {

  @Rule
  public final Timeout globalTimeout = Timeout.seconds(1);
  private final GroupContacts solver = new GroupContacts();

  @Test
  public void test() {
    GroupContacts.Contact con1 = newContact("JohnS", "john@gmail.com");
    GroupContacts.Contact con2 = newContact("Mary", "mary@gmail.com");
    GroupContacts.Contact con3 = newContact("John", "john@yahoo.com");
    GroupContacts.Contact con4 = newContact("John", "john@gmail.com", "john@yahoo.com", "john@hotmail.com");
    GroupContacts.Contact con5 = newContact("Bob", "bob@gmail.com");
    assertEquals(newSet(newSet(con1, con3, con4), newSet(con2), newSet(con5)),
        solver.solve(Arrays.asList(con1, con2, con3, con4, con5)));
  }

  @Test
  public void test2() {
    assertEquals(newSet(),
        solver.solve(Collections.emptyList()));
  }

  @Test
  public void test3() {
    GroupContacts.Contact con1 = newContact("JohnS", "john@gmail.com", "bob@gmail.com");
    GroupContacts.Contact con2 = newContact("Mary", "mary@gmail.com");
    GroupContacts.Contact con3 = newContact("John", "john@yahoo.com", "mary@gmail.com");
    GroupContacts.Contact con4 = newContact("John", "john@gmail.com", "john@yahoo.com", "john@hotmail.com");
    GroupContacts.Contact con5 = newContact("Bob", "bob@gmail.com");
    assertEquals(newSet(newSet(con1, con3, con4, con2, con5)),
        solver.solve(Arrays.asList(con1, con2, con3, con4, con5)));
  }

  @Test
  public void test4() {
    GroupContacts.Contact con1 = newContact("A", "A@gmail.com", "B@gmail.com");
    GroupContacts.Contact con2 = newContact("B", "B@gmail.com", "C@gmail.com");
    GroupContacts.Contact con3 = newContact("C", "C@gmail.com", "D@gmail.com");
    GroupContacts.Contact con4 = newContact("D", "D@gmail.com", "E@gmail.com");
    GroupContacts.Contact con5 = newContact("E", "E@gmail.com", "F@gmail.com");
    assertEquals(newSet(newSet(con1, con3, con4, con2, con5)),
        solver.solve(Arrays.asList(con1, con2, con3, con4, con5)));
  }

  @Test
  public void test5() {
    GroupContacts.Contact con1 = newContact("A", "A@gmail.com", "B@gmail.com");
    GroupContacts.Contact con2 = newContact("B", "B@gmail.com", "C@gmail.com");
    GroupContacts.Contact con3 = newContact("C", "C@gmail.com", "A@gmail.com");
    GroupContacts.Contact con4 = newContact("D", "D@gmail.com", "E@gmail.com");
    GroupContacts.Contact con5 = newContact("E", "E@gmail.com", "D@gmail.com");
    assertEquals(newSet(newSet(con1, con2, con3), newSet(con4, con5)),
        solver.solve(Arrays.asList(con1, con2, con3, con4, con5)));
  }

  @Test
  public void test6() {
    GroupContacts.Contact con1 = newContact("A", "A@gmail.com");
    GroupContacts.Contact con2 = newContact("B", "B@gmail.com");
    GroupContacts.Contact con3 = newContact("C", "C@gmail.com");
    GroupContacts.Contact con4 = newContact("D", "D@gmail.com");
    GroupContacts.Contact con5 = newContact("E", "E@gmail.com");
    assertEquals(newSet(newSet(con1), newSet(con2), newSet(con3), newSet(con4), newSet(con5)),
        solver.solve(Arrays.asList(con1, con2, con3, con4, con5)));
  }

  private static GroupContacts.Contact newContact(String name, String... emails) {
    return new GroupContacts.Contact(name, Arrays.asList(emails));
  }

  private static <T> Set<T> newSet(T... arr) {
    Set<T> res = new HashSet<T>();
    for (T v : arr) {
      res.add(v);
    }
    return res;
  }
}
