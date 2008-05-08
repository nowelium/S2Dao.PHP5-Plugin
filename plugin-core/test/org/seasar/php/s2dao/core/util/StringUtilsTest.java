package org.seasar.php.s2dao.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testCapitalize() {
        assertEquals(StringUtils.capitalize("hoge"), "Hoge");
        assertEquals(StringUtils.capitalize("Hoge"), "Hoge");
    }

}
