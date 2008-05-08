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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sourceforge.phpeclipse.builder.IdentifierIndexManager;
import net.sourceforge.phpeclipse.builder.PHPIdentifierLocation;
import net.sourceforge.phpeclipse.obfuscator.PHPIdentifier;

import org.eclipse.core.resources.IFile;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.IPHPStructureFactory;
import org.seasar.php.s2dao.core.structure.DefaultStructure;
import org.seasar.php.s2dao.core.structure.type.Constant;
import org.seasar.php.s2dao.core.structure.type.Method;
import org.seasar.php.s2dao.core.structure.type.PHPClass;
import org.seasar.php.s2dao.core.structure.type.Property;

/**
 * @author nowel
 *
 */
public class DefaultStructureFactory implements IPHPStructureFactory {
    
    protected IdentifierIndexManager manager;
    
    public DefaultStructureFactory(IdentifierIndexManager manager){
        this.manager = manager;
    }
    
    public IPHPStructure getStructure(IFile file){
        return getStructure(manager.getIdentifiers(file), new DefaultStructure());
    }
    
    protected IPHPStructure getStructure(Map identifiers, IPHPStructure structure){
        Iterator iterator = identifiers.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Object value = entry.getValue();
            if(value instanceof List){
                List valueList = (List) value;
                for(int i = 0; i < valueList.size(); i++){
                    addIdentify((PHPIdentifierLocation) valueList.get(i), structure);
                }
            }
        }
        return structure;
    }
    
    protected void addIdentify(PHPIdentifier phpId, IPHPStructure structure){
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
