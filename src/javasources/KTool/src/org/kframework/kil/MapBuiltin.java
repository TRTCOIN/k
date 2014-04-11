package org.kframework.kil;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.kframework.kil.loader.Context;
import org.kframework.kil.visitors.Visitor;

/**
 * A builtin map.
 *
 * @author AndreiS
 */
public class MapBuiltin extends DataStructureBuiltin {

    private final Map<Term, Term> elements;

    public MapBuiltin(DataStructureSort sort, Collection<Term> baseTerms, Map<Term, Term> elements) {
        super(sort, baseTerms);
        this.elements = elements;
    }

    public Map<Term, Term> elements() {
        return Collections.unmodifiableMap(elements);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty() && super.baseTerms.isEmpty();
    }

    @Override
    public Term shallowCopy() {
        return new MapBuiltin(dataStructureSort, baseTerms, elements);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * Context.HASH_PRIME + super.hashCode();
        hash = hash * Context.HASH_PRIME + elements.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof MapBuiltin)) {
            return false;
        }

        MapBuiltin mapBuiltin = (MapBuiltin) object;
        return super.equals(mapBuiltin) && elements.equals(mapBuiltin.elements);
    }
    
    @Override
    public <P, R> R accept(Visitor<P, R> visitor, P p) {
        return visitor.visit(this, p);
    }
}
