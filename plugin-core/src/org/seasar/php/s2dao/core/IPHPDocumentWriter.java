/**
 * 
 */
package org.seasar.php.s2dao.core;

import org.eclipse.jface.text.BadLocationException;

/**
 * @author nowel
 *
 */
public interface IPHPDocumentWriter {
    
    public void append(ICreator creator) throws BadLocationException;
    
    public void append(ICreator[] creators) throws BadLocationException;

}
