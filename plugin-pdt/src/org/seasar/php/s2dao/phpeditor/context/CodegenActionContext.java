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
package org.seasar.php.s2dao.phpeditor.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.seasar.php.s2dao.phpeditor.ICreatorAction;
import org.seasar.php.s2dao.phpeditor.internal.actions.SerializableAction;
import org.seasar.php.s2dao.phpeditor.internal.actions.SetterGetterAction;
import org.seasar.php.s2dao.phpeditor.internal.actions.ToStringAction;

/**
 * @author nowel
 */
public class CodegenActionContext {
    
    public static final String ToString =
        "org.seasar.php.s2dao.phpeditor.context.actions.ToStringAction";
    
    public static final String Serializable =
        "org.seasar.php.s2dao.phpeditor.context.actions.SerializableAction";
    
    public static final String SetterGetter =
        "org.seasar.php.s2dao.phpeditor.context.actions.SetterGetterAction";

    private static Map<String, Class<? extends ICreatorAction>> contexts =
                      new TreeMap<String, Class<? extends ICreatorAction>>();
    
    static {
        contexts.put(ToString, ToStringAction.class);
        contexts.put(SetterGetter, SetterGetterAction.class);
        contexts.put(Serializable, SerializableAction.class);
    }
    
    public static ICreatorAction getAction(String id){
        return getAction(contexts.get(id));
    }
    
    public static ICreatorAction getAction(Class<? extends ICreatorAction> actionClass){
        try {
            return actionClass.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
    
    public static ICreatorAction[] getActions(){
        List<ICreatorAction> actions = new ArrayList<ICreatorAction>();
        Iterator<Entry<String, Class<? extends ICreatorAction>>> iter = contexts.entrySet().iterator();
        while(iter.hasNext()){
            Entry<String, Class<? extends ICreatorAction>> entry = iter.next();
            actions.add(getAction(entry.getValue()));
        }
        return actions.toArray(new ICreatorAction[contexts.size()]);
    }
    
}
