/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:36:35 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.api.http.response.subject;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary;
import java.util.Collection;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SubjectMetricsPerTermSummary_ESTest extends SubjectMetricsPerTermSummary_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary((String) null, (String) null, (String) null, (String) null, (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectName();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("!7t 4$8 Wcq]", "DZi1;L((n;*L&,,a", "", "", (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectName();
      assertEquals("", string0);
      assertEquals("", subjectMetricsPerTermSummary0.getSubjectCode());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("", (String) null, (String) null, (String) null, (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectCode();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("*t", "*t", "", "ix='f+|}.9?|7Yz^caH", (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectCode();
      assertEquals("", string0);
      assertEquals("ix='f+|}.9?|7Yz^caH", subjectMetricsPerTermSummary0.getSubjectName());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("QjQ{SO<~e56", "", "", "QjQ{SO<~e56", (Collection<SubjectMetricsPerTerm>) null);
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary1 = new SubjectMetricsPerTermSummary("cJPS!", "cJPS!", "#AG[fX.^37bAPxxY=~", "", (Collection<SubjectMetricsPerTerm>) null);
      int int0 = subjectMetricsPerTermSummary0.compareTo(subjectMetricsPerTermSummary1);
      assertEquals("#AG[fX.^37bAPxxY=~", subjectMetricsPerTermSummary1.getSubjectCode());
      assertEquals(11, int0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("f$S$5L_.#LhZnIW8zU", "f$S$5L_.#LhZnIW8zU", "!q7rN\"i{|Ax/WV8?}O", "f$S$5L_.#LhZnIW8zU", (Collection<SubjectMetricsPerTerm>) null);
      assertEquals("f$S$5L_.#LhZnIW8zU", subjectMetricsPerTermSummary0.getSubjectName());
      
      subjectMetricsPerTermSummary0.setSubjectName("cwM)wi.\"X_VhM");
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary1 = new SubjectMetricsPerTermSummary("", "!q7rN\"i{|Ax/WV8?}O", "", "f$S$5L_.#LhZnIW8zU", (Collection<SubjectMetricsPerTerm>) null);
      int int0 = subjectMetricsPerTermSummary0.compareTo(subjectMetricsPerTermSummary1);
      assertEquals((-3), int0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("*t", "*t", "", "ix='f+|}.9?|7Yz^caH", (Collection<SubjectMetricsPerTerm>) null);
      // Undeclared exception!
      try { 
        subjectMetricsPerTermSummary0.compareTo("");
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "}wBYj_R%", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectName();
      assertEquals("}wBYj_R%", subjectMetricsPerTermSummary0.getSubjectCode());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", (Collection<SubjectMetricsPerTerm>) null);
      subjectMetricsPerTermSummary0.setTerms((Collection<SubjectMetricsPerTerm>) null);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", subjectMetricsPerTermSummary0.getFrom());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTerm", (Collection<SubjectMetricsPerTerm>) null);
      Collection<SubjectMetricsPerTerm> collection0 = subjectMetricsPerTermSummary0.getTerms();
      assertNull(collection0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "}wBYj_R%", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", (Collection<SubjectMetricsPerTerm>) null);
      String string0 = subjectMetricsPerTermSummary0.getSubjectCode();
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", subjectMetricsPerTermSummary0.getSubjectName());
      assertEquals("}wBYj_R%", string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "}wBYj_R%", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", (Collection<SubjectMetricsPerTerm>) null);
      subjectMetricsPerTermSummary0.compareTo(subjectMetricsPerTermSummary0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", subjectMetricsPerTermSummary0.getSubjectName());
      assertEquals("}wBYj_R%", subjectMetricsPerTermSummary0.getSubjectCode());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "}wBYj_R%", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", (Collection<SubjectMetricsPerTerm>) null);
      subjectMetricsPerTermSummary0.setSubjectName((String) null);
      // Undeclared exception!
      try { 
        subjectMetricsPerTermSummary0.compareTo(subjectMetricsPerTermSummary0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      SubjectMetricsPerTermSummary subjectMetricsPerTermSummary0 = new SubjectMetricsPerTermSummary("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", "}wBYj_R%", "br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", (Collection<SubjectMetricsPerTerm>) null);
      assertEquals("}wBYj_R%", subjectMetricsPerTermSummary0.getSubjectCode());
      
      subjectMetricsPerTermSummary0.setSubjectCode("m'<l");
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.subject.SubjectMetricsPerTermSummary", subjectMetricsPerTermSummary0.getSubjectName());
  }
}
