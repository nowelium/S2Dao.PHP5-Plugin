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
public class PHPClass implements IPHPType {
    
    private Modifier modifier = NONE;
    
    private String name;
    
    private Position position;
    
    public PHPClass(){
    }
    
    public PHPClass(String name){
        this(NONE, name, null);
    }
    
    public PHPClass(Modifier modifier, String name, Position position){
        this.modifier = modifier;
        this.name = name;
        this.position = position;
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

}
