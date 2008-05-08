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
package org.seasar.php.s2dao.core.impl;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.seasar.php.s2dao.core.ICodeFormatConfig;

/**
 * @author nowel
 */
public class CodeFormatConfigImpl implements ICodeFormatConfig {
    
    private String lineSeparator;
    
    private String whiteSpace;
    
    /**
     * 
     * @param lineSeparator
     * @param whiteSpace
     */
    public CodeFormatConfigImpl(String lineSeparator, String whiteSpace){
        this.lineSeparator = lineSeparator;
        this.whiteSpace = whiteSpace;
    }
    
    /**
     * 
     * @param document
     * @param whiteSpace
     */
    public CodeFormatConfigImpl(IDocument document, String whiteSpace){
        try {
            // ドキュメントの最初の行から改行文字を返す
            lineSeparator = document.getLineDelimiter(0);
        } catch (BadLocationException e) {
            // 例外時はシステムの改行文字を返す
            lineSeparator = System.getProperty("line.separator", "\n");
        }
        this.whiteSpace = whiteSpace;
    }

    /**
     * @see org.seasar.php.s2dao.core.ICodeFormatConfig#getLineSeparator()
     */
    public String getLineSeparator() {
        return lineSeparator;
    }
    
    /**
     * @see org.seasar.php.s2dao.core.ICodeFormatConfig#getWhiteSpace()
     */
    public String getWhiteSpace() {
        return whiteSpace;
    }
    
    /**
     * @see org.seasar.php.s2dao.core.ICodeFormatConfig#indent(int)
     */
    public String indent(int count) {
        if(count == 1){
            return whiteSpace;
        }
        
        StringBuffer buf = new StringBuffer(10);
        for(int i = 0; i < count; ++i){
            buf.append(whiteSpace);
        }
        return buf.toString();
    }

}
