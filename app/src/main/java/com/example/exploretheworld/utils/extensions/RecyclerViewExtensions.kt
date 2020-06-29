package com.example.exploretheworld.utils.extensions

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.withDivider(
    divider: DividerItemDecoration = DividerItemDecoration(
        context,
        LinearLayoutManager.VERTICAL
    )
) {
    addItemDecoration(divider)
}