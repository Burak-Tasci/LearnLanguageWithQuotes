package com.tsci.learnlanguagewithquotes.common.extension

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.tsci.learnlanguagewithquotes.R


fun ChipGroup.setChips(list: List<String>, onLongClick: (Int) ->Unit){
    for (element in list){
        val chip = LayoutInflater.from(context)
            .inflate(R.layout.item_chip, this,false) as Chip
        chip.text = element
        chip.setOnLongClickListener{onLongClick(list.indexOf(element));true }
        this.addView(chip)
    }
}

fun ChipGroup.clearChips(){
    this.removeAllViews()
}