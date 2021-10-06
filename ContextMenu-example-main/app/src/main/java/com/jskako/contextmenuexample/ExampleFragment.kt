package com.jskako.contextmenuexample

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.jskako.contextmenuexample.databinding.FragmentExampleBinding

class ExampleFragment : Fragment() {

    private var binding: FragmentExampleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentExampleBinding.inflate(inflater, container, false)

        binding?.apply { registerForContextMenu(this.buttonFragment) }
        binding?.apply { registerForContextMenu(this.exampleET) }
        binding?.apply { registerForContextMenu(this.constraintLayoutFragment) }

        return binding?.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.setHeaderTitle("Pick option")
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)

        //Disable copy
        menu.findItem(R.id.copyItem).isEnabled = !binding?.exampleET?.text.isNullOrEmpty()
        //Hide share
        menu.findItem(R.id.shareItem).isVisible = !binding?.exampleET?.text.isNullOrEmpty()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.copyItem -> {
                someCopyFun()
            }
            R.id.shareItem -> {
                someShareFun()
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun someShareFun() {}
    private fun someCopyFun() {}

}