package com.bytes2gram.config;

import org.aeonbits.owner.ConfigCache;

/**
 * @author Dinesh
 */
public final class ConfigurationManager {

  private ConfigurationManager() {}

  public static Configuration config() {
    return ConfigCache.getOrCreate(Configuration.class);
  }
}
