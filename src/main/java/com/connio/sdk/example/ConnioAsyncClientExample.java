package com.connio.sdk.example;

import com.connio.sdk.Connio;
import com.connio.sdk.resource.data.DataFeed;
import com.connio.sdk.resource.data.DataPoint;
import com.connio.sdk.resource.data.DeviceState;
import com.connio.sdk.resource.device.Device;
import com.connio.sdk.resource.deviceprofile.DeviceProfile;
import com.connio.sdk.resource.method.Method;
import com.connio.sdk.resource.method.MethodImpl;
import com.connio.sdk.resource.property.Property;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ConnioAsyncClientExample {

	public static void main(String[] args) {
		try {

            // Initialises the context with api key credentials
            Connio.init("_key_671901158138828071", "31acec81b2414b03acf3d8c37ebdf305");

            // Create device profile
            final CompletableFuture<DeviceProfile> deviceProfile = DeviceProfile.create("device_profile_sdk").executeAsync();

            // On device profile creation we compose property creation using it
            final CompletableFuture<Property> property = deviceProfile.thenCompose((dp) -> {
                return dp.addProperty("numericProperty1", Property.Type.Number)
                        .setAccess(Property.Access.Public)
                        .executeAsync();
            });

            // On device profile and property creation we compose method creation using them
            final CompletableFuture<Method> method = deviceProfile.thenCombine(property, (dp, p) -> {return new ImmutablePair<>(dp, p);})
                    .thenCompose((deviceProfileAndProperty) -> {
                        final DeviceProfile dp = deviceProfileAndProperty.getLeft();
                        final Property prop = deviceProfileAndProperty.getRight();

                        final MethodImpl implementation = new MethodImpl.Builder("return value;", MethodImpl.ExecType.Javascript).build();
                        return dp.addMethod("getter", Method.Access.Public, implementation)
                                .setInputId(prop.getId())
                                .executeAsync();
                    });

            // On device profile and method creation we will create the device
            final CompletableFuture<Device> device = deviceProfile.thenCombine(method, (dp, m) -> {return dp;})
                    .thenCompose((dp) -> {
                        return dp.addDevice().setStatus(Device.Status.Debug).executeAsync();
                    });

            // Write three data points
            device.thenCombine(property, (d, p) -> d.writeData(p, new DataFeed(new DataPoint.Builder(16.0).build())).executeAsync());
            device.thenCombine(property, (d, p) -> d.writeData(p, new DataFeed(new DataPoint.Builder(17.0).build())).executeAsync());
            device.thenCombine(property, (d, p) -> d.writeData(p, new DataFeed(new DataPoint.Builder(18.0).build())).executeAsync());

            // Retrieve getter method value
            CompletableFuture<Object> methodValue = device.thenCombine(method, (d, m) -> { return new ImmutablePair<>(d, m); })
                    .thenCompose((deviceAndMethod) -> {
                        final Device d = deviceAndMethod.getLeft();
                        final Method m = deviceAndMethod.getRight();

                       return d.readMethod(m).executeAsync();
                    });

            // Get device state
            CompletableFuture<Optional<DeviceState>> deviceState = device.thenCompose(d -> { return d.state().fetchAsync(); });

            // Until this point there's no blocking whatsoever. Now we block until we get futures values to print
            // some information
            System.out.println("Device profile id: " + deviceProfile.get().getId());
            System.out.println("Property id: " + property.get().getId());
            System.out.println("Method id: " + method.get().getId());
            System.out.println("Device id: " + device.get().getId());
            System.out.println("Getter method value: " + methodValue.get());
            System.out.println("Device state: " + deviceState.get().get());

            Connio.terminate();

        } catch (Exception e) {
			e.printStackTrace();
		}
	}
}