/**
 * 
 */
package org.seasar.php.s2dao.core;

import java.util.List;

import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

/**
 * @author nowel
 */
public interface IPHPStructure {
    
    public PHPClass getPHPClass();
    
    public void setPHPClass(PHPClass phpClass);
    
    public void addProperty(Property property);
    
    public void addMethod(Method method);
    
    public void addConstant(Constant constant);
    
    public List<Constant> getConstants();
    
    public List<Method> getMethods();
    
    public List<Property> getProperties();
    
}
