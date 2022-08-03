/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:26:33 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.TeachersSetAndEnrollments;
import br.edu.ufcg.computacao.eureca.backend.core.models.ClassEnrollments;
import java.util.Set;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class TeachersSetAndEnrollments_ESTest extends TeachersSetAndEnrollments_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-21), (-21), (-21), (-21));
      ClassEnrollments classEnrollments0 = mock(ClassEnrollments.class, new ViolatedAssumptionAnswer());
      doReturn((-21)).when(classEnrollments0).getNumberFailedDueToAbsence();
      doReturn((-21)).when(classEnrollments0).getNumberFailedDueToGrade();
      doReturn((-21)).when(classEnrollments0).getNumberSucceeded();
      doReturn((-21)).when(classEnrollments0).getNumberSuspended();
      teachersSetAndEnrollments0.addEnrollments(classEnrollments0);
      assertEquals((-42), teachersSetAndEnrollments0.getSucceededCount());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1), 0, (-944), 0);
      int int0 = teachersSetAndEnrollments0.getSuspendedCount();
      assertEquals((-944), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals(0, int0);
      assertEquals(0, teachersSetAndEnrollments0.getFailedDueToGradeCount());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 573, 0, 0, 1);
      int int0 = teachersSetAndEnrollments0.getSuspendedCount();
      assertEquals(0, teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals(1, int0);
      assertEquals(573, teachersSetAndEnrollments0.getSucceededCount());
      assertEquals(0, teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 0, (-3256), 0, (-3256));
      int int0 = teachersSetAndEnrollments0.getSucceededCount();
      assertEquals(0, teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals(0, int0);
      assertEquals((-3256), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-3256), teachersSetAndEnrollments0.getSuspendedCount());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 1931, 4350, 1931, 4350);
      int int0 = teachersSetAndEnrollments0.getSucceededCount();
      assertEquals(1931, int0);
      assertEquals(4350, teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals(4350, teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals(1931, teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 0, 0, 0, (-849));
      int int0 = teachersSetAndEnrollments0.getFailedDueToGradeCount();
      assertEquals((-849), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals(0, int0);
      assertEquals(0, teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals(0, teachersSetAndEnrollments0.getSucceededCount());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 1931, 4350, 1931, 4350);
      int int0 = teachersSetAndEnrollments0.getFailedDueToGradeCount();
      assertEquals(1931, teachersSetAndEnrollments0.getSucceededCount());
      assertEquals(4350, teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals(1931, teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals(4350, int0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1955), 502, 0, (-2050));
      int int0 = teachersSetAndEnrollments0.getFailedDueToAbsenceCount();
      assertEquals((-1955), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals(0, int0);
      assertEquals((-2050), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals(502, teachersSetAndEnrollments0.getFailedDueToGradeCount());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 1931, 4350, 1931, 4350);
      int int0 = teachersSetAndEnrollments0.getFailedDueToAbsenceCount();
      assertEquals(4350, teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals(1931, teachersSetAndEnrollments0.getSucceededCount());
      assertEquals(1931, int0);
      assertEquals(4350, teachersSetAndEnrollments0.getSuspendedCount());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      int int0 = teachersSetAndEnrollments0.getSuspendedCount();
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), int0);
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      int int0 = teachersSetAndEnrollments0.getFailedDueToAbsenceCount();
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals((-1498), int0);
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-21), (-21), (-21), (-21));
      int int0 = teachersSetAndEnrollments0.getFailedDueToGradeCount();
      assertEquals((-21), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals((-21), int0);
      assertEquals((-21), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-21), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      int int0 = teachersSetAndEnrollments0.getSucceededCount();
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1498), int0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      teachersSetAndEnrollments0.getTeachers();
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-21), (-21), (-21), (-21));
      teachersSetAndEnrollments0.setSuspendedCount((-21));
      assertEquals((-21), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-21), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-21), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-21), teachersSetAndEnrollments0.getSuspendedCount());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, 598, 0, (-308), 598);
      // Undeclared exception!
      try { 
        teachersSetAndEnrollments0.addEnrollments((ClassEnrollments) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.models.TeachersSetAndEnrollments", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      teachersSetAndEnrollments0.setSucceededCount((-1498));
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      teachersSetAndEnrollments0.setFailedDueToAbsenceCount((-1498));
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      teachersSetAndEnrollments0.setFailedDueToGradeCount((-1498));
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      TeachersSetAndEnrollments teachersSetAndEnrollments0 = new TeachersSetAndEnrollments((Set<String>) null, (-1498), (-1498), (-1498), (-1498));
      teachersSetAndEnrollments0.setTeachers((Set<String>) null);
      assertEquals((-1498), teachersSetAndEnrollments0.getSucceededCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToAbsenceCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getFailedDueToGradeCount());
      assertEquals((-1498), teachersSetAndEnrollments0.getSuspendedCount());
  }
}
