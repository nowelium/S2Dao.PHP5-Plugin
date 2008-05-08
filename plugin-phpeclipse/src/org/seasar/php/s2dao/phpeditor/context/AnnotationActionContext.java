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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.php.s2dao.phpeditor.internal.actions.ColumnAnnotationAction;
import org.seasar.php.s2dao.phpeditor.internal.actions.TableAnnotationAction;

/**
 * @author nowel
 */
public class AnnotationActionContext {
    
    public static final String TableAnnotation =
        "org.seasar.php.s2dao.phpeditor.context.actions.TableAnnotaionAction";
    public static final String ColumnAnnotation =
        "org.seasar.php.s2dao.phpeditor.context.actions.ColumnAnnotaionAction";
    
    private static Map<String, Class<?>> contexts = new HashMap<String, Class<?>>();
    
    static {
        contexts.put(TableAnnotation, TableAnnotationAction.class);
        contexts.put(ColumnAnnotation, ColumnAnnotationAction.class);
    };
    
    public static Object getAction(String actionId){
        Class clazz = contexts.get(actionId);
        if(clazz == null){
            return null;
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Object[] getActions(){
        List<Object> actions = new ArrayList<Object>();
        Iterator iter = contexts.keySet().iterator();
        while(iter.hasNext()){
            String actionId = (String) iter.next();
            actions.add(getAction(actionId));
        }
        return actions.toArray(new Object[contexts.size()]);
    }

}
