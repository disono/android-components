package disono.com.webmons.material.components.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.ExpandableListView

class NoScrollExListView : ExpandableListView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
            Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST
        ))
        val params = layoutParams
        params.height = measuredHeight
    }
}