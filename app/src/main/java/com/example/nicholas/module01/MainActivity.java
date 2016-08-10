package com.example.nicholas.module01;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    MyAdapter adapter;
    ArrayList data;
    ListView listView;
    Random random;

    public MainActivity() {
        data = new ArrayList();
        random = new Random();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter(this, data);
        listView.setAdapter(adapter);
        data.add(new ListItem(random.nextInt(8999) + 1000));
        data.add(new ListItem(random.nextInt(8999) + 1000));
        data.add(new ListItem(random.nextInt(8999) + 1000));
        data.add(new ListItem(random.nextInt(8999) + 1000));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(R.string.add);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuitem) {
        data.add(new ListItem(random.nextInt(8999) + 1000));
        adapter.notifyDataSetChanged();
        return super.onContextItemSelected(menuitem);
    }
    private class MyAdapter extends BaseAdapter {
        Context ctx;
        ArrayList data;
        Button delete;

        LayoutInflater inflater;

        MyAdapter(Context context, ArrayList arraylist) {
            super();
            ctx = context;
            inflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
            data = arraylist;
        }

        public int getCount() {
            int j = 0;
            for (int i = 0; i < data.size(); ) {
                int k = j;
                if (data.get(i) != null) {
                    k = j + 1;
                }
                i++;
                j = k;
            }

            return j;
        }

        public Object getItem(int i) {
            return data.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }
        public View getView(int i, View view, ViewGroup viewgroup) {

            ListItem listitem = (ListItem) getItem(i);

            View view1 = view;
            if (view == null) {
                view1 = inflater.inflate(R.layout.item, viewgroup, false);
            }

            ((TextView) view1.findViewById(R.id.tvNumber)).setText((new StringBuilder())
                    .append("Number ").append(i + 1).append(": ").append(listitem.number).toString());
            delete = (Button) view1.findViewById(R.id.button);
            delete.setTag(Integer.valueOf(i));
            delete.setOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view) {
                    data.remove(((Integer) view.getTag()).intValue());
                    notifyDataSetChanged();
                }
            });
            return view1;
        }


    }

    public class ListItem {
        int number;

        ListItem(int i) {
            number = i;
        }
    }
}
