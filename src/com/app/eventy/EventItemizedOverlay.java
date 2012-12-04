package com.app.eventy;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.eventy.model.Event;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

@SuppressWarnings("rawtypes")
public class EventItemizedOverlay extends ItemizedOverlay {

	private List<Event> events = new ArrayList<Event>();
	private Context context;
	
	public EventItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		Event event = events.get(i);
		GeoPoint point = new GeoPoint((int) (event.getLat() * 1E6), (int) (event.getLng() * 1E6));
		OverlayItem overlayItem = new OverlayItem(point, null, null);
		return overlayItem;
	}

	@Override
	public int size() {
		  return events.size();
	}
		
	@Override
	protected boolean onTap(int index) {
		
		Event event = events.get(index);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_event, null);
		
		TextView description = (TextView) view.findViewById(R.id.description);
		description.setText(event.getDescription());
		
		TextView address = (TextView) view.findViewById(R.id.address);
		address.setText(event.getStreetAndNumber() + "\n"
				+ event.getPostcode() + " " + event.getCity() + "\n"
				+ event.getWojewodztwo());
		
		TextView contact = (TextView) view.findViewById(R.id.contact);
		contact.setText(
			"Manager: " + event.getManagerOfEvent() + "\n" +
			"WWW: " + event.getWebpage() + "\n" +
			"Tel: " + event.getPhone() + "\n" +
			"Skype: " + event.getSkype() + "\n"
		);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher).setTitle(event.getTitle()).setView(view).setPositiveButton("Zamknij", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
		
		return true;
	}

	public void addEvent(Event event) {
		events.add(event);
		populate();
	}
	
}
