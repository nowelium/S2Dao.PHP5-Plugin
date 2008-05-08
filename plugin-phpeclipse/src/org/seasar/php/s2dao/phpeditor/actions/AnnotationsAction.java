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

import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.seasar.php.s2dao.phpeditor.context.AnnotationActionContext;
import org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction;

/**
 * @author nowel
 *
 */
public class AnnotationsAction extends AbstractPHPEditorAction {
    
    /**
     * 
     * @param actionId
     * @return
     */
    public AbstractPHPEditorAction getAction(String actionId){
        return (AbstractPHPEditorAction) AnnotationActionContext.getAction(actionId);
    }
    
    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        AbstractPHPEditorAction aAction = getAction(action.getId());
        if(null != aAction){
            aAction.setActiveEditor(action, targetEditor);
        }
        super.setActiveEditor(action, targetEditor);
    }
    
    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        AbstractPHPEditorAction aAction = getAction(action.getId());
        if(null != aAction){
            aAction.selectionChanged(action, selection);
        }
        super.selectionChanged(action, selection);
    }

    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#run(org.eclipse.jface.action.IAction, net.sourceforge.phpeclipse.phpeditor.PHPEditor)
     */
    protected void run(IAction action, PHPEditor editor) {
        AbstractPHPEditorAction aAction = getAction(action.getId());
        if(aAction == null){
            Shell shell = editor.getEditorSite().getShell();
            // TODO: messages.properties? NLSも
            String title = "選択したアクションが完了しませんでした。";
            String message = "選択したアクションが完了しませんでした。(actionId: " + action.getId() + ")";
            MessageDialog.openInformation(shell, title, message);
            return;
        }
        aAction.run(action);
    }

}
