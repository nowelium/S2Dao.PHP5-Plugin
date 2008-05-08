/**
 * 
 */
package org.seasar.php.s2dao.core;

import org.eclipse.jface.text.BadLocationException;

/**
 * @author nowel
 *
 */
public interface IPHPRangeDocumentWriter {

    public void append(IRangeCreator creator) throws BadLocationException;
    
    public void append(IRangeCreator[] creators) throws BadLocationException;
    
}
