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
import net.sourceforge.phpeclipse.builder.IdentifierIndexManager;
import net.sourceforge.phpeclipse.phpeditor.DocumentAdapter;
import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 *
 */
public abstract class AbstractPHPEditorAction implements IEditorActionDelegate {
    
    private IdentifierIndexManager indexManager;
    
    private IEditorPart targetEditor;
    
    private void initialize(PHPEditor editor){
        IFile file = PHPEditorUtils.getFile(editor);
        PHPeclipsePlugin plugin = PHPeclipsePlugin.getDefault();
        indexManager = plugin.getIndexManager(file.getProject());
        //indexManager.initialize();
        indexManager.changeFile(file);
    }
    
    /**
     * 必要に応じてサブクラスでオーバライドしてください
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        this.targetEditor = targetEditor;
        targetEditor.addPropertyListener(new IPropertyListener(){
            public void propertyChanged(Object source, int propId) {
                PHPEditor editor = (PHPEditor) source;
                initialize(editor);
                ((DocumentAdapter) editor.getAdapter(DocumentAdapter.class)).documentChanged(null);
            }
        });
    }
    
    public IdentifierIndexManager getIndexManager(){
        return indexManager;
    }
    
    /**
     * 必要に応じてサブクラスでオーバライドしてください
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    }
    
    /**
     * 必要に応じてサブクラスでオーバライドしてください
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if(targetEditor instanceof PHPEditor){
            PHPEditor editor = (PHPEditor) targetEditor;
            if(editor.validateEditorInputState()){
                initialize(editor);
            }
            run(action, editor);
            // TODO: 保存せずに変更を通知する...fireEvent(? dirty:= true)
            editor.doSave(null);
        }
    }
    
    /**
     * サブクラスで実装してください
     * @param action
     * @param editor
     */
    protected abstract void run(IAction action, PHPEditor editor);
    
}
