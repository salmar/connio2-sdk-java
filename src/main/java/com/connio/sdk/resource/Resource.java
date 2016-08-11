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

package com.connio.sdk.resource;

import com.connio.sdk.request.ResourceDeleteRequest;
import com.connio.sdk.request.ResourceUpdateRequest;

import java.io.Serializable;

/**
 * Base abstract class for all Rest resources
 * @param <UR> resource update request
 * @param <DR> resource delete request
 */
public abstract class Resource<UR extends ResourceUpdateRequest, DR extends ResourceDeleteRequest> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract UR update();

    public abstract  DR delete();
}
