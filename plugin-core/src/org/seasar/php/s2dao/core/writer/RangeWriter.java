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
import org.eclipse.jface.text.Position;
import org.seasar.php.s2dao.core.IPHPRangeDocumentWriter;
import org.seasar.php.s2dao.core.IRangeCreator;

/**
 * @author nowel
 *
 */
public class RangeWriter extends AbstractDocumentWriter implements IPHPRangeDocumentWriter {
    
    public RangeWriter(IDocument document){
        super(document);
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPRangeDocumentWriter#append(org.seasar.php.s2dao.core.IRangeCreator)
     */
    public void append(IRangeCreator creator) throws BadLocationException {
        append(new IRangeCreator[]{creator});
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPRangeDocumentWriter#append(org.seasar.php.s2dao.core.IRangeCreator[])
     */
    public void append(IRangeCreator[] creators) throws BadLocationException {
        for(int i = 0; i < creators.length; ++i){
            IRangeCreator creator = creators[i];
            Position pos = creator.getPostion();
            append(pos.getOffset(), pos.getLength(), creator.create());
        }
    }

}
