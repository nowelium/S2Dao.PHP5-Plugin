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

import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.creator.php.ToStringCreator;
import org.seasar.php.s2dao.phpeditor.ICreatorAction;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 */
public class ToStringAction implements ICreatorAction {
    /**
     * @see org.seasar.php.s2dao.phpeditor.ICreatorAction#createNewPHPCreator(net.sourceforge.phpeclipse.phpeditor.PHPEditor, org.seasar.php.s2dao.core.IPHPStructure)
     */
    public ICreator createNewPHPCreator(PHPEditor editor, IPHPStructure structure) {
        return new ToStringCreator(PHPEditorUtils.getCodeFormat(editor), structure);
    }
}
