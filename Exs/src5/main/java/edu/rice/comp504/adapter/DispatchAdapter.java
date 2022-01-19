package edu.rice.comp504.adapter;


import edu.rice.comp504.model.LineStore;
import edu.rice.comp504.model.MovingLine;
import edu.rice.comp504.model.cmd.ILineCmd;
import edu.rice.comp504.model.cmd.SwitchCmd;
import edu.rice.comp504.model.cmd.UpdateCmd;

/**
 * The dispatch adapter is responsible for ensuring that the moving lines are properly updated.
 */
public class DispatchAdapter {
    private final LineStore lineStore;

    /**
     * constructor.
     */
    public DispatchAdapter(LineStore ls) {
        this.lineStore = ls;
    }

    /**
     * Get the line store.
     * @return The line store
     */
    public LineStore getLineStore() {
        return this.lineStore;
    }

    /**
     * Return line store with a new line with a random initial strategy.
     * @return Line store with new line
     */
    public LineStore loadLine(String kind) {
        lineStore.makeLine(kind);
        return lineStore;
    }

    /**
     * Update the lines.
     * @return Line store
     */
    public LineStore updateLines() {
        lineStore.sendRecvCmd(new UpdateCmd());
        //TO DO: Implement me
        return lineStore;
    }

    /**
     * Switch the line strategies.
     * @return Line store
     */
    public LineStore switchStrategy() {
        lineStore.sendRecvCmd(new SwitchCmd());
        // TO DO: Implement me
        return lineStore;
    }

    /**
     * Reset lines to original position.
     * @return Line store
     */
    public LineStore resetLines() {
        lineStore.reset();
        return lineStore;
    }
}
