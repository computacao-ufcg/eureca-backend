/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:35:38 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutReasonSummary;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class DropoutPerTermSummary_ESTest extends DropoutPerTermSummary_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 0, (DropoutReasonSummary) null, 0, 0);
      dropoutPerTermSummary0.getTerm();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("1A/%&Yi", 0, (DropoutReasonSummary) null, 0, 0);
      dropoutPerTermSummary0.setDropoutTerm("");
      dropoutPerTermSummary0.getTerm();
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 0, (DropoutReasonSummary) null, 0, 0);
      dropoutPerTermSummary0.getReasons();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("1A/%&Yi", 0, (DropoutReasonSummary) null, 0, 0);
      dropoutPerTermSummary0.getDropoutTerm();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("", 0, (DropoutReasonSummary) null, 0, 1109.31418582);
      dropoutPerTermSummary0.getDropoutTerm();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1109.31418582, dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutReasonSummary", 0, (DropoutReasonSummary) null, 0.0, (-1225.612708465343));
      int int0 = dropoutPerTermSummary0.getDropoutCount();
      assertEquals((-1225.612708465343), dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("BRVc1.YhtUu9*<*", (-3116), (DropoutReasonSummary) null, 0.0, (-3116));
      int int0 = dropoutPerTermSummary0.getDropoutCount();
      assertEquals((-3116), int0);
      assertEquals((-3116.0), dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("Pv4B$np*eI\"@[2q>", (-1272), (DropoutReasonSummary) null, 1677.62890076708, 1.0);
      double double0 = dropoutPerTermSummary0.getAverageTerms();
      assertEquals((-1272), dropoutPerTermSummary0.getDropoutCount());
      assertEquals(1677.62890076708, double0, 0.01);
      assertEquals(1.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("jXv(==zI%l\"?", 1025, (DropoutReasonSummary) null, 0.0, 0.0);
      double double0 = dropoutPerTermSummary0.getAverageCost();
      assertEquals(1025, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, double0, 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("rsNqUp#A/ 7k,Xs", 1628, (DropoutReasonSummary) null, 1.0, 0.0);
      dropoutPerTermSummary0.setAverageCost((-851.9));
      double double0 = dropoutPerTermSummary0.getAverageCost();
      assertEquals((-851.9), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("", 0, (DropoutReasonSummary) null, 0, 1109.31418582);
      dropoutPerTermSummary0.compareTo(dropoutPerTermSummary0);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1109.31418582, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("V%1", 1, dropoutReasonSummary0, 1, 1);
      DropoutReasonSummary dropoutReasonSummary1 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary1 = new DropoutPerTermSummary("", 1429, dropoutReasonSummary1, (-162.97742928439263), 1429);
      int int0 = dropoutPerTermSummary0.compareTo(dropoutPerTermSummary1);
      assertEquals((-162.97742928439263), dropoutPerTermSummary1.getAverageTerms(), 0.01);
      assertEquals(3, int0);
      assertEquals(1429, dropoutPerTermSummary1.getDropoutCount());
      assertEquals(1429.0, dropoutPerTermSummary1.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("BRVc1.YhtUu9*<*", (-3116), (DropoutReasonSummary) null, 0.0, (-3116));
      DropoutPerTermSummary dropoutPerTermSummary1 = new DropoutPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutReasonSummary", (-3116), (DropoutReasonSummary) null, 918.7405, 0.0);
      int int0 = dropoutPerTermSummary0.compareTo(dropoutPerTermSummary1);
      assertEquals(0.0, dropoutPerTermSummary1.getAverageCost(), 0.01);
      assertEquals((-32), int0);
      assertEquals(918.7405, dropoutPerTermSummary1.getAverageTerms(), 0.01);
      assertEquals((-3116), dropoutPerTermSummary1.getDropoutCount());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      // Undeclared exception!
      try { 
        dropoutPerTermSummary0.compareTo(",'xwVxu");
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      dropoutPerTermSummary0.getDropoutTerm();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1983, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(1334.2133006113, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      dropoutPerTermSummary0.setDropoutCount(1983);
      assertEquals(1983, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1334.2133006113, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      int int0 = dropoutPerTermSummary0.getDropoutCount();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1983, int0);
      assertEquals(1334.2133006113, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 0, dropoutReasonSummary0, 0, 0.0);
      double double0 = dropoutPerTermSummary0.getAverageTerms();
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
      assertEquals(0.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 0, dropoutReasonSummary0, 0, 0.0);
      DropoutReasonSummary dropoutReasonSummary1 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      dropoutPerTermSummary0.setReasons(dropoutReasonSummary1);
      assertEquals(0, dropoutPerTermSummary0.getDropoutCount());
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(0.0, dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      dropoutPerTermSummary0.setAverageTerms((-1.0));
      double double0 = dropoutPerTermSummary0.getAverageTerms();
      assertEquals((-1.0), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 1983, dropoutReasonSummary0, 0.0, 1334.2133006113);
      double double0 = dropoutPerTermSummary0.getAverageCost();
      assertEquals(0.0, dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals(1334.2133006113, double0, 0.01);
      assertEquals(1983, dropoutPerTermSummary0.getDropoutCount());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary("a5", (-29), dropoutReasonSummary0, (-29), (-29));
      dropoutPerTermSummary0.getTerm();
      assertEquals((-29), dropoutPerTermSummary0.getDropoutCount());
      assertEquals((-29.0), dropoutPerTermSummary0.getAverageTerms(), 0.01);
      assertEquals((-29.0), dropoutPerTermSummary0.getAverageCost(), 0.01);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      DropoutReasonSummary dropoutReasonSummary0 = mock(DropoutReasonSummary.class, new ViolatedAssumptionAnswer());
      DropoutPerTermSummary dropoutPerTermSummary0 = new DropoutPerTermSummary((String) null, 0, dropoutReasonSummary0, 0, 0.0);
      // Undeclared exception!
      try { 
        dropoutPerTermSummary0.compareTo(dropoutPerTermSummary0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.dropout.DropoutPerTermSummary", e);
      }
  }
}
