import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import modepkg.*;

public class Project5Tests {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("Project5Tests");
  }
  //failures: 1,2,3,4,5,6,7,9,10,11,12,13
  //still passing: 8
  
  //used in the below tests
  List<Integer> yy = Arrays.asList(3,3,3,2,2,1,1,1,1,1,2,1,1,1,1,3,4,4,54,1);
  List<Integer> ans = Arrays.asList(3,2,1,4,54);
  
  List<Integer> mode1_2 = Arrays.asList(1,1,1,1,2,2,2,2,3,4,3,5,3); //mode is 1 and 2
  List<Integer> ans1_2 = Arrays.asList(1,2);
  List<Integer> mode_1 = Arrays.asList(3,3,3,2,2,1,1,1,1,1,2,1,1,1,1,3,4,4,54,1); //mode is 1
  List<Integer> ans_1 = Arrays.asList(1);
  
  Pair<Integer,Integer> x = new Pair<Integer,Integer>(1,2);
  Pair<Integer,String> y = new Pair<Integer,String>(-1,"this");
  Pair<String,String> z = new Pair<String,String>("this","this");
  Pair<String,Integer> w = new Pair<String,Integer>("this",4);
  
  Pair<Integer,Integer> p1 = new Pair<Integer,Integer>(1,2);
  Pair<Integer,Integer> p2 = new Pair<Integer,Integer>(22,3);
  Pair<Integer,Integer> p3 = new Pair<Integer,Integer>(0,0);
  Pair<Integer,Integer> p4 = new Pair<Integer,Integer>(43,21);
  Pair<Integer,Integer> p5 = new Pair<Integer,Integer>(10,100);
  Pair<Integer,Integer> p6 = new Pair<Integer,Integer>(8,6);
  
  
  //TESTS FOR PAIR:
  //tests for getfirst and get second
  @Test (timeout=2000) public void get_first_second() { //4
    assertEquals((Integer) 1, x.getFirst());
    assertEquals((Integer) (-1), y.getFirst()); //weird casting for -1
    assertEquals("this", z.getFirst());
    
    assertEquals((Integer) 2, x.getSecond());
    assertEquals("this", y.getSecond());
    assertEquals("this", z.getSecond());
  }
  
  //tests if setfirst and setsecond work getfirst and getsecond work
  @Test (timeout=2000) public void set_first_second() { //no fails
    x.setFirst(4);
    x.setSecond(5);
    
    assertEquals((Integer) 4, x.getFirst());
    assertEquals((Integer) 5, x.getSecond());
  }
  //test toString
  @Test (timeout=2000) public void to_string() { //3,4
    assertEquals("Pair(1,2)", x.toString());
    assertEquals("Pair(-1,this)", y.toString());
    assertEquals("Pair(this,this)", z.toString());
    
    Pair<Object,Object> asdf = new Pair<Object,Object>(null,null);
    assertEquals("Pair(null,null)", asdf.toString());
  }
  
  //test for string only pairs
  @Test (timeout=2000) public void diff_types_to_string() { //3,4
    assertEquals("Pair(-1,this)",y.toString());
    assertEquals("Pair(this,4)",w.toString());
    assertEquals("Pair(this,this)",z.toString());
  }
  
  //test indexbyfirst
  @Test (timeout=2000) public void index_first_second() { //4
    List<Pair<Integer,Integer>> x = Arrays.asList(p1,p2,p3,p4,p5,p6);
    assertEquals(0, Pair.indexByFirst(x,1));
    assertEquals(1, Pair.indexByFirst(x,22));
    
    assertEquals(1, Pair.indexBySecond(x,3));
    assertEquals(5, Pair.indexBySecond(x,6));
  }
  //tests if indexfirst/second return -1 if not found
  @Test (timeout=2000) public void index_first_second_notFound() {
    List<Pair<Integer,Integer>> x = Arrays.asList(p1,p2,p3,p4);
    assertEquals(-1, Pair.indexByFirst(x,10));
    assertEquals(-1, Pair.indexByFirst(x,8));
    
    assertEquals(-1, Pair.indexBySecond(x,100));
    assertEquals(-1, Pair.indexBySecond(x,6));
  }
  //tests the ability to accept null values
  @Test (timeout=2000) public void set_get_null() {
    Pair<Object,Object> xnu = new Pair<Object,Object>(null,null);
    assertEquals(null, xnu.getFirst());
    assertEquals(null, xnu.getSecond());
  }
  
  //TESTS FOR TESTME:
  //tests contains to see if it can correctly identify a pair that is inside x and not inside x
  @Test (timeout=2000) public void contains_1_ints() { //
    List<Integer> yx = Arrays.asList(1,2,3,4,5,53,21,9);
    assertEquals(true, TestMe.contains(yx, 4));
    assertEquals(false, TestMe.contains(yx,2323));
  }
  //tests if contains works with strings
  @Test (timeout=2000) public void contains_2_strings() {
    List<String> yx = Arrays.asList("dfadf","adfafdfa","vvcvxcv","vcv","ewer");
    assertTrue(TestMe.contains(yx, "vcv"));
    assertFalse(TestMe.contains(yx, "adfafegargaa"));
  }
  //check that it can do both types at the same time
  @Test (timeout=2000) public void contains_3_both() {
    List<Object> yx = Arrays.asList(23,"adfafdfa","vvcvxcv","vcv",3232);
    assertEquals(true, TestMe.contains(yx, 23));
    assertEquals(false, TestMe.contains(yx, "adfafegargaa"));
  }
  
   //check that contains works with different lengths of input
  @Test (timeout=2000) public void contains_4_lengthvary() {
    List<Integer> yx = Arrays.asList(1);
    assertEquals(true, TestMe.contains(yx, 1));
    assertEquals(false, TestMe.contains(yx, 100));
    
    assertEquals(true, TestMe.contains(yy, 54));
    assertEquals(false, TestMe.contains(yy, 100));
  }
  
  //check contains with list of pairs
  @Test (timeout=2000) public void contains_5_pairs() {
    List<Pair<Integer,Integer>> xx = Arrays.asList(p1,p2,p3,p4);
    assertTrue(TestMe.contains(xx,p2));
    assertFalse(TestMe.contains(xx,p5));
  }
  //check if it reads null values
  @Test (timeout=2000) public void contains_6_null() {
    List<Integer> nux = Arrays.asList(null,null);
    assertTrue(TestMe.contains(nux,null));
    assertFalse(TestMe.contains(nux,12));
  }
  
  //tests of uniques works with interger list
  @Test (timeout=2000) public void uniques_1() {
    assertEquals(ans, TestMe.uniques(yy));
  }
  //testing an empty list
  @Test (timeout=2000) public void uniques_2_empty() {
    List<Integer> yyy = Arrays.asList();
    List<Integer> eans = Arrays.asList();
    assertEquals(eans, TestMe.uniques(yyy));
  }
  //testing for null value
  @Test (timeout=2000) public void unique_3_null() { //imp1 fails, imp5 fails, imp6 fails,
    List<Integer> nu = Arrays.asList(null,null,null,2,2,null);
    List<Integer> nuans = Arrays.asList(null,2);
    assertEquals(nuans, TestMe.uniques(nu));
  }
  //see if unique works with a List of Pairs
  @Test (timeout=2000) public void uniques_4() {
    List<Pair<Integer,Integer>> px = Arrays.asList(p1,p2,p1,p3,p4,p1,p2,p1,p5,p6,p5);
    
    List<Pair<Integer,Integer>> pans = Arrays.asList(p1,p2,p3,p4,p5,p6);
    assertEquals(pans, TestMe.uniques(px));
  }
  
  //test empty list return null
  @Test (timeout=2000) public void uniques_5() {
    List<Integer> dx = Arrays.asList();
    assertEquals(dx, TestMe.uniques(null));
  }
  
  //tests mode method with integer list, single and multiple answers
  @Test (timeout=2000) public void mode_1() { //9 fails, 11 fails multi
    assertEquals(ans1_2, TestMe.mode(mode1_2));
    assertEquals(ans_1, TestMe.mode(mode_1));
  }
  
  //test mode with arrays, single and multiple answers
  @Test (timeout=2000) public void mode_2() { //11 fails multi, 12 only fails pairs multi
    List<Pair<Integer,Integer>> plist = Arrays.asList(p1,p1,p2,p1,p3,p2,p3,p4,p2,p1,p1);
    List<Pair<Integer,Integer>> pans = Arrays.asList(p1);
    assertEquals(pans, TestMe.mode(plist));
    
    List<Pair<Integer,Integer>> pplist = Arrays.asList(p1,p1,p2,p1,p3,p2,p3,p4,p2,p1,p1,p2,p2);
    List<Pair<Integer,Integer>> ppans = Arrays.asList(p1,p2);
    assertEquals(ppans, TestMe.mode(pplist));
  }
  
  //test with empty list
  @Test (timeout=2000) public void mode_3() { //13 fail exception raised
    List<Integer> emp = Arrays.asList();
    List<Integer> empans = Arrays.asList();
    assertEquals(empans, TestMe.mode(emp));
  }
  //test mode with strings
  @Test (timeout=2000) public void mode_4() {
    List<String> sx = Arrays.asList("dad","mom","crap","dog","dad","mom","dad");
    List<String> sxans = Arrays.asList("dad");
    
    List<String> sx1 = Arrays.asList("dad","mom","crap","dog","dad","mom","dad","crap","crap");
    List<String> sxans1 = Arrays.asList("dad","crap");
  }
  
  //Tests for uniques with null
  @Test (timeout=2000) public void mode_5() {
    List<Object> objlist = Arrays.asList(12, "String", null, 12, "String", null);
    List<Object> anslist = Arrays.asList(12, "String", null);
    assertEquals(anslist, TestMe.mode(objlist));
  }
  
  //tests list of pairs and nulls
  @Test (timeout=2000) public void mode_6() {
   List<Integer> objlist = Arrays.asList((Integer) null,1,3,(Integer) null,(Integer) null, 1,3,2);
   List<Integer> anslist = Arrays.asList((Integer) null,1,3,2);
   List<Integer> alist = Arrays.asList((Integer) null);
   
    assertEquals(anslist, TestMe.uniques(objlist));
    assertEquals(alist, TestMe.mode(objlist));
  }
}
