/**
 * 
 */
package org.seasar.php.s2dao.phpeditor;

import net.sourceforge.phpeclipse.phpeditor.PHPEditor;

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
    public ICreator createNewPHPCreator(PHPEditor editor, IPHPStructure structure);
    
}
