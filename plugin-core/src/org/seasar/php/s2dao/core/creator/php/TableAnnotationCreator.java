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
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.structure.type.PHPClass;

/**
 * @author nowel
 *
 */
public class TableAnnotationCreator implements IRangeCreator {
    
    private ICodeFormatConfig config;
    private ITextSelection selection;
    private IPHPStructure structure;
    
    /**
     * 
     * @param config
     * @param selection
     * @param structure
     */
    public TableAnnotationCreator(ICodeFormatConfig config,
                                  ITextSelection selection,
                                  IPHPStructure structure){
        this.config = config;
        this.selection = selection;
        this.structure = structure;
    }
    
    /**
     * 
     * @see org.seasar.php.s2dao.core.IRangeCreator#getPostion()
     */
    public Position getPostion() {
        // TODO: クラス宣言の直下に出す
        //PHPClass phpClass = structure.getPHPClass();
        //Position classPosition = phpClass.position();
        if(selection.isEmpty()){
            return new Position(selection.getOffset(), selection.getLength());
        }
        return new Position(selection.getOffset(), 0);
    }

    /**
     * 
     * @see org.seasar.php.s2dao.core.ICreator#create()
     */
    public String create() {
        String lineSep = config.getLineSeparator();
        String whiteSpace = config.getWhiteSpace();
        PHPClass phpClass = structure.getPHPClass();
        return lineSep + whiteSpace + "const TABLE = '" + phpClass.getName() + "';" + lineSep;
    }

}
