package com.tommyhasselman.termsandconditions.model;

import com.tommyhasselman.termsandconditions.Controller;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StoryTreeNodeTest  {

    private Controller c;
    private StoryTreeNode s;

    @Before
    public void setUp() throws Exception {

        c = new Controller();
        s= c.getStoryNode();


    }

    @Test
    public void newStoryNode() {
        assertNotNull(s);
        assertNotNull(s.getCinematic());
    }

}
