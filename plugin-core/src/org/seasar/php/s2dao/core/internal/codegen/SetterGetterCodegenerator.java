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
package org.seasar.php.s2dao.core.internal.codegen;

import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.IPHPType;
import org.seasar.php.s2dao.core.internal.ICodegenerator;
import org.seasar.php.s2dao.core.structure.type.Property;
import org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher;
import org.seasar.php.s2dao.core.util.StringUtils;

/**
 * @author nowel
 */
public class SetterGetterCodegenerator implements ICodegenerator {
    
    /** プロパティ名 */
    private String propertyName;
    /** setterメソッド名 */
    private String setterMethodName;
    /** getterメソッド名 */
    private String getterMethodName;
    
    private String lineSeparator;

    private String indent1;
    
    private String indent2;
    
    private String accessorName;

    /**
     * 
     * @param config
     * @param accessor
     * @param property
     */
    public SetterGetterCodegenerator(ICodeFormatConfig config,
                                     IPHPType.Accessor accessor,
                                     Property property){
        String propertyName = property.getName();
        String capitalizedName = StringUtils.capitalize(propertyName);
        this.propertyName = propertyName;
        this.setterMethodName = "set" + capitalizedName;
        this.getterMethodName = "get" + capitalizedName;
        this.accessorName = accessor.getName();
        this.lineSeparator = config.getLineSeparator();
        this.indent1 = config.getWhiteSpace();
        this.indent2 = config.indent(2);
    }

    /**
     * 
     * @see org.seasar.php.s2dao.core.internal.ICodegenerator#generate(org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher)
     */
    public String generate(PHPTypeMatcher matcher) {
        StringBuffer buf = new StringBuffer();
        if(!matcher.matches(setterMethodName)){
            buf.append(createSetter());
        }
        if(!matcher.matches(getterMethodName)){
            buf.append(createGetter());
        }
        return buf.toString();
    }

    /**
     * getterメソッドの生成
     * @return getterメソッドの内容
     */
    protected String createSetter(){
        StringBuffer buf = new StringBuffer();
        buf.append(lineSeparator);
        buf.append(indent1);
        buf.append(accessorName).append(" function ").append(setterMethodName);
        buf.append("($").append(propertyName).append(") {");
        buf.append(lineSeparator);
        buf.append(indent2).append("$this->");
        buf.append(propertyName).append(" = $").append(propertyName).append(";");
        buf.append(lineSeparator);
        buf.append(indent1).append("}");
        buf.append(lineSeparator);
        return buf.toString();
    }

    /**
     * setterメソッドの生成
     * @return setterメソッドの内容
     */
    protected String createGetter(){
        StringBuffer buf = new StringBuffer();
        buf.append(lineSeparator);
        buf.append(indent1);
        buf.append(accessorName).append(" function ").append(getterMethodName);
        buf.append("() {");
        buf.append(lineSeparator);
        buf.append(indent2).append("return ").append("$this->");
        buf.append(propertyName).append(";");
        buf.append(lineSeparator);
        buf.append(indent1).append("}");
        buf.append(lineSeparator);
        return buf.toString();
    }

}
