package jp.yumemi.lab.refactorme.module.qiita.presentation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.yumemi.lab.refactorme.android_mvc_sample.R;
import jp.yumemi.lab.refactorme.module.qiita.entity.QiitaItem;

public class QiitaItemListAdapter extends ArrayAdapter<QiitaItem> {

    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private int mResource;

    // コンストラクタ
    public QiitaItemListAdapter(Context context, int resource, List<QiitaItem> objects) {
        super(context, resource, objects);
        mActivity = (Activity)context;
        mLayoutInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mLayoutInflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        QiitaItem qiitaItemEntity = getItem(position);

        Picasso.with(mActivity)
                .load(qiitaItemEntity.user.profileImageUrl)
                .resize(100, 100)
                .centerCrop()
                .into(viewHolder.mProfileImageView);
        viewHolder.mTitleTextView.setText(qiitaItemEntity.title);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.profileImageView)
        ImageView mProfileImageView;
        @InjectView(R.id.titleTextView)
        TextView mTitleTextView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}