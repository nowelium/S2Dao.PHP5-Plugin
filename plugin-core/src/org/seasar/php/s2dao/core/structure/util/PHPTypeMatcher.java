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
package org.seasar.php.s2dao.core.structure.util;

import java.util.List;

import org.seasar.php.s2dao.core.IPHPType;

/**
 * @author nowel
 */
public class PHPTypeMatcher {
    
    private IPHPType[] list;
    
    public PHPTypeMatcher(List<?> types){
        list = (IPHPType []) types.toArray(new IPHPType[types.size()]);
    }
    
    public boolean matches(Object obj){
        for(int i = 0; i < list.length; ++i){
            IPHPType type = (IPHPType) list[i];
            if(type.equals(obj)){
                return true;
            }
            if(type.getName().equals(obj)){
                return true;
            }
        }
        return false;
    }

}
