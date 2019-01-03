package com.techiespace.projects.fallingnotes.pianoHelpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class RoundRectShapeRenderer extends ShapeRenderer {
    /**
     * Draws a rectangle with rounded corners of the given radius.
     */

    public void roundedRect(ShapeRenderer renderer, float x, float y, float width, float height, float radius, Color xy, Color xwy, Color xwyh, Color xyh) {

        // Central rectangle
        super.rect(x + radius, y + radius, width - 2 * radius, height - 2 * radius, xy, xwy, xwyh, xyh);

        // Four side rectangles, in clockwise order
        //bottom
        super.rect(x + radius, y, width - 2 * radius, radius, xy, xwy, xwy, xy);
        //right
        super.rect(x + width - radius, y + radius, radius, height - 2 * radius, xwy, xwy, xwyh, xwyh);
        //top
        super.rect(x + radius, y + height - radius, width - 2 * radius, radius, xyh, xwyh, xwyh, xyh);
        //left
        super.rect(x, y + radius, radius, height - 2 * radius, xy, xy, xyh, xyh);

        // Four arches, clockwise too
        //bottom-left
        renderer.setColor(xy);
        super.circle(x + radius, y + radius, radius);
        //bottom-right
        renderer.setColor(xwy);
        super.circle(x + width - radius, y + radius, radius);
        //top-right
        renderer.setColor(xwyh);
        super.circle(x + width - radius, y + height - radius, radius);
        //top-left
        renderer.setColor(xyh);
        super.circle(x + radius, y + height - radius, radius);
    }
}