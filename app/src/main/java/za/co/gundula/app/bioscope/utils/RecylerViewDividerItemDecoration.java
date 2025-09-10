package za.co.gundula.app.bioscope.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import za.co.gundula.app.bioscope.R;

/**
 * An ItemDecoration that draws a divider between items in a RecyclerView.
 */
public class RecylerViewDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    /**
     * Constructs a new RecylerViewDividerItemDecoration.
     *
     * @param context The context to use.
     */
    public RecylerViewDividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    /**
     * Gets the offsets of the divider.
     *
     * @param outRect The Rect to store the offsets in.
     * @param view    The View to get the offsets for.
     * @param parent  The RecyclerView.
     * @param state   The RecyclerView's state.
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = mDivider.getIntrinsicHeight();
    }

    /**
     * Draws the divider.
     *
     * @param canvas The Canvas to draw on.
     * @param parent The RecyclerView.
     * @param state  The RecyclerView's state.
     */
    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }

    }

}
