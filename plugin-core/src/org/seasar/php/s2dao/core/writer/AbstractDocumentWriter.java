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
package org.seasar.php.s2dao.core.writer;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;

/**
 * @author nowel
 *
 */
abstract class AbstractDocumentWriter {
    
    protected IDocument document;
    
    public AbstractDocumentWriter(IDocument document){
        this.document = document;
    }
    
    /**
     * 最後の行に追加します。
     * @param text
     * @throws BadLocationException
     */
    protected void append(ITextSelection selection, String text) throws BadLocationException {
        if(selection.isEmpty()){
            int offset = selection.getOffset();
            int length = selection.getLength();
            append(offset, length, text);
        } else {
            append(selection.getOffset(), 0, text);
        }
    }
    
    /**
     * 指定行に追加します。
     * @param offset
     * @param length
     * @param text
     * @throws BadLocationException
     */
    protected void append(int offset, int length, String text) throws BadLocationException {
        if(null != text && 0 < text.length()){
            // 現在のドキュメントの選択行に作成したテキストを追加
            document.replace(offset, length, text);
        }
    }

}
