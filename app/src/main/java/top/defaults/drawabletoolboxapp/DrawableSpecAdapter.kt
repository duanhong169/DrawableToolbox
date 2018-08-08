package top.defaults.drawabletoolboxapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.*
import top.defaults.drawabletoolboxapp.spec.DrawableSpec
import top.defaults.drawabletoolboxapp.spec.ImageViewSourceDrawableSpec
import top.defaults.drawabletoolboxapp.spec.SegmentedControlDrawableSpec
import top.defaults.drawabletoolboxapp.spec.TextViewBackgroundDrawableSpec

class DrawableSpecAdapter(private val drawableSpecList: List<DrawableSpec>) : RecyclerView.Adapter<DrawableSpecAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_drawable_spec, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemCount(): Int {
        return drawableSpecList.size
    }

    private fun getItem(position: Int): DrawableSpec {
        return drawableSpecList[position]
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView
        private val imageViewBoard: View
        private val imageView: ImageView
        private val animateCheckBox: CheckBox
        private val button: TextView
        private val segmentedControl: LinearLayout
        private var animator: ObjectAnimator? = null

        init {
            itemView.setOnClickListener { v -> onItemClickListener?.onItemClick(v, adapterPosition) }
            nameTextView = itemView.findViewById(R.id.name)
            imageViewBoard = itemView.findViewById(R.id.imageViewBoard)
            imageView = itemView.findViewById(R.id.imageView)
            animateCheckBox = itemView.findViewById(R.id.animateCheckBox)
            button = itemView.findViewById(R.id.button)
            segmentedControl = itemView.findViewById(R.id.segmentedControl)
        }

        fun bind(drawableSpec: DrawableSpec, position: Int) {
            animator?.run {
                cancel()
            }

            nameTextView.text = drawableSpec.name
            nameTextView.visibility = View.GONE
            imageViewBoard.visibility = View.GONE
            button.visibility = View.GONE
            segmentedControl.visibility = View.GONE
            when (drawableSpec) {
                is ImageViewSourceDrawableSpec -> {
                    val drawable = drawableSpec.build()

                    nameTextView.visibility = View.VISIBLE
                    imageViewBoard.visibility = View.VISIBLE
                    imageView.setImageDrawable(drawable)
                    imageView.setImageLevel(drawableSpec.initialLevel)
                    animateCheckBox.visibility = if (drawableSpec.canAnimate()) View.VISIBLE else View.INVISIBLE
                    if (drawableSpec.canAnimate()) {
                        animateCheckBox.setOnCheckedChangeListener(null)
                        animateCheckBox.isChecked = drawableSpec.animationEnabled
                        animateCheckBox.setOnCheckedChangeListener { _, isChecked ->
                            drawableSpec.animationEnabled = isChecked
                            notifyItemChanged(position)
                        }
                    }

                    if (drawableSpec.shouldAnimate()) {
                        animator = ObjectAnimator.ofInt(drawable, "level", 10000, 0)
                        animator?.run {
                            repeatCount = ValueAnimator.INFINITE
                            repeatMode = drawableSpec.animationRepeatMode
                            duration = 3000
                            interpolator = LinearInterpolator()
                            start()
                        }
                    }
                }
                is TextViewBackgroundDrawableSpec -> {
                    button.visibility = View.VISIBLE
                    button.setBackgroundDrawable(drawableSpec.build())
                    button.text = drawableSpec.name
                    if (drawableSpec.isDarkBackground) {
                        button.setTextColor(Color.WHITE)
                    } else {
                        button.setTextColor(COLOR_DEFAULT_DARK)
                    }
                }
                is SegmentedControlDrawableSpec -> {
                    segmentedControl.visibility = View.VISIBLE
                    val itemCount = segmentedControl.childCount
                    for (i in 0 until itemCount) {
                        val textView = segmentedControl.getChildAt(i) as TextView
                        textView.setBackgroundDrawable(drawableSpec.build(i))
                        textView.isSelected = i == drawableSpec.selectedIndex
                        if (i == drawableSpec.selectedIndex) {
                            textView.setTextColor(Color.WHITE)
                        } else {
                            textView.setTextColor(COLOR_DEFAULT_DARK)
                        }
                        textView.setOnClickListener {
                            drawableSpec.selectedIndex = i
                            notifyItemChanged(position)
                        }
                    }
                }
            }
        }
    }
}
