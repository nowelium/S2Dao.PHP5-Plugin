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

import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.seasar.php.s2dao.core.IPHPType;
import org.seasar.php.s2dao.core.structure.type.Property;

/**
 * @author nowel
 *
 */
public class LineParserUtils {
    
    private static final Pattern accessPattern =
        Pattern.compile("(public|protected|private)", Pattern.CASE_INSENSITIVE);
    
    /**
     * TODO: もう少しマシなロジックを。。。
     * @param text
     * @return
     */
    public static IPHPType getProperty(String text){
        String access = null;
        String instance = null;
        StringTokenizer tokenizer = new StringTokenizer(text, " \t\n\r\f;");
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(access == null){
                if(accessPattern.matcher(token).lookingAt()){
                    access = token;
                    continue;
                }
            }
            if(instance == null){
                if(token.equalsIgnoreCase("static")){
                    instance = token;
                    continue;
                }
            }
            if(token.startsWith("$")){
                return getProperty(access, instance, token.substring(1));
            }
        }
        return null;
    }
    
    private static IPHPType getProperty(String access, String modify, String propertyName){
        IPHPType.Accessor accessor = AccessorUtils.getAccessor(access);
        IPHPType.Modifier modifier = ModifierUtils.getModifier(modify);
        return new Property(accessor, modifier, propertyName, null);
    }
    
}
