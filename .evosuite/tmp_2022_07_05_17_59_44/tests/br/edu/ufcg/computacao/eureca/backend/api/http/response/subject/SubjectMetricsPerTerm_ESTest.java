/*
 * This file was automatically generated by EvoSuite
 * Tue Jul 05 23:25:55 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetrics;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SubjectMetricsPerTerm_ESTest extends SubjectMetricsPerTerm_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", (SubjectMetrics) null);
      subjectMetricsPerTerm0.setTerm((String) null);
      String string0 = subjectMetricsPerTerm0.getTerm();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", (SubjectMetrics) null);
      String string0 = subjectMetricsPerTerm0.getTerm();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("|+p9z-GM~`D", (SubjectMetrics) null);
      SubjectMetrics subjectMetrics0 = subjectMetricsPerTerm0.getMetrics();
      assertNull(subjectMetrics0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      SubjectMetrics subjectMetrics0 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", subjectMetrics0);
      subjectMetricsPerTerm0.setTerm("hs6U51dHUm");
      SubjectMetrics subjectMetrics1 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm1 = new SubjectMetricsPerTerm("", subjectMetrics1);
      int int0 = subjectMetricsPerTerm0.compareTo(subjectMetricsPerTerm1);
      assertEquals(10, int0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("|+p9z-GM~`D", (SubjectMetrics) null);
      subjectMetricsPerTerm0.setTerm(", ongoing=");
      SubjectMetricsPerTerm subjectMetricsPerTerm1 = new SubjectMetricsPerTerm("?", (SubjectMetrics) null);
      int int0 = subjectMetricsPerTerm0.compareTo(subjectMetricsPerTerm1);
      assertEquals((-19), int0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", (SubjectMetrics) null);
      // Undeclared exception!
      try { 
        subjectMetricsPerTerm0.compareTo((Object) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", e);
      }
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      SubjectMetrics subjectMetrics0 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("K$w", subjectMetrics0);
      // Undeclared exception!
      try { 
        subjectMetricsPerTerm0.compareTo("");
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", e);
      }
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      SubjectMetrics subjectMetrics0 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("K$w", subjectMetrics0);
      String string0 = subjectMetricsPerTerm0.getTerm();
      assertEquals("K$w", string0);
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      SubjectMetrics subjectMetrics0 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", subjectMetrics0);
      subjectMetricsPerTerm0.setMetrics(subjectMetrics0);
      assertEquals("", subjectMetricsPerTerm0.getTerm());
  }

  @Test(timeout = 4000)
  public void test9()  throws Throwable  {
      SubjectMetrics subjectMetrics0 = mock(SubjectMetrics.class, new ViolatedAssumptionAnswer());
      SubjectMetricsPerTerm subjectMetricsPerTerm0 = new SubjectMetricsPerTerm("", subjectMetrics0);
      int int0 = subjectMetricsPerTerm0.compareTo(subjectMetricsPerTerm0);
      assertEquals(0, int0);
  }
}
