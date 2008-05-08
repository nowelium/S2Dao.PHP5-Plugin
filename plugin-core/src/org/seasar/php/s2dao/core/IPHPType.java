/**
 * 
 */
package org.seasar.php.s2dao.core;

import org.eclipse.jface.text.Position;

/**
 * @author nowel
 * 
 */
public interface IPHPType {

    public static final Accessor PUBLIC = new Accessor("public");

    public static final Accessor PROTECTED = new Accessor("protected");

    public static final Accessor PRIVATE = new Accessor("private");
    
    public static final Accessor PACKAGE = new Accessor("");

    public static final Modifier STATIC = new Modifier("static");

    public static final Modifier ABSTRACT = new Modifier("abstract");

    public static final Modifier FINAL = new Modifier("final");
    
    public static final Modifier NONE = new Modifier("");

    public String getName();
    
    public Position getPosition();

    class Accessor {

        private String name;

        public Accessor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class Modifier {

        private String name;

        public Modifier(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
