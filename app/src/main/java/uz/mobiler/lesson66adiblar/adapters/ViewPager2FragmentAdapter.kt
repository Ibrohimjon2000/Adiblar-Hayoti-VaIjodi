package uz.mobiler.lesson66adiblar.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.mobiler.lesson66adiblar.fragments.AdiblarViewPagerFragment

class ViewPager2FragmentAdapter(
    fragment: Fragment,
    private val list: List<String>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return AdiblarViewPagerFragment.newInstance(list[position])
    }
}