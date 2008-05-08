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

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.php.internal.core.PHPCoreConstants;
import org.eclipse.php.internal.core.PHPCorePlugin;
import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.seasar.php.s2dao.core.ICodeFormatConfig;
import org.seasar.php.s2dao.core.impl.CodeFormatConfigImpl;

public class PHPEditorUtils {
    
    public static ISelection getSelection(PHPStructuredEditor editor){
        ISelectionProvider selectionProvider = editor.getSelectionProvider();
        return selectionProvider.getSelection();
    }
    
    public static IDocument getDocument(PHPStructuredEditor editor){
        IDocumentProvider docProvider = editor.getDocumentProvider();
        return docProvider.getDocument(editor.getEditorInput());
    }
    
    public static ICodeFormatConfig getCodeFormat(PHPStructuredEditor editor){
        Preferences preference = PHPCorePlugin.getDefault().getPluginPreferences();
        String whiteSpace = null;
        // プリファレンスからタブ文字をスペースとして使用するかどうかを取得
        if (preference.getBoolean(PHPCoreConstants.FORMATTER_USE_TABS)) {
            // タブ文字を返す
            whiteSpace = "\t";
        } else {
            //スペースを使用する場合
            // タブサイズの取得
            int tabSize = preference.getInt(PHPCoreConstants.FORMATTER_INDENTATION_SIZE);
            StringBuffer buf = new StringBuffer();
            for(int i = 0; i < tabSize; i++){
                buf.append(' ');
            }
            // 指定サイズ分のスペースを返す
            whiteSpace = buf.toString();
        }
        return new CodeFormatConfigImpl(getDocument(editor), whiteSpace);
    }

}
