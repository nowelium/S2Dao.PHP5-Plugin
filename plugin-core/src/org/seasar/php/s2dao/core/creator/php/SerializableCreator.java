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

import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.internal.ICodegenerator;
import org.seasar.php.s2dao.core.internal.codegen.SerializableCodegenerator;
import org.seasar.php.s2dao.core.structure.util.PHPTypeMatcher;

/**
 * @author nowel
 *
 */
public class SerializableCreator implements ICreator {

    private ICodeFormatConfig config;
    private IPHPStructure structure;
    
    /**
     * 
     * @param config
     * @param structure
     */
    public SerializableCreator(ICodeFormatConfig config, IPHPStructure structure) {
        this.config = config;
        this.structure = structure;
    }

    /**
     * 
     * @see org.seasar.php.s2dao.core.ICreator#create()
     */
    public String create() {
        ICodegenerator gen = new SerializableCodegenerator(config);
        return gen.generate(new PHPTypeMatcher(structure.getMethods()));
    }
    
}
