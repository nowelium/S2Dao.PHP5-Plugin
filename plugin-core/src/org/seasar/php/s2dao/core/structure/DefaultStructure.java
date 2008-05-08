/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.php.s2dao.core.structure;

import java.util.ArrayList;
import java.util.List;

import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

/**
 * @author nowel
 * 
 */
public class DefaultStructure implements IPHPStructure {
    
    /** */
    private List<Property> properties = new ArrayList<Property>();
    
    /** */
    private List<Method> methods = new ArrayList<Method>();
    
    /** */
    private List<Constant> constants = new ArrayList<Constant>();
    
    /** */
    private PHPClass phpClass;

    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#addConstant(org.seasar.php.s2dao.core.structure.type.Constant)
     */
    public void addConstant(Constant constant) {
        constants.add(constant);
    }
    
    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#getConstants()
     */
    public List<Constant> getConstants(){
        return constants;
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#addMethod(org.seasar.php.s2dao.core.structure.type.Method)
     */
    public void addMethod(Method method) {
        methods.add(method);
    }
    
    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#getMethods()
     */
    public List<Method> getMethods(){
        return methods;
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#addProperty(org.seasar.php.s2dao.core.structure.type.Property)
     */
    public void addProperty(Property property) {
        properties.add(property);
    }
    
    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#getProperties()
     */
    public List<Property> getProperties(){
        return properties;
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#getPHPClass()
     */
    public PHPClass getPHPClass() {
        return phpClass;
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPStructure#setPHPClass(org.seasar.php.s2dao.core.structure.type.PHPClass)
     */
    public void setPHPClass(PHPClass phpClass) {
        this.phpClass = phpClass;
    }
    
}
