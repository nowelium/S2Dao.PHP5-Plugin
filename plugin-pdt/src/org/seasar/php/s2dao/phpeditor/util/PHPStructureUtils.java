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
package org.seasar.php.s2dao.phpeditor.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.php.internal.core.phpModel.phpElementData.PHPClassConstData;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPClassData;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPFileData;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPFunctionData;
import org.eclipse.php.internal.core.phpModel.phpElementData.PHPVariableData;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.structure.DefaultStructure;
import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

public class PHPStructureUtils {
    
    private PHPStructureUtils(){
        // no operation
    }
    
    public static IPHPStructure createStructure(PHPFileData fileData){
        PHPClassData[] classDatas = fileData.getClasses();
        if(classDatas == null){
            return null;
        }
        
        PHPClassData classData = classDatas[0];
        Structure structure = new Structure();
        structure.setConstants(getConstants(classData));
        structure.setMethods(getMethods(classData));
        structure.setProperties(getProperties(classData));
        structure.setPHPClass(getPHPClass(classData));
        return structure;
    }
    
    public static List<Constant> getConstants(PHPClassData classData){
        PHPClassConstData[] datas = classData.getConsts();
        if(datas == null){
            return null;
        }
        
        List<Constant> constants = new ArrayList<Constant>();
        for(int i = 0; i < datas.length; ++i){
            Constant constant = new Constant();
            constant.setName(datas[i].getName());
            constants.add(constant);
        }
        return constants;
    }
    
    public static List<Method> getMethods(PHPClassData classData){
        PHPFunctionData[] functions = classData.getFunctions();
        if(functions == null){
            return null;
        }
        
        List<Method> methods = new ArrayList<Method>();
        for(int i = 0; i < functions.length; ++i){
            Method method = new Method();
            method.setName(functions[i].getName());
            methods.add(method);
        }
        return methods;
    }
    
    public static List<Property> getProperties(PHPClassData classData){
        PHPVariableData[] vars = classData.getVars();
        if(vars == null){
            return null;
        }
        
        List<Property> properties = new ArrayList<Property>();
        for(int i = 0; i < vars.length; ++i){
            Property property = new Property();
            property.setName(vars[i].getName());
            properties.add(property);
        }
        return properties;
    }
    
    public static PHPClass getPHPClass(PHPClassData classData){
        PHPClass phpClass = new PHPClass();
        phpClass.setName(classData.getName());
        return phpClass;
    }
    
    private static class Structure extends DefaultStructure {
        
        private List<Constant> constants;
        
        private List<Method> methods;
        
        private List<Property> properties;
        
        public void setConstants(List<Constant> constants){
            this.constants = constants;
        }
        
        public void setMethods(List<Method> methods){
            this.methods = methods;
        }
        
        public void setProperties(List<Property> properties){
            this.properties = properties;
        }
        
        @Override
        public List<Constant> getConstants() {
            return constants;
        }

        @Override
        public List<Method> getMethods() {
            return methods;
        }

        @Override
        public List<Property> getProperties() {
            return properties;
        }
        
    }

}
