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
package org.seasar.php.s2dao.phpeditor.internal.actions;

import net.sourceforge.phpeclipse.PHPeclipsePlugin;
import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.seasar.php.s2dao.core.IPHPRangeDocumentWriter;
import org.seasar.php.s2dao.core.IRangeCreator;
import org.seasar.php.s2dao.core.creator.php.ColumnAnnotationCreator;
import org.seasar.php.s2dao.core.writer.RangeWriter;
import org.seasar.php.s2dao.phpeditor.IRangeCreatorAction;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 */
public class ColumnAnnotationAction extends AbstractPHPEditorAction implements IRangeCreatorAction {
    
    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if(selection.isEmpty()){
            action.setEnabled(false);
        } else {
            action.setEnabled(true);
        }
    }

    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#run(org.eclipse.jface.action.IAction, net.sourceforge.phpeclipse.phpeditor.PHPEditor)
     */
    protected void run(IAction action, PHPEditor editor) {
        ITextSelection selection = (ITextSelection) editor.getSelectionProvider().getSelection();
        if(!selection.isEmpty()){
            try {
                IPHPRangeDocumentWriter writer = new RangeWriter(PHPEditorUtils.getDocument(editor));
                writer.append(createNewPHPCreator(editor, selection));
            } catch(BadLocationException e){
                PHPeclipsePlugin.log(e);
            }
        } else {
            Shell shell = editor.getEditorSite().getShell();
            // TODO: message
            MessageDialog.openInformation(shell, "確認してください", "プロパティを選択してください");
        }
    }
    
    /**
     * @see org.seasar.php.s2dao.phpeditor.IRangeCreatorAction#createNewPHPCreator(net.sourceforge.phpeclipse.phpeditor.PHPEditor, org.eclipse.jface.text.ITextSelection)
     */
    public IRangeCreator createNewPHPCreator(PHPEditor editor, ITextSelection selection) {
        return new ColumnAnnotationCreator(PHPEditorUtils.getCodeFormat(editor), selection);
    }

}
