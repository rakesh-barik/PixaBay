package io.rakesh.pixabay.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemMarginDecoration extends RecyclerView.ItemDecoration {
    private int itemMarginLeftAndTop;

    public ItemMarginDecoration(int itemMargin) {
        this.itemMarginLeftAndTop = itemMargin;
    }

    public ItemMarginDecoration(Context context, @DimenRes int itemMarginId) {
        this(context.getResources().getDimensionPixelOffset(itemMarginId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(itemMarginLeftAndTop, itemMarginLeftAndTop, itemMarginLeftAndTop, itemMarginLeftAndTop);
    }
}
