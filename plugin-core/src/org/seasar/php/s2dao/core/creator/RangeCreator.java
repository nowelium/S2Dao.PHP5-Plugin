/**
 * 
 */
package org.seasar.php.s2dao.core.creator;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.seasar.php.s2dao.core.ICreator;
import org.seasar.php.s2dao.core.IRangeCreator;

/**
 * @author nowel
 *
 */
public class RangeCreator implements IRangeCreator {
    
    private ITextSelection selection;
    private ICreator creator;
    
    public RangeCreator(ITextSelection selection, ICreator creator){
        this.selection = selection;
        this.creator = creator;
    }
    
    public Position getPostion(){
        return new Position(selection.getOffset(), 0);
    }

    /**
     * @see org.seasar.php.s2dao.core.IPHPCreator#create()
     */
    public String create() {
        return creator.create();
    }

}
