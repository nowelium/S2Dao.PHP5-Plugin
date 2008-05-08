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
package org.seasar.php.s2dao.phpeditor.actions;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.phpeclipse.PHPeclipsePlugin;
import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPDocumentWriter;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.IPHPStructureFactory;
import org.seasar.php.s2dao.core.writer.EditorWriter;
import org.seasar.php.s2dao.phpeditor.ICreatorAction;
import org.seasar.php.s2dao.phpeditor.context.CodegenActionContext;
import org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction;
import org.seasar.php.s2dao.phpeditor.structure.DefaultStructureFactory;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 */
public class AllEntityAction extends AbstractPHPEditorAction {
    
    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#run(org.eclipse.jface.action.IAction, net.sourceforge.phpeclipse.phpeditor.PHPEditor)
     */
    protected void run(IAction action, PHPEditor editor) {
        IDocument document = PHPEditorUtils.getDocument(editor);
        ITextSelection selection = (ITextSelection) PHPEditorUtils.getSelection(editor);
        IPHPDocumentWriter writer = new EditorWriter(document, selection);
        
        try {
            writer.append(apply(editor));
        } catch (BadLocationException e) {
            PHPeclipsePlugin.log(e);
        }
    }
    
    protected ICreator[] apply(PHPEditor editor){
        IPHPStructureFactory factory = new DefaultStructureFactory(getIndexManager());
        IPHPStructure structure = factory.getStructure(PHPEditorUtils.getFile(editor));
        
        ICreatorAction[] actions = CodegenActionContext.getActions();
        List<ICreator> creators = new ArrayList<ICreator>();
        for(int i = 0; i < actions.length; i++){
            creators.add(actions[i].createNewPHPCreator(editor, structure));
        }
        return creators.toArray(new ICreator[creators.size()]);
    }

}
