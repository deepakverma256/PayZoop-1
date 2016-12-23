// Copyright 2014 Google Inc. All Rights Reserved.

package com.objectedge.payzoop.credentials;

/**
 * Static container class for holding a reference to your YouTube Developer Key.
 */
public class DeveloperKey {

  /**
   * Please replace this with a valid API key which is enabled for the
   * YouTube Data API v3 service. Go to the
   * <a href="https://console.developers.google.com/">Google Developers Console</a>
   * to register a new developer key.
   */
  public static final String DEVELOPER_KEY = "AIzaSyAxdoTBxohue7OcyddYhaphLCFlA3OBIaE";

  public static com.objectedge.payzoop.model.APIKey APIKey;

  public static void setAPIKey(com.objectedge.payzoop.model.APIKey APIKey) {
    DeveloperKey.APIKey = APIKey;
    DeveloperKey.APIKey.setId_token("Bearer "+DeveloperKey.APIKey.getId_token());
  }

}
