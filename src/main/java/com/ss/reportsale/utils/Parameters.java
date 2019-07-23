package com.ss.reportsale.utils;

public class Parameters {

  public static final String PATH_IN = System.getProperty("os.name").toLowerCase().contains("win")
          ? System.getProperty("user.home") + "\\data\\in"
          : System.getProperty("user.home") + "/data/in";
  public static final String PATH_OUT = System.getProperty("os.name").toLowerCase().contains("win")
          ? System.getProperty("user.home") + "\\data\\out"
          : System.getProperty("user.home") + "/data/out";
  public static final String SPLIT_VAR = "ç";
}
