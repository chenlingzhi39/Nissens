package com.nissens.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC-20160514 on 2016/10/18.
 */

public class CarCondition {
    private List<String> types;
    private String hint;

    public CarCondition(String hint, List<String> types) {
        this.hint = hint;
        this.types = types;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
