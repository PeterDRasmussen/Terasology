/*
 * Copyright 2012
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.asset;

import com.google.common.base.Objects;

import java.net.URI;
import java.util.Locale;

/**
 * @author Immortius
 */
public class AssetUri {
    private AssetType type;
    private String packageName;
    private String assetName;

    public AssetUri(AssetType type, String packageName, String assetName) {
        this.type = type;
        this.packageName = packageName.toLowerCase(Locale.ENGLISH);
        this.assetName = assetName.toLowerCase(Locale.ENGLISH);
    }

    public AssetUri(String uri) {
        // TODO: handle incomplete/relative uris?
        String[] typeSplit = uri.split(":", 2);
        if (typeSplit.length > 1) {
            type = AssetType.getTypeForId(typeSplit[0]);
            String[] packageSplit = typeSplit[1].split("/",2);
            if (packageSplit.length > 1) {
                packageName = packageSplit[0];
                assetName = packageSplit[1];
            }
        }
    }

    public AssetType getAssetType() {
        return type;
    }

    public String getPackage() {
        return packageName;
    }

    public String getAssetName() {
        return assetName;
    }

    public boolean isValid() {
        return type != null && !packageName.isEmpty() && !assetName.isEmpty();
    }

    @Override
    public String toString() {
        return type.getTypeId() + ":" + packageName + "/" + assetName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj instanceof AssetUri) {
            AssetUri other = (AssetUri) obj;
            return Objects.equal(type, other.type) && Objects.equal(packageName, other.packageName) && Objects.equal(assetName, other.assetName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, packageName, assetName);
    }
}