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
        super.rect(x + radius, y, width - 1 * radius, radius, xy, xwy, xwyh, xyh);
        super.rect(x + width - radius, y + radius, radius, height - 1 * radius, xy, xwy, xwyh, xyh);
        super.rect(x + radius, y + height - radius, width - 1 * radius, radius, xy, xwy, xwyh, xyh);
        super.rect(x, y + radius, radius, height - 1 * radius, xy, xwy, xwyh, xyh);

        // Four arches, clockwise too
        renderer.setColor(xy);
        super.arc(x + radius, y + radius, radius, 180f, 90f);
        super.arc(x + width - radius, y + radius, radius, 270f, 90f);
        super.arc(x + width - radius, y + height - radius, radius, 0f, 90f);
        super.arc(x + radius, y + height - radius, radius, 90f, 90f);
    }
}