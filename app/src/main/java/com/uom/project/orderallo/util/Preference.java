package com.uom.project.orderallo.util;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Himashi Nethinika on 2/17/17.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface Preference {

    @DefaultInt(0)
    int FBLoginCount();

    @DefaultInt(0)
    int LoginCount();

    @DefaultString("")
    String AuthToken();

    @DefaultString("")
    String userName();

    @DefaultString("")
    String userPassword();

    // The field lastUpdated will have default value 0
    long lastUpdated();

}
