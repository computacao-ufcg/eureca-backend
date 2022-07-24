/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:29:58 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TeacherCSV_ESTest extends TeacherCSV_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV((String) null, "b0E@o.9a1OQ@", "", (String) null, (String) null, teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertEquals("", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTeacherId());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("b0E@o.9a1OQ@", teacherCSV0.getTeacherName());
      
      String string0 = teacherCSV0.getTerm();
      assertNull(string0);
      assertEquals("", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTeacherId());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("b0E@o.9a1OQ@", teacherCSV0.getTeacherName());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("SGx1iJqB8So", "SGx1iJqB8So", "SGx1iJqB8So", "[RClY", "[RClY", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
      assertEquals("[RClY", teacherCSV0.getTerm());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
      
      teacherCSV0.setTerm("");
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getTerm());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
      
      String string0 = teacherCSV0.getTerm();
      assertNotNull(string0);
      assertEquals("", string0);
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getTerm());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("%", "%", "%", "%", "k40Jn[$Jmr+", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("%", teacherCSV0.getCurriculumCode());
      assertEquals("k40Jn[$Jmr+", teacherCSV0.getTerm());
      assertEquals("%", teacherCSV0.getTeacherName());
      assertEquals("%", teacherCSV0.getTeacherId());
      assertEquals("%", teacherCSV0.getCourseCode());
      
      String string0 = teacherCSV0.getTeacherName();
      assertNotNull(string0);
      assertEquals("%", string0);
      assertEquals("%", teacherCSV0.getCurriculumCode());
      assertEquals("k40Jn[$Jmr+", teacherCSV0.getTerm());
      assertEquals("%", teacherCSV0.getTeacherName());
      assertEquals("%", teacherCSV0.getTeacherId());
      assertEquals("%", teacherCSV0.getCourseCode());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("{", (String) null, "{", (String) null, (String) null, (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("{", teacherCSV0.getTeacherId());
      assertEquals("{", teacherCSV0.getCourseCode());
      
      teacherCSV0.setTeacherName("");
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("", teacherCSV0.getTeacherName());
      assertEquals("{", teacherCSV0.getTeacherId());
      assertEquals("{", teacherCSV0.getCourseCode());
      
      String string0 = teacherCSV0.getTeacherName();
      assertNotNull(string0);
      assertEquals("", string0);
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("", teacherCSV0.getTeacherName());
      assertEquals("{", teacherCSV0.getTeacherId());
      assertEquals("{", teacherCSV0.getCourseCode());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV((String) null, "b0E@o.9a1OQ@", "", (String) null, (String) null, teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("b0E@o.9a1OQ@", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherId());
      
      String string0 = teacherCSV0.getTeacherId();
      assertNull(string0);
      assertNull(teacherCSV0.getCurriculumCode());
      assertEquals("b0E@o.9a1OQ@", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherId());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("SGx1iJqB8So", "SGx1iJqB8So", "SGx1iJqB8So", "[RClY", "[RClY", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
      assertEquals("[RClY", teacherCSV0.getTerm());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
      
      String string0 = teacherCSV0.getTeacherId();
      assertNotNull(string0);
      assertEquals("SGx1iJqB8So", string0);
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
      assertEquals("[RClY", teacherCSV0.getTerm());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherId());
      assertEquals("", teacherCSV0.getTerm());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCurriculumCode());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherName());
      
      TeacherStatisticsSummary teacherStatisticsSummary0 = teacherCSV0.getMetrics();
      assertNull(teacherStatisticsSummary0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherId());
      assertEquals("", teacherCSV0.getTerm());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCurriculumCode());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherName());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("{", (String) null, "{", (String) null, (String) null, (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("{", teacherCSV0.getTeacherId());
      assertEquals("{", teacherCSV0.getCourseCode());
      
      String string0 = teacherCSV0.getCurriculumCode();
      assertNull(string0);
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("{", teacherCSV0.getTeacherId());
      assertEquals("{", teacherCSV0.getCourseCode());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherId());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTerm());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getCurriculumCode());
      assertEquals("", teacherCSV0.getTeacherName());
      
      String string0 = teacherCSV0.getCurriculumCode();
      assertNotNull(string0);
      assertEquals("", string0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherId());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTerm());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getCurriculumCode());
      assertEquals("", teacherCSV0.getTeacherName());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV((String) null, "", (String) null, (String) null, "", teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertNull(teacherCSV0.getTeacherId());
      assertNull(teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
      
      String string0 = teacherCSV0.getCourseCode();
      assertNull(string0);
      assertNull(teacherCSV0.getTeacherId());
      assertNull(teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getTerm());
      assertNull(teacherCSV0.getCurriculumCode());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("", (String) null, "", "3Xmn_>7EHdFh5|Z3v", "3Xmn_>7EHdFh5|Z3v", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherId());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getCurriculumCode());
      
      String string0 = teacherCSV0.getCourseCode();
      assertNotNull(string0);
      assertEquals("", string0);
      assertEquals("", teacherCSV0.getCourseCode());
      assertEquals("", teacherCSV0.getTeacherId());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getCurriculumCode());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("xX\"~IoQ?lxi(7gzO<L", "xX\"~IoQ?lxi(7gzO<L", "giQ{]8", "xX\"~IoQ?lxi(7gzO<L", ";,Xz1|y43ssP", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals(";,Xz1|y43ssP", teacherCSV0.getTerm());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherId());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getCurriculumCode());
      assertEquals("giQ{]8", teacherCSV0.getCourseCode());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherName());
      
      TeacherCSV teacherCSV1 = new TeacherCSV("R--cWI&$\"'", ", curriculumCode='", ", term='", "%n?FkjmVB/Tp';[", "", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV1);
      assertFalse(teacherCSV1.equals((Object)teacherCSV0));
      assertEquals(", term='", teacherCSV1.getCourseCode());
      assertEquals("", teacherCSV1.getTerm());
      assertEquals(", curriculumCode='", teacherCSV1.getTeacherName());
      assertEquals("R--cWI&$\"'", teacherCSV1.getTeacherId());
      assertEquals("%n?FkjmVB/Tp';[", teacherCSV1.getCurriculumCode());
      
      int int0 = teacherCSV0.compareTo(teacherCSV1);
      assertEquals(38, int0);
      assertFalse(teacherCSV0.equals((Object)teacherCSV1));
      assertFalse(teacherCSV1.equals((Object)teacherCSV0));
      assertEquals(";,Xz1|y43ssP", teacherCSV0.getTerm());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherId());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getCurriculumCode());
      assertEquals("giQ{]8", teacherCSV0.getCourseCode());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherName());
      assertEquals(", term='", teacherCSV1.getCourseCode());
      assertEquals("", teacherCSV1.getTerm());
      assertEquals(", curriculumCode='", teacherCSV1.getTeacherName());
      assertEquals("R--cWI&$\"'", teacherCSV1.getTeacherId());
      assertEquals("%n?FkjmVB/Tp';[", teacherCSV1.getCurriculumCode());
      assertNotSame(teacherCSV0, teacherCSV1);
      assertNotSame(teacherCSV1, teacherCSV0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("xX\"~IoQ?lxi(7gzO<L", "xX\"~IoQ?lxi(7gzO<L", "giQ{]8", "xX\"~IoQ?lxi(7gzO<L", ";,Xz1|y43ssP", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherName());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getCurriculumCode());
      assertEquals("giQ{]8", teacherCSV0.getCourseCode());
      assertEquals(";,Xz1|y43ssP", teacherCSV0.getTerm());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherId());
      
      teacherCSV0.setTeacherId(", term='");
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherName());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getCurriculumCode());
      assertEquals("giQ{]8", teacherCSV0.getCourseCode());
      assertEquals(";,Xz1|y43ssP", teacherCSV0.getTerm());
      assertEquals(", term='", teacherCSV0.getTeacherId());
      
      TeacherCSV teacherCSV1 = new TeacherCSV("R--cWI&$\"'", ", curriculumCode='", ", term='", "%n?FkjmVB/Tp';[", "", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV1);
      assertFalse(teacherCSV1.equals((Object)teacherCSV0));
      assertEquals(", term='", teacherCSV1.getCourseCode());
      assertEquals(", curriculumCode='", teacherCSV1.getTeacherName());
      assertEquals("", teacherCSV1.getTerm());
      assertEquals("%n?FkjmVB/Tp';[", teacherCSV1.getCurriculumCode());
      assertEquals("R--cWI&$\"'", teacherCSV1.getTeacherId());
      
      int int0 = teacherCSV0.compareTo(teacherCSV1);
      assertEquals((-38), int0);
      assertFalse(teacherCSV0.equals((Object)teacherCSV1));
      assertFalse(teacherCSV1.equals((Object)teacherCSV0));
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getTeacherName());
      assertEquals("xX\"~IoQ?lxi(7gzO<L", teacherCSV0.getCurriculumCode());
      assertEquals("giQ{]8", teacherCSV0.getCourseCode());
      assertEquals(";,Xz1|y43ssP", teacherCSV0.getTerm());
      assertEquals(", term='", teacherCSV0.getTeacherId());
      assertEquals(", term='", teacherCSV1.getCourseCode());
      assertEquals(", curriculumCode='", teacherCSV1.getTeacherName());
      assertEquals("", teacherCSV1.getTerm());
      assertEquals("%n?FkjmVB/Tp';[", teacherCSV1.getCurriculumCode());
      assertEquals("R--cWI&$\"'", teacherCSV1.getTeacherId());
      assertNotSame(teacherCSV0, teacherCSV1);
      assertNotSame(teacherCSV1, teacherCSV0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("SGx1iJqB8So", "SGx1iJqB8So", "SGx1iJqB8So", "[RClY", "[RClY", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherName());
      assertEquals("SGx1iJqB8So", teacherCSV0.getTeacherId());
      assertEquals("[RClY", teacherCSV0.getTerm());
      assertEquals("[RClY", teacherCSV0.getCurriculumCode());
      assertEquals("SGx1iJqB8So", teacherCSV0.getCourseCode());
      
      // Undeclared exception!
      try { 
        teacherCSV0.compareTo((Object) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherCSV", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      TeacherCSV teacherCSV0 = new TeacherCSV("", (String) null, "", "3Xmn_>7EHdFh5|Z3v", "3Xmn_>7EHdFh5|Z3v", (TeacherStatisticsSummary) null);
      assertNotNull(teacherCSV0);
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getCurriculumCode());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getCourseCode());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getTerm());
      assertEquals("", teacherCSV0.getTeacherId());
      
      String string0 = teacherCSV0.getTeacherId();
      assertNotNull(string0);
      assertEquals("", string0);
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getCurriculumCode());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getCourseCode());
      assertEquals("3Xmn_>7EHdFh5|Z3v", teacherCSV0.getTerm());
      assertEquals("", teacherCSV0.getTeacherId());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCurriculumCode());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherId());
      
      String string0 = teacherCSV0.getCourseCode();
      assertNotNull(string0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", string0);
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
      assertNull(teacherCSV0.getTerm());
      assertNull(teacherCSV0.getTeacherName());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCurriculumCode());
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getTeacherId());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("4", "4", "4", "4", "4", teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertEquals("4", teacherCSV0.getCurriculumCode());
      assertEquals("4", teacherCSV0.getTeacherName());
      assertEquals("4", teacherCSV0.getTeacherId());
      assertEquals("4", teacherCSV0.getCourseCode());
      assertEquals("4", teacherCSV0.getTerm());
      
      int int0 = teacherCSV0.compareTo(teacherCSV0);
      assertEquals(0, int0);
      assertEquals("4", teacherCSV0.getCurriculumCode());
      assertEquals("4", teacherCSV0.getTeacherName());
      assertEquals("4", teacherCSV0.getTeacherId());
      assertEquals("4", teacherCSV0.getCourseCode());
      assertEquals("4", teacherCSV0.getTerm());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("4", "4", "4", "4", "4", teacherStatisticsSummary0);
      assertNotNull(teacherCSV0);
      assertEquals("4", teacherCSV0.getTerm());
      assertEquals("4", teacherCSV0.getCourseCode());
      assertEquals("4", teacherCSV0.getTeacherId());
      assertEquals("4", teacherCSV0.getTeacherName());
      assertEquals("4", teacherCSV0.getCurriculumCode());
      
      String string0 = teacherCSV0.getCurriculumCode();
      assertNotNull(string0);
      assertEquals("4", string0);
      assertEquals("4", teacherCSV0.getTerm());
      assertEquals("4", teacherCSV0.getCourseCode());
      assertEquals("4", teacherCSV0.getTeacherId());
      assertEquals("4", teacherCSV0.getTeacherName());
      assertEquals("4", teacherCSV0.getCurriculumCode());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, teacherStatisticsSummary0);
      teacherCSV0.setCourseCode("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary");
      assertEquals("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", teacherCSV0.getCourseCode());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, teacherStatisticsSummary0);
      String string0 = teacherCSV0.getTeacherName();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", "br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherStatisticsSummary", (String) null, teacherStatisticsSummary0);
      // Undeclared exception!
      try { 
        teacherCSV0.compareTo("F|EBn");
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.lang.String cannot be cast to br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherCSV
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.api.http.response.teacher.TeacherCSV", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(teacherStatisticsSummary0).toString();
      TeacherCSV teacherCSV0 = new TeacherCSV("", "", "", "j~d", "j~d", teacherStatisticsSummary0);
      String string0 = teacherCSV0.toString();
      assertEquals("TeacherCSV{teacherId='', teacherName='', courseCode='', curriculumCode='j~d', term='j~d', metrics=null}", string0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("", "", "", "j~d", "j~d", teacherStatisticsSummary0);
      TeacherStatisticsSummary teacherStatisticsSummary1 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      teacherCSV0.setMetrics(teacherStatisticsSummary1);
      assertEquals("j~d", teacherCSV0.getTerm());
      assertEquals("", teacherCSV0.getTeacherId());
      assertEquals("j~d", teacherCSV0.getCurriculumCode());
      assertEquals("", teacherCSV0.getTeacherName());
      assertEquals("", teacherCSV0.getCourseCode());
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("4", "4", "4", "4", "4", teacherStatisticsSummary0);
      teacherCSV0.setCurriculumCode("TeacherCSV{teacherId='4', teacherName='4', courseCode='4', curriculumCode='4', term='4', metrics=4}");
      assertEquals("4", teacherCSV0.getTerm());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      TeacherStatisticsSummary teacherStatisticsSummary0 = mock(TeacherStatisticsSummary.class, new ViolatedAssumptionAnswer());
      TeacherCSV teacherCSV0 = new TeacherCSV("AH]w@d3z6\"]", "AH]w@d3z6\"]", "AH]w@d3z6\"]", "AH]w@d3z6\"]", "AH]w@d3z6\"]", teacherStatisticsSummary0);
      String string0 = teacherCSV0.getTerm();
      assertNotNull(string0);
  }
}
