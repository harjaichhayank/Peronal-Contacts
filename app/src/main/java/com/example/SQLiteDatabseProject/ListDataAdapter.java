package com.example.SQLiteDatabseProject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ListDataAdapter extends ArrayAdapter<Object> {

    private List<Object> list = new ArrayList<>();

    ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    static class LayoutHandler{
        TextView NAME,EMAIL,NUMBER;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutHandler layoutHandler;

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.row_layout,parent,false);

            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = convertView.findViewById(R.id.UserNameId);
            layoutHandler.EMAIL = convertView.findViewById(R.id.UserEmailId);
            layoutHandler.NUMBER = convertView.findViewById(R.id.UserNumberId);

            convertView.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) convertView.getTag();
        }
        DataProvider dataProvider = (DataProvider) this.getItem(position);
        assert dataProvider != null;
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.NUMBER.setText(dataProvider.getNumber());
        layoutHandler.EMAIL.setText(dataProvider.getEmail());
        return convertView;
    }
}