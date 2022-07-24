/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:31:50 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.constants;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import br.edu.ufcg.computacao.eureca.backend.constants.EnrollmentsGlossaryFields;
import br.edu.ufcg.computacao.eureca.backend.constants.Field;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class EnrollmentsGlossaryFields_ESTest extends EnrollmentsGlossaryFields_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      enrollmentsGlossaryFields0.setSubjects((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getSubjects();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      enrollmentsGlossaryFields0.setMin((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getMin();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, (Field) null);
      enrollmentsGlossaryFields0.setMax((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getMax();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      enrollmentsGlossaryFields0.setAverageEnrollmentsPerPeriod((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getAverageEnrollmentsPerPeriod();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      enrollmentsGlossaryFields0.setAverageEnrollmentsPerClass((Field) null);
      Field field2 = enrollmentsGlossaryFields0.getAverageEnrollmentsPerClass();
      assertNull(field2);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      enrollmentsGlossaryFields0.setAverageClassesPerPeriod((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getAverageClassesPerPeriod();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      enrollmentsGlossaryFields0.setAverageClassesPerDiscipline((Field) null);
      Field field1 = enrollmentsGlossaryFields0.getAverageClassesPerDiscipline();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      Field field1 = enrollmentsGlossaryFields0.getMin();
      assertNull(field1.getDescription());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      Field field1 = enrollmentsGlossaryFields0.getAverageClassesPerDiscipline();
      assertSame(field1, field0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      Field field2 = enrollmentsGlossaryFields0.getMax();
      assertNotNull(field2);
      assertSame(field2, field0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field1).toString();
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      Field field2 = enrollmentsGlossaryFields0.getAverageEnrollmentsPerPeriod();
      assertNotSame(field2, field0);
      assertNotNull(field2);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      enrollmentsGlossaryFields0.setMax(enrollmentsGlossaryFields0.subjects);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      Field field2 = enrollmentsGlossaryFields0.getAverageEnrollmentsPerClass();
      assertNotNull(field2);
      assertNotSame(field2, field1);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field1);
      Field field2 = enrollmentsGlossaryFields0.getAverageClassesPerPeriod();
      assertNotNull(field2);
      assertSame(field2, field0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      EnrollmentsGlossaryFields enrollmentsGlossaryFields0 = new EnrollmentsGlossaryFields(field0, field0, field0, field0, field0, field0, field0);
      Field field1 = enrollmentsGlossaryFields0.getSubjects();
      assertNull(field1.getDescription());
  }
}
