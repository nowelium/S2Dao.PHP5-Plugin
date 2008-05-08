/**
 * 
 */
package org.seasar.php.s2dao.core;

import org.eclipse.core.resources.IFile;

/**
 * @author nowel
 *
 */
public interface IPHPStructureFactory {
        
    public IPHPStructure getStructure(IFile file);
        
}
