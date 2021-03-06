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

import org.seasar.php.s2dao.core.IPHPType;

/**
 * @author nowel
 *
 */
public class ModifierUtils {
    
    public static IPHPType.Modifier getModifier(String token){
        if(IPHPType.STATIC.getName().equalsIgnoreCase(token)){
            return IPHPType.STATIC;
        } else if(IPHPType.ABSTRACT.getName().equalsIgnoreCase(token)){
            return IPHPType.ABSTRACT;
        } else if(IPHPType.FINAL.getName().equalsIgnoreCase(token)){
            return IPHPType.FINAL;
        } else {
            return IPHPType.NONE;
        }
    }

}
