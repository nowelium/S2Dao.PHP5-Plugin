package org.seasar.php.s2dao.core.structure.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.seasar.php.s2dao.core.IPHPType;

public class AccessorUtilsTest {

    @Test
    public void testGetAccessor() {
        assertEquals(AccessorUtils.getAccessor("public"), IPHPType.PUBLIC);
        assertEquals(AccessorUtils.getAccessor("PubliC"), IPHPType.PUBLIC);
        assertEquals(AccessorUtils.getAccessor("pRiVatE"), IPHPType.PRIVATE);
        assertEquals(AccessorUtils.getAccessor("PRIVATE"), IPHPType.PRIVATE);
        assertEquals(AccessorUtils.getAccessor("proTected"), IPHPType.PROTECTED);
        assertEquals(AccessorUtils.getAccessor("protecteD"), IPHPType.PROTECTED);
        assertEquals(AccessorUtils.getAccessor(""), IPHPType.PACKAGE);
    }
    
    @Test
    public void testGetAccessorUnmatch() {
        assertEquals(AccessorUtils.getAccessor(null), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor(""), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor("pliblic"), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor("priv"), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor("protect"), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor("absolute"), IPHPType.PACKAGE);
        assertEquals(AccessorUtils.getAccessor("hgoehgoe"), IPHPType.PACKAGE);
    }

}
