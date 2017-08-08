package com.github.cafune1853.scaffold.generator;

/**
 * @author doggy
 * Created on 2017-08-09.
 */
public class ParentGenerator extends AbstractGenerator{
    public ParentGenerator(String groupId, String artifactId) {
        super(groupId, artifactId, null, false);
    }

    @Override
    public void setFiles() {
        //do nothing here.
    }
}
