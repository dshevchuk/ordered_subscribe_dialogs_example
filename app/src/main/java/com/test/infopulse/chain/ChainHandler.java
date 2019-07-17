package com.test.infopulse.chain;

public abstract class ChainHandler {

    private ChainHandler nextHandler;

    public void setNextHandler(ChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract public boolean show();

    protected boolean showNext() {
       return nextHandler != null ? nextHandler.show() : false;
    }
}
