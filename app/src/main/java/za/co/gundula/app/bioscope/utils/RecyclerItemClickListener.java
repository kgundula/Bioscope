package za.co.gundula.app.bioscope.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * A RecyclerView.OnItemTouchListener that provides a simple way to handle item clicks.
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;
    private GestureDetector mGestureDetector;

    /**
     * Interface for receiving item click events.
     */
    public interface OnItemClickListener {
        /**
         * Called when an item is clicked.
         *
         * @param view     The view that was clicked.
         * @param position The position of the item that was clicked.
         * @param x        The x coordinate of the click.
         * @param y        The y coordinate of the click.
         */
        void onItemClick(View view, int position, float x, float y);
    }

    /**
     * Constructs a new RecyclerItemClickListener.
     *
     * @param context  The context to use.
     * @param listener The listener to notify of item clicks.
     */
    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    /**
     * Intercepts touch events to detect item clicks.
     *
     * @param rv The RecyclerView.
     * @param e  The MotionEvent.
     * @return true if the event was intercepted, false otherwise.
     */
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getChildPosition(childView), e.getX(), e.getY());
            return true;
        }
        return false;
    }

    /**
     * Called when a touch event is dispatched to a view.
     *
     * @param rv The RecyclerView.
     * @param e  The MotionEvent.
     */
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    /**
     * Called when a child of RecyclerView does not want RecyclerView and its ancestors to
     * intercept touch events with onInterceptTouchEvent.
     *
     * @param disallowIntercept True if the child does not want the parent to intercept touch events.
     */
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
