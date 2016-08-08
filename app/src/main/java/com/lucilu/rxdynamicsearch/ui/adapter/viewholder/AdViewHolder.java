package com.lucilu.rxdynamicsearch.ui.adapter.viewholder;

import com.lucilu.rxdynamicsearch.R;
import com.lucilu.rxdynamicsearch.data.pojo.Ad;
import com.lucilu.rxdynamicsearch.utils.ViewUtils;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

public class AdViewHolder extends SimpleViewHolder<Ad> {

    @NonNull
    private final TextView headerTextView;

    public AdViewHolder(final View itemView) {
        super(itemView);

        headerTextView = ViewUtils.find(itemView, R.id.item_textView_header);
    }

    @Override
    public void bind(@NonNull final Ad model) {
        headerTextView.setText(model.header());
    }
}
