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

import java.util.List;

import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.internal.ICodegenerator;
import org.seasar.php.s2dao.core.structure.type.Property;
import org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher;

/**
 * @author nowel
 */
public class ToStringCodegenerator implements ICodegenerator {
    
    private List<Property> properties;
    
    private String lineSeparator;
    private String indent1;
    private String indent2;
    
    public ToStringCodegenerator(ICodeFormatConfig config, List<Property> properties){
        this.lineSeparator = config.getLineSeparator();
        this.indent1 = config.getWhiteSpace();
        this.indent2 = config.indent(2);
        this.properties = properties;
    }

    /**
     * @see org.seasar.php.s2dao.core.internal.ICodegenerator#generate(org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher)
     */
    public String generate(PHPTypeMatcher matcher) {
        if(!matcher.matches("__toString")){
            return create();
        }
        return null;
    }
    
    protected String create(){
        int size = properties.size();
        StringBuffer buf = new StringBuffer(size);
        buf.append(lineSeparator);
        buf.append(indent1).append("public function __toString() {");
        buf.append(lineSeparator);
        buf.append(indent2).append("$buf = '';");
        buf.append(lineSeparator);
        for(int i = 0; i < size; ++i){
            Property property = properties.get(i);
            String propertyName = property.getName();
            buf.append(indent2);
            buf.append("$buf .= (string)$this->").append(propertyName).append(" . PHP_EOL;");
            buf.append(lineSeparator);
        }
        buf.append(indent2).append("return (string)$buf;");
        buf.append(lineSeparator);
        buf.append(indent1).append("}");
        buf.append(lineSeparator);
        return buf.toString();
    }

}
