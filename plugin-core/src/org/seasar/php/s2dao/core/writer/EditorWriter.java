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
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPDocumentWriter;

/**
 * @author nowel
 *
 */
public class EditorWriter extends AbstractDocumentWriter implements IPHPDocumentWriter {
    
    private ITextSelection selection;
    
    public EditorWriter(IDocument document, ITextSelection selection){
        super(document);
        this.selection = selection;
    }
    
    public void setTextSelection(ITextSelection selection){
        this.selection = selection;
    }
    
    public void append(ICreator creator) throws BadLocationException {
        append(new ICreator[]{creator});
    }
    
    public void append(ICreator[] creators) throws BadLocationException {
        StringBuffer buf = new StringBuffer(1000); 
        for(int i = 0; i < creators.length; ++i){
            ICreator creator = creators[i];
            String string = creator.create();
            if(string != null){
                buf.append(string);
            }
        }
        append(selection, buf.toString());
    }
    
}
