package uz.mobiler.lesson66adiblar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import uz.mobiler.lesson66adiblar.models.Adib
import uz.mobiler.lesson66adiblar.R
import uz.mobiler.lesson66adiblar.databinding.ItemAdibBinding

class AdibAdapter(
    val context: Context,
    var list: ArrayList<Adib>,
    val listener: OnItemClickListener,
) : RecyclerView.Adapter<AdibAdapter.Vh>() {

    inner class Vh(val itemAdibBinding: ItemAdibBinding) :
        RecyclerView.ViewHolder(itemAdibBinding.root) {
        fun onBind(adib: Adib, position: Int, holder: Vh) {
            itemAdibBinding.apply {
                name.text = adib.name
                date.text = "(${adib.birthYear}-${adib.diedYear})"
                if (adib.isSave == true) {
                    saveImg.setImageResource(R.drawable.ic_saqlanganlar)
                    save.setBackgroundResource(R.drawable.save1)
                } else {
                    saveImg.setImageResource(R.drawable.ic_saqlangan)
                    save.setBackgroundResource(R.drawable.save2)
                }
                Glide.with(context)
                    .load(adib.photoUrl)
                    .apply(
                        RequestOptions().placeholder(
                            R.drawable.plase_img
                        ).centerCrop()
                    )
                    .into(image)

                save.setOnClickListener {
                    listener.onItemSaveListener(adib, position, holder)
                }

                itemView.setOnClickListener {
                    listener.onItemClickListener(adib, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemAdibBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position, holder)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClickListener(adib: Adib, position: Int)
        fun onItemSaveListener(adib: Adib, position: Int, holder: Vh)
    }

    fun filterList(filteredList: ArrayList<Adib>) {
        list = filteredList
        notifyDataSetChanged()
    }
}