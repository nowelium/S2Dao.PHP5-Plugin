package org.seasar.php.s2dao.core.creator.php;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.IPHPType;
import org.seasar.php.s2dao.core.creator.php.SetterGetterCreator;
import org.seasar.php.s2dao.core.impl.CodeFormatConfigImpl;
import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

public class SetterGetterCreatorTest {

    @Test
    public void testCreateDefault() {
        Property pt1 = new Property("hoge");
        Property pt2 = new Property("foo");
        Property pt3 = new Property("bar");
        Property pt4 = new Property("avail");
        
        structure.addProperty(pt1);
        structure.addProperty(pt2);
        structure.addProperty(pt3);
        structure.addProperty(pt4);
        
        ICreator c = new SetterGetterCreator(config, structure);
        System.err.println(c.create());
    }
    
    @Test
    public void testCreateAccessor() {
        Property pt1 = new Property("hoge");
        Property pt2 = new Property("foo");
        Property pt3 = new Property("bar");
        
        structure.addProperty(pt1);
        structure.addProperty(pt2);
        structure.addProperty(pt3);
        
        ICreator c = new SetterGetterCreator(config, structure, IPHPType.PROTECTED);
        System.err.println(c.create());
    }
    
    private ICodeFormatConfig config = new CodeFormatConfigImpl("\n", "1234");
    
    private IPHPStructure structure = new IPHPStructure() {
        
        private List<Property> list = new ArrayList<Property>();

        public void addConstant(Constant constant) {
        }

        public void addMethod(Method method) {
        }

        public void addProperty(Property property) {
            list.add(property);
        }

        public List<Constant> getConstants() {
            return null;
        }

        @SuppressWarnings("serial")
        public List<Method> getMethods() {
            return new ArrayList<Method>(){
                {
                    add(new Method(){
                        {
                            setName("getAvail");
                        }
                    });
                }
            };
        }

        public PHPClass getPHPClass() {
            return null;
        }

        public List<Property> getProperties() {
            return list;
        }

        public void setPHPClass(PHPClass phpClass) {
        }
    };

}
