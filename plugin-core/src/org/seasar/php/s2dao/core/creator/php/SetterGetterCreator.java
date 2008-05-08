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
package org.seasar.php.s2dao.core.creator.php;

import java.util.List;

import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.IPHPType;
import org.seasar.php.s2dao.core.internal.ICodegenerator;
import org.seasar.php.s2dao.core.internal.codegen.SetterGetterCodegenerator;
import org.seasar.php.s2dao.core.structure.type.Property;
import org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher;

/**
 * @author nowel
 */
public class SetterGetterCreator implements ICreator {
    
    private ICodeFormatConfig config;
    
    private IPHPStructure structure;
    
    private IPHPType.Accessor accessor;
    
    /**
     * 
     * @param config
     * @param structure
     */
    public SetterGetterCreator(ICodeFormatConfig config, IPHPStructure structure){
        this(config, structure, IPHPType.PUBLIC);
    }
    
    /**
     * 
     * @param config
     * @param structure
     * @param accessor
     */
    public SetterGetterCreator(ICodeFormatConfig config, IPHPStructure structure, IPHPType.Accessor accessor){
        this.config = config;
        this.structure = structure;
        this.accessor = accessor;
    }

    /**
     * 
     * @see org.seasar.php.s2dao.core.ICreator#create()
     */
    public String create() {
        PHPTypeMatcher matcher = new PHPTypeMatcher(structure.getMethods());
        List<Property> properties = structure.getProperties();
        
        StringBuffer buf = new StringBuffer(100);
        for(int i = 0; i < properties.size(); i++){
            ICodegenerator gen = createCodegenerator(properties.get(i));
            buf.append(gen.generate(matcher));
        }
        return buf.toString();
    }
    
    /**
     * 
     * @param property
     * @return
     */
    protected ICodegenerator createCodegenerator(Property property){
        return new SetterGetterCodegenerator(config, accessor, property);
    }
    
}
