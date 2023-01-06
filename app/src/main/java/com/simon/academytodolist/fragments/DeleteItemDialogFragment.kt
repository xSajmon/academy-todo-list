package com.simon.academytodolist.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.simon.academytodolist.viewmodel.ListViewModel


class DeleteItemDialogFragment: DialogFragment() {

    private val listViewModel: ListViewModel by activityViewModels()

    companion object {
        const val TAG = "DeleteItemDialog"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val item  = requireArguments().getString("item")
        val position = requireArguments().getInt("itemPosition")
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle("Delete")
        alert.setMessage("Do you want to delete $item from the list?")
        alert.setCancelable(false)
        alert.setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
        alert.setPositiveButton("Yes") { dialogInterface, _ ->
            listViewModel.changeItemState(position)
            dialogInterface.cancel()
        }
        return alert.create()
    }
}