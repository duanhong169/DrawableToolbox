package top.defaults.drawabletoolboxapp

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class DrawableSpecAdapter(private val drawableSpecList: List<DrawableSpec>) : RecyclerView.Adapter<DrawableSpecAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_drawable_spec, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drawableSpec = drawableSpecList[position]
        holder.bind(drawableSpec, position)
    }

    override fun getItemCount(): Int {
        return drawableSpecList.size
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
        private val textView: TextView
        private val animateCheckBox: CheckBox
        private var animator: ObjectAnimator? = null

        init {
            itemView.setOnClickListener { v -> onItemClickListener?.onItemClick(v, adapterPosition) }
            nameTextView = itemView.findViewById(R.id.name)
            imageViewBoard = itemView.findViewById(R.id.imageViewBoard)
            imageView = itemView.findViewById(R.id.imageView)
            textView = itemView.findViewById(R.id.textView)
            animateCheckBox = itemView.findViewById(R.id.animateCheckBox)
        }

        fun bind(drawableSpec: DrawableSpec, position: Int) {
            nameTextView.text = drawableSpec.name
            val drawable = drawableSpec.build()

            nameTextView.visibility = View.GONE
            imageViewBoard.visibility = View.GONE
            textView.visibility = View.GONE
            when (drawableSpec.type) {
                DrawableSpec.TYPE_IMAGE_VIEW_SOURCE -> {
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
                }
                DrawableSpec.TYPE_TEXT_VIEW_BACKGROUND -> {
                    textView.visibility = View.VISIBLE
                    textView.setBackgroundDrawable(drawable)
                    textView.text = drawableSpec.name
                    if (drawableSpec.isDarkBackground) {
                        textView.setTextColor(Color.WHITE)
                    } else {
                        textView.setTextColor(COLOR_DEFAULT_DARK)
                    }
                }
            }

            animator?.run {
                cancel()
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
    }
}
