package geox.eldoradio;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Georgiy on 11.03.2016.
 */
public class StationList extends ListActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        ListView listStations = getListView();
        ArrayAdapter<Station> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Station.stations
        );
        listStations.setAdapter(listAdapter);
    }

    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id){
        Intent intent = new Intent(StationList.this, RadioActivity.class);
        intent.putExtra(RadioActivity.EXTRA_STATIONNO, (int) id);
        startActivity(intent);
    }
}
