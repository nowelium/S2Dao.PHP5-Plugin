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
package org.seasar.php.s2dao.phpeditor.util;

import net.sourceforge.phpdt.core.ICompilationUnit;
import net.sourceforge.phpdt.internal.corext.util.CodeFormatterUtil;
import net.sourceforge.phpdt.ui.IWorkingCopyManager;
import net.sourceforge.phpdt.ui.PreferenceConstants;
import net.sourceforge.phpeclipse.PHPeclipsePlugin;
import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.impl.CodeFormatConfigImpl;

/**
 * @author nowel
 */
public final class PHPEditorUtils {
    
    public static IFile getFile(PHPEditor editor){
        PHPeclipsePlugin plugin = PHPeclipsePlugin.getDefault();
        IWorkingCopyManager workingCopyManager = plugin.getWorkingCopyManager();
        ICompilationUnit unit = workingCopyManager.getWorkingCopy(editor.getEditorInput());
        return (IFile) unit.getResource();
    }
    
    public static IDocument getDocument(PHPEditor editor){
        IDocumentProvider docProvider = editor.getDocumentProvider();
        return docProvider.getDocument(editor.getEditorInput());
    }
    
    public static ISelection getSelection(PHPEditor editor){
        ISelectionProvider selectionProvider = editor.getSelectionProvider();
        return selectionProvider.getSelection();
    }
    
    public static ICodeFormatConfig getCodeFormat(PHPEditor editor){
        IPreferenceStore preference = PHPeclipsePlugin.getDefault().getPreferenceStore();
        String whiteSpace = null;
        // プリファレンスからタブ文字をスペースとして使用するかどうかを取得
        if (preference.getBoolean(PreferenceConstants.EDITOR_SPACES_FOR_TABS)) {
            //スペースを使用する場合
            // タブサイズの取得
            int tabSize = CodeFormatterUtil.getTabWidth();
            StringBuffer buf = new StringBuffer();
            for(int i = 0; i < tabSize; i++){
                buf.append(' ');
            }
            // 指定サイズ分のスペースを返す
            whiteSpace = buf.toString();
        } else {
            //タブ文字を返す
            whiteSpace = "\t";
        }
        return new CodeFormatConfigImpl(getDocument(editor), whiteSpace);
    }

}
