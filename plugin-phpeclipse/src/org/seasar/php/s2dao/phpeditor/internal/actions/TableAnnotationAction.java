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
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.ITextSelection;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPRangeDocumentWriter;
import org.seasar.php.s2dao.core.IPHPStructure;
import org.seasar.php.s2dao.core.IPHPStructureFactory;
import org.seasar.php.s2dao.core.IRangeCreator;
import org.seasar.php.s2dao.core.creator.php.TableAnnotationCreator;
import org.seasar.php.s2dao.core.writer.RangeWriter;
import org.seasar.php.s2dao.phpeditor.ICreatorAction;
import org.seasar.php.s2dao.phpeditor.structure.LocationStructureFactory;
import org.seasar.php.s2dao.phpeditor.util.PHPEditorUtils;

/**
 * @author nowel
 */
public class TableAnnotationAction extends AbstractPHPEditorAction implements ICreatorAction {

    /**
     * @see org.seasar.php.s2dao.phpeditor.internal.actions.AbstractPHPEditorAction#run(org.eclipse.jface.action.IAction, net.sourceforge.phpeclipse.phpeditor.PHPEditor)
     */
    protected void run(IAction action, PHPEditor editor) {
        IPHPStructureFactory factory = new LocationStructureFactory(getIndexManager());
        IPHPStructure structure = factory.getStructure(PHPEditorUtils.getFile(editor));
        IPHPRangeDocumentWriter writer = new RangeWriter(PHPEditorUtils.getDocument(editor));
        
        try {
            writer.append((IRangeCreator) createNewPHPCreator(editor, structure));
        } catch (BadLocationException e) {
            PHPeclipsePlugin.log(e);
        }
    }

    /**
     * @see org.seasar.php.s2dao.phpeditor.ICreatorAction#createNewPHPCreator(net.sourceforge.phpeclipse.phpeditor.PHPEditor, org.seasar.php.s2dao.core.IPHPStructure)
     */
    public ICreator createNewPHPCreator(PHPEditor editor, IPHPStructure structure) {
        ITextSelection selection = (ITextSelection) editor.getSelectionProvider().getSelection();
        return new TableAnnotationCreator(PHPEditorUtils.getCodeFormat(editor), selection, structure);
    }

}
