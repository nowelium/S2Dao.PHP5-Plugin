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

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.seasar.php.s2dao.core.IRangeCreator;
import org.seasar.php.s2dao.core.creator.php.ColumnAnnotationCreator;
import org.seasar.php.s2dao.phpeditor.IRangeCreatorAction;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 */
public class ColumnAnnotationAction implements IRangeCreatorAction {
    public IRangeCreator createNewPHPCreator(PHPStructuredEditor editor, ITextSelection selection) {
        return new ColumnAnnotationCreator(PHPEditorUtils.getCodeFormat(editor), selection);
    }
}
