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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPDocumentWriter;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.writer.EditorWriter;
import org.seasar.php.s2dao.phpeditor.ICreatorAction;
import org.seasar.php.s2dao.phpeditor.context.CodegenActionContext;
import org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;
import org.seasar.php.s2dao.phpeditor.util.PHPStructureUtils;

/**
 * 
 * @author nowel
 */
public class AllEntityAction extends AbstractPHPEditorAction {

    protected void run(IAction action, PHPStructuredEditor editor) {
        IDocument document = PHPEditorUtils.getDocument(editor);
        ITextSelection selection = (ITextSelection) PHPEditorUtils.getSelection(editor);
        IPHPDocumentWriter writer = new EditorWriter(document, selection);
        
        try {
            writer.append(apply(editor));
        } catch (BadLocationException e) {
            PHPCorePlugin.log(e);
        }
    }
    
    protected ICreator[] apply(PHPStructuredEditor editor){
        IPHPStructure structure = PHPStructureUtils.createStructure(editor.getPHPFileData());
        
        ICreatorAction[] actions = CodegenActionContext.getActions();
        List<ICreator> creators = new ArrayList<ICreator>();
        for(int i = 0; i < actions.length; i++){
            creators.add(actions[i].createNewPHPCreator(editor, structure));
        }
        return creators.toArray(new ICreator[creators.size()]);
    }

}
