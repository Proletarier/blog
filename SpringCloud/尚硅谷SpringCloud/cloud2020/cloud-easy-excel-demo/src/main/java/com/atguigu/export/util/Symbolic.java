package com.atguigu.export.util;

public enum Symbolic {

    BOX(0x00A8),
    BOX_YES(0x00FE),
    BOX_NO(0x00FD),
    TICK(0x221A),
    ERROR(0x00D7);

    private final int value;

    private  Symbolic(int  value){
        this.value=value;
    }

    public int  getValue(){
        return  value;
    }

}
