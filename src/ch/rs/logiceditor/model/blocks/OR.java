/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.blocks;

import ch.rs.logiceditor.model.master.ConnectionPoint;
import ch.rs.logiceditor.model.master.LogicBlock;
import java.util.LinkedList;

/**
 *
 * @author Ricardo
 */
public class OR extends LogicBlock {

    public OR(LinkedList<ConnectionPoint> inputs, LinkedList<ConnectionPoint> outputs) {
        super("OR", inputs, outputs);
    }

    @Override
    protected void Logic() {
        boolean isTrue = false;
        for (ConnectionPoint in : getInputs()) {
            if ((Boolean) in.getValue()) {
                isTrue = true;
                break;
            }
        }
        setOutput(isTrue);
    }
    
}