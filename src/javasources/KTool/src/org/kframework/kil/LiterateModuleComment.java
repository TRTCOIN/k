package org.kframework.kil;

import org.kframework.kil.visitors.Visitor;

public class LiterateModuleComment extends ModuleItem implements LiterateComment {

    private String value;
    private LiterateCommentType lcType;

    public LiterateModuleComment(LiterateModuleComment literateModuleComment) {
        super(literateModuleComment);
        value = literateModuleComment.value;
        lcType = literateModuleComment.lcType;
    }

    public LiterateModuleComment(String value, LiterateCommentType lcType) {
        this.value = value;
        this.lcType = lcType;
    }

    public LiterateModuleComment(LiterateDefinitionComment ldc) {
        setFilename(ldc.getFilename());
        setLocation(ldc.getLocation());
        value = ldc.getValue();
        lcType = ldc.getType();
    }
    
    @Override
    public <P, R> R accept(Visitor<P, R> visitor, P p) {
        return visitor.visit(this, p);
    }
    
    @Override
    public LiterateCommentType getType() {
        return lcType;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public LiterateModuleComment shallowCopy() {
        return new LiterateModuleComment(this);
    }

    @Override
    public String toString() {
        return "/*"+lcType+value+"*/";
    }
}
