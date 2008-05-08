/**
 * 
 */
package org.seasar.php.s2dao.phpeditor;

import org.eclipse.php.internal.ui.editor.PHPStructuredEditor;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IPHPStructure;

/**
 * @author nowel
 *
 */
public interface ICreatorAction {

    /**
     * @param editor
     * @param structure
     * @return
     */
    public ICreator createNewPHPCreator(PHPStructuredEditor editor, IPHPStructure structure);
    
}
