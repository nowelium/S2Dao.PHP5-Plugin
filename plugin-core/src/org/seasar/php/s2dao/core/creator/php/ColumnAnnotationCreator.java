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

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.IRangeCreator;
import org.seasar.php.s2dao.core.structure.type.Property;
import org.seasar.php.s2dao.core.structure.util.LineParserUtils;

/**
 * @author nowel
 *
 */
public class ColumnAnnotationCreator implements IRangeCreator {
    
    private ICodeFormatConfig config;
    private ITextSelection selection;
    private Property property;
    
    /**
     * 
     * @param config
     * @param selection
     */
    public ColumnAnnotationCreator(ICodeFormatConfig config, ITextSelection selection){
        this.config = config;
        this.selection = selection;
        this.property = (Property)LineParserUtils.getProperty(selection.getText());
    }
    
    /**
     * 
     * @see org.seasar.php.s2dao.core.IRangeCreator#getPostion()
     */
    public Position getPostion() {
        return new Position(selection.getOffset(), 0);
    }

    /**
     * 
     * @see org.seasar.php.s2dao.core.ICreator#create()
     */
    public String create() {
        String propertyName = property.getName();
        return "const " + propertyName + "_COLUMN = '" + propertyName.toUpperCase() + "';" +
               config.getLineSeparator() + config.getWhiteSpace();
    }

}
