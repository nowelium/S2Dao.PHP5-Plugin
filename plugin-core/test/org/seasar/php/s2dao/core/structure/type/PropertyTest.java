package org.seasar.php.s2dao.core.structure.type;

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertyTest {

    @Test
    public void testProperty() {
        Property pt1 = new Property();
        pt1.setName("hoge");
        Property pt2 = new Property();
        pt2.setName("hoge");
        assertEquals(pt1, pt2);
    }

    @Test
    public void testPropertyAccessorModifierStringPosition() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testAccessor() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testModifier() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testGetName() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testSetName() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testGetPosition() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testSetPostion() {
        fail("まだ実装されていません。");
    }

    @Test
    public void testEqualsObject() {
        fail("まだ実装されていません。");
    }

}
