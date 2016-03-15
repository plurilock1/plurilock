package group1.cpsc319.plurilock_client.DataCollectionUtils.Context;

import org.json.JSONObject;

/**
 * Generic Interface for classes that retrieve device specific data
 *
 * @see  CollectGeoInfo , CollectHardwareInfo
 * Created by Junoh on 2/27/2016.
 */
public interface CollectDeviceContextData {

    public JSONObject collectDeviceInfo();
}
