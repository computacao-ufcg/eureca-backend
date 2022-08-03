/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:29:55 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.constants;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import br.edu.ufcg.computacao.eureca.backend.constants.Field;
import br.edu.ufcg.computacao.eureca.backend.constants.SubjectsGlossaryFields;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SubjectsGlossaryFields_ESTest extends SubjectsGlossaryFields_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field1).toString();
      subjectsGlossaryFields0.mandatory = field1;
      Field field2 = subjectsGlossaryFields0.getMandatory();
      assertNotSame(field2, field0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      subjectsGlossaryFields0.setOptional((Field) null);
      Field field1 = subjectsGlossaryFields0.getOptional();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields((Field) null, (Field) null, (Field) null, (Field) null);
      Field field0 = subjectsGlossaryFields0.getMandatory();
      assertNull(field0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      subjectsGlossaryFields0.setElective((Field) null);
      Field field1 = subjectsGlossaryFields0.getElective();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      subjectsGlossaryFields0.setComplementary((Field) null);
      Field field1 = subjectsGlossaryFields0.getComplementary();
      assertNull(field1);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      Field field1 = subjectsGlossaryFields0.getComplementary();
      assertNull(field1.getDescription());
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      Field field1 = subjectsGlossaryFields0.getElective();
      assertSame(field1, field0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      doReturn((String) null).when(field0).toString();
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      Field field1 = subjectsGlossaryFields0.getOptional();
      assertNull(field1.getDescription());
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      Field field0 = mock(Field.class, new ViolatedAssumptionAnswer());
      SubjectsGlossaryFields subjectsGlossaryFields0 = new SubjectsGlossaryFields(field0, field0, field0, field0);
      Field field1 = mock(Field.class, new ViolatedAssumptionAnswer());
      subjectsGlossaryFields0.setMandatory(field1);
  }
}
