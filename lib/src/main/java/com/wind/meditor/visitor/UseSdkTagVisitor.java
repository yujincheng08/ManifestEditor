package com.wind.meditor.visitor;

import com.wind.meditor.property.ModificationProperty;
import com.wind.meditor.utils.NodeValue;

import java.util.List;

import pxb.android.axml.NodeVisitor;

/**
 * @author Windysha
 */
public class UseSdkTagVisitor extends NodeVisitor {

    private final ModificationProperty.SDK sdk;

    UseSdkTagVisitor(NodeVisitor nv, ModificationProperty.SDK sdk) {
        super(nv);
        this.sdk = sdk;
    }

    @Override
    public void attr(String ns, String name, int resourceId, int type, Object obj) {
        if (sdk == null) {
            super.attr(ns, name, resourceId, type, obj);
            return;
        }
        if (NodeValue.UsesSDK.MAX_SDK_VERSION.equals(name) && sdk.getMaxSDK() > 0) {
            if (sdk.getMaxSDK() > 0)
                obj = String.valueOf(sdk.getMaxSDK());
        }
        if (NodeValue.UsesSDK.MIN_SDK_VERSION.equals(name) && sdk.getMinSDK() > 0) {
            if (sdk.getMinSDK() > 0)
                obj = String.valueOf(sdk.getMinSDK());
        }
        if (NodeValue.UsesSDK.TARGET_SDK_VERSION.equals(name) && sdk.getTargetSDK() > 0) {
            if (sdk.getTargetSDK() > 0)
                obj = String.valueOf(sdk.getTargetSDK());
        }
        super.attr(ns, name, resourceId, type, obj);
    }
}
