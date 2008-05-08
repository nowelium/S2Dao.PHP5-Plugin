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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public abstract class AbstractPHPEditorAction implements IObjectActionDelegate {

    private IWorkbenchPart workbenchPart;
    
    /**
     * 必要に応じてサブクラスでオーバライドしてください
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }

    /**
     * 必要に応じてサブクラスでオーバライドしてください
     */
    public void setActivePart(IAction action, IWorkbenchPart workbenchPart) {
        this.workbenchPart = workbenchPart;
    }
    
    /**
     * 必要に応じてサブクラスでオーバライドしてください
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if(workbenchPart instanceof PHPStructuredEditor){
            try {
                run(action, (PHPStructuredEditor) workbenchPart);
            } catch(Exception e){
                PHPCorePlugin.log(e);
            }
        }
    }
    
    /**
     * サブクラスで実装してください
     * @param action
     * @param editor
     */
    protected abstract void run(IAction action, PHPStructuredEditor editor) throws Exception;
}
