package com.example.maps;

import android.content.Context;
import android.location.LocationManager;

public class GeoSensor {

		private LocationManager m_locMngr;
		private MyLocationListener m_locLstrn;
		private Maps mContext;
	public GeoSensor(Maps context) {
		super();
		mContext=context;
        m_locMngr = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        m_locLstrn = new MyLocationListener();
        m_locLstrn.act=(Maps)context;
	}
    
    
    public void onResume()
    {
    	m_locMngr.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,1,m_locLstrn);
    }
    
    public void onPause()
    {
    	m_locMngr.removeUpdates(m_locLstrn);
    }
    
    public void onStop()
    {
    }

}
