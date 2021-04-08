package org.academiadecodigo.rememberthename.model;

public abstract class AbstractModel implements Model{

    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
