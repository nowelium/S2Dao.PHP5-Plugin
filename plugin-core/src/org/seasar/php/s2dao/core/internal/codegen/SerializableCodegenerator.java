/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.seasar.php.s2dao.core.internal.codegen;

import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.internal.ICodegenerator;
import org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher;

/**
 * @author nowel
 */
public class SerializableCodegenerator implements ICodegenerator {
    
    private String lineSeparator;
    private String indent1;
    private String indent2;
    private String indent3;
    
    /**
     * 
     * @param config
     */
    public SerializableCodegenerator(ICodeFormatConfig config) {
        lineSeparator = config.getLineSeparator();
        indent1 = config.getWhiteSpace();
        indent2 = config.indent(2);
        indent3 = config.indent(3);
    }
    
    /**
     * 
     * @see org.seasar.php.s2dao.core.internal.ICodegenerator#generate(org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher)
     */
    public String generate(PHPTypeMatcher matcher) {
        StringBuffer buf = new StringBuffer();
        if(!matcher.matches("serialize")){
            buf.append(createSerialize());
        }
        if(matcher.matches("unserialize")){
            buf.append(createSerialize());
        }
        return buf.toString();
    }

    /**
     * 
     * @return
     */
    protected String createSerialize() {
        StringBuffer buf = new StringBuffer();
        buf.append(lineSeparator);
        buf.append(indent1).append("public function serialize() {");
        buf.append(lineSeparator);
        buf.append(indent2).append("$serialize = array();");
        buf.append(lineSeparator);
        buf.append(indent2).append("foreach($this as $property => $value) {");
        buf.append(lineSeparator);
        buf.append(indent3).append("$serialize[$property] = $value;");
        buf.append(lineSeparator);
        buf.append(indent2).append("}");
        buf.append(lineSeparator);
        buf.append(indent2).append("return serialize($serialize);");
        buf.append(lineSeparator);
        buf.append(indent1).append("}");
        buf.append(lineSeparator);
        return buf.toString();
    }

    /**
     * 
     * @return
     */
    protected String createUnserialize() {
        StringBuffer buf = new StringBuffer();
        buf.append(lineSeparator);
        buf.append(indent1).append("public function unserialize($serialize) {");
        buf.append(lineSeparator);
        buf.append(indent2).append("$variables = unserialize($serialize);");
        buf.append(lineSeparator);
        buf.append(indent2).append("foreach($variables as $property => $value) {");
        buf.append(lineSeparator);
        buf.append(indent3).append("$this->$property = $value;");
        buf.append(lineSeparator);
        buf.append(indent2).append("}");
        buf.append(lineSeparator);
        buf.append(indent1).append("}");
        buf.append(lineSeparator);
        return buf.toString();
    }
}
