/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:42:04 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject;

import org.junit.Test;
import static org.junit.Assert.*;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectRetentionCSV;
import br.edu.ufcg.computacao.eureca.backend.api.http.response.retention.subject.SubjectsRetentionResponse;
import java.util.Collection;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class SubjectsRetentionResponse_ESTest extends SubjectsRetentionResponse_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      SubjectsRetentionResponse subjectsRetentionResponse0 = new SubjectsRetentionResponse((Collection<SubjectRetentionCSV>) null);
      Collection<SubjectRetentionCSV> collection0 = subjectsRetentionResponse0.getSubjectRetention();
      assertNull(collection0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      SubjectsRetentionResponse subjectsRetentionResponse0 = new SubjectsRetentionResponse((Collection<SubjectRetentionCSV>) null);
      subjectsRetentionResponse0.setSubjectRetention((Collection<SubjectRetentionCSV>) null);
  }
}
