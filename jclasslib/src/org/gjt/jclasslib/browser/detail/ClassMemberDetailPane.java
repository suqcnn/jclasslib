/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.browser.detail;

import org.gjt.jclasslib.browser.*;
import org.gjt.jclasslib.structures.*;
import org.gjt.jclasslib.util.*;

import javax.swing.tree.*;

/**
    Detail pane showing class members (methods or fields):
 
    @author <a href="mailto:jclasslib@gmx.net">Ingo Kegel</a>
    @version $Revision: 1.1.1.1 $ $Date: 2001-05-14 16:49:23 $
*/
public class ClassMemberDetailPane extends FixedListDetailPane {

    /** Constant which indicates that a <tt>ClassMemberDetailPane</tt> shows fields */
    public static final int FIELDS = 1;
    /** Constant which indicates that a <tt>ClassMemberDetailPane</tt> shows methods */
    public static final int METHODS = 2;
    
    private int mode;
    
    // Visual components
    
    private ExtendedJLabel lblName;
    private ExtendedJLabel lblNameVerbose;

    private ExtendedJLabel lblDescriptor;
    private ExtendedJLabel lblDescriptorVerbose;

    private ExtendedJLabel lblAccessFlags;
    private ExtendedJLabel lblAccessFlagsVerbose;

    /**
        Construct a <tt>ClassMemberDetailPane</tt> with a specified mode which is
        either <tt>FIELDS</tt> or <tt>METHODS</tt>.
        @param parentFrame the parent frame
        @param mode the mode
     */
    public ClassMemberDetailPane(BrowserInternalFrame parentFrame, int mode) {
        super(parentFrame);
        this.mode = mode;
    }
    
    protected void setupLabels() {
        
        addDetailPaneEntry(normalLabel("Name:"),
                           lblName = linkLabel(),
                           lblNameVerbose = highlightLabel());

        addDetailPaneEntry(normalLabel("Descriptor:"),
                           lblDescriptor = linkLabel(),
                           lblDescriptorVerbose = highlightLabel());

        addDetailPaneEntry(normalLabel("Access flags:"),
                           lblAccessFlags = highlightLabel(), 
                           lblAccessFlagsVerbose = highlightLabel());
    }

    public void show(TreePath treePath) {
        
        ClassMember classMember = null;
        if (mode == FIELDS) {
            classMember = parentFrame.getClassFile().getFields()[getIndex(treePath)];
        } else {
            classMember = parentFrame.getClassFile().getMethods()[getIndex(treePath)];
        }
        
        constantPoolHyperlink(lblName,
                              lblNameVerbose,
                              classMember.getNameIndex());
        
        constantPoolHyperlink(lblDescriptor,
                              lblDescriptorVerbose,
                              classMember.getDescriptorIndex());
        
        lblAccessFlags.setText(classMember.getFormattedAccessFlags());
        lblAccessFlagsVerbose.setText("[" + classMember.getAccessFlagsVerbose() + "]");

        super.show(treePath);
        
    }
    
}
