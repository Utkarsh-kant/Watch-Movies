
package com.utkarsh.watchmovies.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Genre {

private List<GeneresItems>generes;

    public List<GeneresItems> getGeneres() {
        return generes;
    }

    public void setGeneres(List<GeneresItems> generes) {
        this.generes = generes;
    }
}
