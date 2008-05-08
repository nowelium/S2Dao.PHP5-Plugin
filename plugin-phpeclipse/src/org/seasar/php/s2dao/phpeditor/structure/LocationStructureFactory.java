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
package org.seasar.php.s2dao.phpeditor.structure;

import net.sourceforge.phpeclipse.builder.IdentifierIndexManager;
import net.sourceforge.phpeclipse.builder.PHPIdentifierLocation;

import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

/**
 * @author nowel
 */
public class LocationStructureFactory extends DefaultStructureFactory {

    /**
     * @param manager
     */
    public LocationStructureFactory(IdentifierIndexManager manager) {
        super(manager);
    }
    
    protected void addIdentify(PHPIdentifierLocation phpId, IPHPStructure structure){
        String idName = phpId.getIdentifier();
        if(phpId.isClass()){
            structure.setPHPClass(new PHPClass(idName));
        } else if(phpId.isMethod()){
            // メソッドであればメソッド名の取得
            structure.addMethod(new Method(idName));
        } else if(phpId.isVariable()) {
            // プロパティであればプロパティ名の取得
            structure.addProperty(new Property(idName));
        } else if(phpId.isDefine()){
            // class constants ???
            // TODO: PHPIdentifierじゃわからないので、他のやつで取得する
            structure.addConstant(new Constant(idName));
        }
    }

}
