package com.ss.reportsale.utils;

import java.nio.file.Paths;

public class Parameters {

  public static final String PATH_IN = Paths.get(System.getProperty("user.home") + "/data/in").toString();
  public static final String PATH_OUT = Paths.get(System.getProperty("user.home") + "/data/out").toString();
  public static final String SPLIT_VAR = "ç";
}
