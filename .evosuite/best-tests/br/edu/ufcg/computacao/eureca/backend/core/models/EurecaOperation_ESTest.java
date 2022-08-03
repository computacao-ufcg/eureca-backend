/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:30:18 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.core.models;

import org.junit.Test;
import static org.junit.Assert.*;
import br.edu.ufcg.computacao.eureca.backend.core.models.EurecaOperation;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class EurecaOperation_ESTest extends EurecaOperation_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      EurecaOperation[] eurecaOperationArray0 = EurecaOperation.values();
      assertEquals(28, eurecaOperationArray0.length);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      EurecaOperation eurecaOperation0 = EurecaOperation.valueOf("GET_TEACHERS_STATISTICS");
      assertEquals("getTeachersStatistics", eurecaOperation0.getValue());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      EurecaOperation eurecaOperation0 = EurecaOperation.GET_SUBJECT_ENROLLMENTS_STATISTICS_SUMMARY;
      String string0 = eurecaOperation0.getValue();
      assertEquals("getSubjectEnrollmentsStatisticsSummary", string0);
  }
}
