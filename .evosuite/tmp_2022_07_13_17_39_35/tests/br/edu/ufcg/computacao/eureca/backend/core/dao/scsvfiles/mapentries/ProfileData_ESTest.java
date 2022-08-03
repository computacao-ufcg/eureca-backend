/*
 * This file was automatically generated by EvoSuite
 * Wed Jul 13 21:38:00 GMT 2022
 */

package br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries;

import org.junit.Test;
import static org.junit.Assert.*;
import br.edu.ufcg.computacao.eureca.backend.core.dao.scsvfiles.mapentries.ProfileData;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ProfileData_ESTest extends ProfileData_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      ProfileData profileData0 = new ProfileData("?f&%XZ$N(Y)", "?f&%XZ$N(Y)");
      profileData0.setCourseName("");
      String string0 = profileData0.getCourseName();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      ProfileData profileData0 = new ProfileData("?f&%XZ$N(Y)", "?f&%XZ$N(Y)");
      profileData0.setCourseCode("");
      String string0 = profileData0.getCourseCode();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      ProfileData profileData0 = new ProfileData("?f&%XZ$N(Y)", "?f&%XZ$N(Y)");
      String string0 = profileData0.getCourseCode();
      assertEquals("?f&%XZ$N(Y)", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      ProfileData profileData0 = new ProfileData("4efKVYhY6", "l0[02k(7b:e'RUlx");
      String string0 = profileData0.getCourseName();
      assertEquals("4efKVYhY6", profileData0.getCourseCode());
      assertEquals("l0[02k(7b:e'RUlx", string0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      ProfileData profileData0 = new ProfileData("", (String) null);
      profileData0.setCourseCode((String) null);
      String string0 = profileData0.getCourseCode();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      ProfileData profileData0 = new ProfileData();
      String string0 = profileData0.getCourseName();
      assertNull(string0);
  }
}
