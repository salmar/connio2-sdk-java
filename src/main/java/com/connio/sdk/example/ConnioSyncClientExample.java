/**
 * Copyright (c) 2016 Connio Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: juan@connio.com
 *
 */

package com.connio.sdk.example;

import com.connio.sdk.Connio;
import com.connio.sdk.SequentialAccessResultPage;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.data.DataPoint;
import com.connio.sdk.resource.data.DataValue;
import com.connio.sdk.resource.data.DeviceState;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.connio.sdk.resource.property.Property;

import java.util.Optional;

/**
 * Code sample of synchronous API using implicit Connio client.
 */
public class ConnioSyncClientExample {

	public static void main(String[] args) {
		try {

            // Initialises the context with api key credentials
            Connio.init("_key_671901158138828071", "31acec81b2414b03acf3d8c37ebdf305");

            // Create device profile add request and execute device profile creation
            final DeviceProfile deviceProfile = DeviceProfile.create("device_profile_sdk").execute();

            // Once the property client is ready we use it to create a property
            final Property property = deviceProfile.addProperty("numericProperty1", Property.Type.Number)
                    .setAccess(Property.Access.Public)
                    .execute();

            final MethodImpl implementation = new MethodImpl.Builder("return value;", MethodImpl.ExecType.Javascript).build();
            final Method method = deviceProfile.addMethod("getter", Method.Access.Public, implementation)
                    .setInputId(property.getId())
                    .execute();

            // Once the the device profile and the property is created we create the device
            final Device device = deviceProfile.addDevice().setStatus(Device.Status.Debug).execute();

            // Write three data points
            device.writeData(property, new DataFeed(new DataPoint.Builder(16.0).build())).execute();
            device.writeData(property, new DataFeed(new DataPoint.Builder(17.0).build())).execute();
            device.writeData(property, new DataFeed(new DataPoint.Builder(18.0).build())).execute();

            // Get public getter value
            Object value = device.readMethod(method).execute();

            // Get device state
            final Optional<DeviceState> deviceState = device.state().fetch();

            Thread.sleep(3000);

            // Read property historical values
            final SequentialAccessResultPage<DataValue> dataValues = device.readData(property).execute();

            // Now we print some data, note that as all operations where blocking at this point we already have
            // all information and so we don't need to wait until all executions finish
            System.out.println("Device profile id: " + deviceProfile.getId());
            System.out.println("Property id: " + property.getId());
            System.out.println("Method id: " + method.getId());
            System.out.println("Device id: " + device.getId());
            System.out.println("Getter method value: " + value);
            System.out.println("Device state: " + deviceState.get());
            System.out.println("Property historical values: " + dataValues);

            Connio.terminate();

        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}