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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
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

public class MethodCreatorsAction extends AbstractPHPEditorAction {
    
    protected ICreatorAction getCreatorAction(String actionId){
        return CodegenActionContext.getAction(actionId);
    }

    @Override
    public void run(IAction action, PHPStructuredEditor editor) throws BadLocationException {
        ICreatorAction creatorAction = getCreatorAction(action.getId());
        if(creatorAction == null){
            return;
        }

        IPHPStructure structure = PHPStructureUtils.createStructure(editor.getPHPFileData());
        ICreator creator = creatorAction.createNewPHPCreator(editor, structure);
        IDocument document = PHPEditorUtils.getDocument(editor);    
        ITextSelection selection = (ITextSelection) PHPEditorUtils.getSelection(editor);
        IPHPDocumentWriter writer = new EditorWriter(document, selection);
        writer.append(creator);
    }

}
