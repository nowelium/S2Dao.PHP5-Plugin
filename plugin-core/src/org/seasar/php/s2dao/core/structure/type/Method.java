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
package org.seasar.php.s2dao.core.structure.type;

import org.eclipse.jface.text.Position;
import org.seasar.php.s2dao.core.IPHPType;

/**
 * @author nowel
 *
 */
public class Method implements IPHPType {
    
    private Accessor accessor = PUBLIC;
    
    private Modifier modifier = NONE;
    
    private String name;
    
    private Position position;
    
    public Method(){
    }
    
    public Method(String name){
        this(PRIVATE, NONE, name, null);
    }
    
    public Method(Accessor accessor, Modifier modifier, String name, Position position){
        this.accessor = accessor;
        this.modifier = modifier;
        this.name = name;
        this.position = position;
    }
    
    public Accessor accessor(){
        return accessor;
    }
    
    public Modifier modifier(){
        return modifier;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public Position getPosition(){
        return position;
    }
    
    public void setPostion(Position position){
        this.position = position;
    }
    
    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if(obj instanceof Method){
            Method o = (Method)obj;
            return o.name.equals(name);
        }
        if(obj instanceof String){
            String s = (String)obj;
            return s.equals(name);
        }
        return super.equals(obj);
    }
    
}
