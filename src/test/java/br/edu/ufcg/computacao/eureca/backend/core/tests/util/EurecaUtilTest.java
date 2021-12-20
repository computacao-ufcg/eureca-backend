package br.edu.ufcg.computacao.eureca.backend.core.tests.util;

import br.edu.ufcg.computacao.eureca.backend.core.models.Subject;
import br.edu.ufcg.computacao.eureca.backend.core.models.SubjectType;
import br.edu.ufcg.computacao.eureca.backend.core.util.EurecaUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

public class EurecaUtilTest {

    private Collection<Integer> collection1;
    private Collection<Integer> collection2;

    @Before
    public void setUp() {
        collection1 = Arrays.asList(1, 2, 3, 4, 5);
        collection2 = Arrays.asList(3, 4, 5, 6, 7);
    }

    @Test
    public void testIntersection() {
        Collection<Integer> result = EurecaUtil.intersection(collection1, collection2);
        Collection<Integer> expected = Arrays.asList(3, 4, 5);

        Assert.assertEquals(result, expected);
    }

    @Test
    public void testGetSubjectType() {
        Subject mandatory = new Subject(null, null, null, null, "M", 0, 0, null, null, 0, null, null);
        Subject optional = new Subject(null, null, null, null, "O", 0, 0, null, null, 0, null, null);
        Subject complementary = new Subject(null, null, null, null, "C", 0, 0, null, null, 0, null, null);
        Subject elective = new Subject(null, null, null, null, "E", 0, 0, null, null, 0, null, null);

        Assert.assertEquals(EurecaUtil.getSubjectType(mandatory), SubjectType.MANDATORY);
        Assert.assertEquals(EurecaUtil.getSubjectType(optional), SubjectType.OPTIONAL);
        Assert.assertEquals(EurecaUtil.getSubjectType(complementary), SubjectType.COMPLEMENTARY);
        Assert.assertEquals(EurecaUtil.getSubjectType(elective), SubjectType.ELECTIVE);
    }

    @Test
    public void testDifference() {
        Collection<Integer> result = EurecaUtil.difference(collection1, collection2);
        Collection<Integer> expected = Arrays.asList(1, 2);

        Assert.assertEquals(result, expected);
    }

}
